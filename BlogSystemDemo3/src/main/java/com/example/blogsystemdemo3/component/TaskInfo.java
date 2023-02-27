package com.example.blogsystemdemo3.component;

import com.example.blogsystemdemo3.searcher.Index;
import com.example.blogsystemdemo3.searcher.Parser;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/8/1$ - 17:54
 */
@Slf4j
@Component
@EnableScheduling
public class TaskInfo {
    @Value("${task.switch.is-open}")
    private boolean flag;

    @Scheduled(cron = "${task.corn.task-corn}")
    public void m1() {
        if (flag) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());
            System.out.println("定时任务启动->>" + format);
            Parser parser = new Parser();
            Index.forwardIndex = new ArrayList<>();
            Index.invertedIndex = new HashMap<>();
            parser.runAllData();
        } else {
            System.out.println("未设置定时任务");
        }
    }

}
