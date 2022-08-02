package com.example.blogsystemdemo3.searcher;

import com.example.blogsystemdemo3.searcher.model.BlogInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/7/29$ - 19:41
 */
@SpringBootTest
class ParserTest {
    @Autowired
    Parser parser;




//        System.out.println("++++++++++");
//        for (Integer i :
//                Index.deleteSet) {
//            System.out.println(i);
//        }
//        System.out.println("===========");


    @Test
    void testRunAllData() {
        parser.runAllData();
    }

    @Test
    void runAllData() {
        parser.runAllData();
    }
}