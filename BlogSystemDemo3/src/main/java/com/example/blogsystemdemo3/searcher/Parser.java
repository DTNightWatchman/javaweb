package com.example.blogsystemdemo3.searcher;

import com.example.blogsystemdemo3.component.ApplicationContextUtil;
import com.example.blogsystemdemo3.mapper.BlogMapper;
import com.example.blogsystemdemo3.searcher.model.BlogInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: YT
 * @Description: 用来构建调用索引中的方法
 * @DateTime: 2022/7/29$ - 17:41
 */
@Component
public class Parser {


    private Index index = new Index();

    //@Autowired
    private BlogMapper blogMapper = ApplicationContextUtil.getBean(BlogMapper.class);

    public void runAllData() {
        System.out.println("开始向数据库中读取所有数据");
        System.out.println(index);
        System.out.println(blogMapper);
        System.out.println("========");
        List<BlogInfo> blogInfos = blogMapper.getBlogTitleAndContent();
        System.out.println("读取结束");
        for (BlogInfo blogInfo: blogInfos) {
            blogInfo.setUrl("blogDetail.html?blogId=" + blogInfo.getBlogId());
            // 此处使用线程池加快速度
            index.addBlog(blogInfo);
        }
        System.out.println("结束读取");
    }
}
