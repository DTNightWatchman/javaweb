package com.example.blogsystemdemo3.searcher;

import com.example.blogsystemdemo3.searcher.model.BlogInfo;
import com.example.blogsystemdemo3.searcher.model.Weight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: YT
 * @Description: 用来构建索引
 * @DateTime: 2022/7/29$ - 17:41
 */
@Component
public class Index {
    public static Set<Integer> deleteSet = new HashSet<>();

    // 数组下标表示docId
    public static ArrayList<BlogInfo> forwardIndex = new ArrayList<>();

    // hash表来表示倒排索引
    // key 就是词
    // value 就是一组有关文章
    public static HashMap<String, ArrayList<Weight>> invertedIndex = new HashMap<>();

    public void addBlog(BlogInfo blogInfo) {
        // 新增文档操作，既需要这增加正排索引，也需要增加倒排索引
        buildForward(blogInfo);
        buildInveted(blogInfo);
    }

    /**
     * 增加正排索引
     * @param blogInfo
     */
    public void buildForward(BlogInfo blogInfo) {
        blogInfo.setId(forwardIndex.size());
        forwardIndex.add(blogInfo);
    }

    public void buildInveted(BlogInfo blogInfo) {
        class WordCount {
            public int titleCount;
            public int contentCount;
        }
        HashMap<String,WordCount> wordCountHashMap = new HashMap<>();

        // 针对标题进行分词
        List<Term> terms = ToAnalysis.parse(blogInfo.getTitle()).getTerms();
        for (Term term: terms) {
            String word = term.getName();
            WordCount wordCount = wordCountHashMap.get(word);
            if (wordCount == null) {
                WordCount newWordCount = new WordCount();
                newWordCount.titleCount = 1;
                newWordCount.contentCount = 0;
                wordCountHashMap.put(word,newWordCount);
            } else {
                wordCount.titleCount+=1;
            }
        }
        terms = ToAnalysis.parse(blogInfo.getContent()).getTerms();
        for (Term term : terms) {
            String word = term.getName();
            WordCount wordCount = wordCountHashMap.get(word);
            if (wordCount == null) {
                WordCount newWordCount = new WordCount();
                newWordCount.titleCount = 0;
                newWordCount.contentCount = 1;
                wordCountHashMap.put(word, newWordCount);
            } else {
                wordCount.contentCount+=1;
            }
        }
        // 汇总结果
        for (Map.Entry<String,WordCount> entry: wordCountHashMap.entrySet()) {
            Weight weight = new Weight();
            weight.setId(blogInfo.getId());
            weight.setWeight(entry.getValue().titleCount * 10 + entry.getValue().contentCount * 1);
            List<Weight> list = invertedIndex.get(entry.getKey());
            if (list == null) {
                ArrayList<Weight> newList = new ArrayList<>();
                newList.add(weight);
                invertedIndex.put(entry.getKey(), newList);
            } else {
                list.add(weight);
            }
        }

    }
    private ObjectMapper objectMapper = new ObjectMapper();
    public void save() {
        System.out.println("开始保存索引");
        // /root/oj_system
        File forwardIndexFile = new File("./forward.txt");
        // /root/oj_system
        File invertedIndexFile = new File("./inverted.txt");
        try {
            objectMapper.writeValue(forwardIndexFile,forwardIndex);
            objectMapper.writeValue(invertedIndexFile,invertedIndex);
            System.out.println("保存结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        File forwardIndexFile = new File("./forward.txt");
        File invertedIndexFile = new File("./inverted.txt");
        try {
            this.forwardIndex = objectMapper.readValue(forwardIndexFile, new TypeReference<ArrayList<BlogInfo>>() {});
            this.invertedIndex = objectMapper.readValue(invertedIndexFile, new TypeReference<HashMap<String, ArrayList<Weight>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("加载结束");
    }

    public List<Weight> getInverted(String word) {
        return invertedIndex.get(word);
    }

    public BlogInfo getBlogInfo(int id) {
        return forwardIndex.get(id);
    }

    public static void main(String[] args) {

    }

}
