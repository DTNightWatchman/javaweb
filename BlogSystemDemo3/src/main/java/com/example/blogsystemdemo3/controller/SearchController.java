package com.example.blogsystemdemo3.controller;

import com.example.blogsystemdemo3.searcher.BlogSearcher;
import com.example.blogsystemdemo3.searcher.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: YT
 * @Description: 查找模块
 * @DateTime: 2022/7/31$ - 18:05
 */
@RestController
public class SearchController {
    @Autowired
    private BlogSearcher blogSearcher;
    @RequestMapping("/search")
    public List<Result> getResult(String query) {

        List<Result> results = blogSearcher.search(query);
        return results;
    }
}
