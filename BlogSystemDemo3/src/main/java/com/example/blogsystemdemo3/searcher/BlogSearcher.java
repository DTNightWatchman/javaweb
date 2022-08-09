package com.example.blogsystemdemo3.searcher;

import com.example.blogsystemdemo3.searcher.model.BlogInfo;
import com.example.blogsystemdemo3.searcher.model.Result;
import com.example.blogsystemdemo3.searcher.model.Weight;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * @Author: YT
 * @Description: 搜索
 * @DateTime: 2022/7/31$ - 18:06
 */

@Service
public class BlogSearcher {
    private Index index = new Index();


    public static boolean isEnglish(String p) {
        byte[] bytes = p.getBytes();
        int i = bytes.length;//i为字节长度
        int j = p.length();//j为字符长度
        if(i==j){
            return true;
        }else{
            return false;
        }
    }


    private Set<String> stopWords = new HashSet<>();
    private void loadStopWords() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./stop.txt"))) {
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                stopWords.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BlogSearcher() {
        index.load();
        // 下载暂停词
        loadStopWords();
    }

    public List<Result> search(String query) {
        // 先进行分词
        // 分词的时候去掉暂停词
        List<Term> oldTerms = ToAnalysis.parse(query).getTerms();
        List<Term> terms = new ArrayList<>();
        for (Term t : oldTerms) {
            if (!stopWords.contains(t)) {
                terms.add(t);
            }
        }
        // 得到分词结果，进行查询（倒排查询）
        List<List<Weight>> allResult = new ArrayList<>();
        for (Term term: terms) {
            String word = term.getName();
            List<Weight> list = index.getInverted(word);
            if (list != null) {
                allResult.add(list);
            }
        }

        List<Weight> finalResult = mergeResult(allResult);
        finalResult.sort(new Comparator<Weight>() {
            @Override
            public int compare(Weight o1, Weight o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });
        // 打包结果
        List<Result> results = new ArrayList<>();
        for (Weight weight : finalResult) {
            BlogInfo blogInfo = index.getBlogInfo(weight.getId());
            if (blogInfo.getBlogId() == -1) {
                continue;
            }
            Result result = new Result();
            result.setTitle(blogInfo.getTitle());
            result.setUrl(blogInfo.getUrl());
            String desc = getDesc(blogInfo.getContent(), terms);
            if (desc != null) {
                result.setDesc(desc);
                results.add(result);
            }
        }

        return results;
    }

    private String getDesc(String content, List<Term> terms) {
        int firstPos = -1;
        for (Term term : terms) {
            String word = term.getName();
            if (isEnglish(word)) {
                content = content.toLowerCase().replaceAll("\\b" + word + "\\b"," " + word + " ");
                firstPos = content.toLowerCase().indexOf(" " + word + " ");
                if (firstPos > 0) {
                    break;
                }
            } else {
                firstPos = content.indexOf(word);
                if (firstPos > 0) {
                    break;
                }
            }
        }
        if (firstPos == -1) {
            if (content.length() > 60) {
                return content.substring(0,60) + "...";
            } else {
                return content;
            }
        }
        // 以第一个位置作为基准位置
        String desc = null;
        int descBegin = firstPos < 60 ? 0 : firstPos - 60;
        if (firstPos + 80 >= content.length()) {
            desc = content.substring(descBegin);
        } else {
            desc = content.substring(descBegin, firstPos + 80) + "...";
        }

        for (Term term: terms) {
            String word = term.getName();
            if (isEnglish(word)) {
                desc = desc.replaceAll("(?i) " + word + " ", "<i> " + word + " </i>");
            } else {
                desc = desc.replaceAll(word, "<i>" + word + "</i>");
            }
        }
        return desc;
    }


    private List<Weight> mergeResult(List<List<Weight>> allResult) {
        class Pos {
            public int row;
            public int col;

            public Pos(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        for (List<Weight> curRow : allResult) {
            curRow.sort(new Comparator<Weight>() {
                @Override
                public int compare(Weight o1, Weight o2) {
                    return o1.getId() - o2.getId();
                }
            });
        }
        List<Weight> ret = new ArrayList<>();
        // 构造一个队列
        PriorityQueue<Pos> queue = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                // 根据pos的位置找到weight再进行比较
                Weight w1 = allResult.get(o1.row).get(o2.col);
                Weight w2 = allResult.get(o2.row).get(o2.col);
                return w1.getId() - w2.getId();
            }
        });
        for (int i = 0; i < allResult.size(); i++) {
            queue.offer(new Pos(i,0));
        }
        while (!queue.isEmpty()) {
            Pos min = queue.poll();
            Weight weight = allResult.get(min.row).get(min.col);
            if (ret.size() > 0) {
                Weight befWeight = ret.get(ret.size() - 1);
                if (befWeight.getId() == weight.getId()) {
                    befWeight.setWeight(befWeight.getWeight() + weight.getWeight());
                } else {
                    ret.add(weight);
                }
            } else {
                ret.add(weight);
            }
            // 向后移动
            Pos newPos = new Pos(min.row, min.col + 1);
            if (newPos.col < allResult.get(newPos.row).size()) {
                // 未到达结尾处
                queue.offer(newPos);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        BlogSearcher blogSearcher = new BlogSearcher();
        List<Result> results = blogSearcher.search("public");
        for (Result result :
                results) {
            System.out.println(result);
        }
    }
}
