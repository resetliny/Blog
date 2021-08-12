package com.minzheng.blog.schedule;

import com.minzheng.blog.dto.ArticleSearchDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: yuanlin
 * @date: 2021-07-16 16:01:50
 * @description:
 */

@Slf4j
@Component
public class ScheduleTask {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Scheduled(fixedRate = 1800000, initialDelay = 1800000)
    public void keepConnectionAlive() {
        log.debug("Trying to ping Elasticsearch");
        try {
            final long count = elasticsearchRestTemplate.count(new NativeSearchQueryBuilder().build(), ArticleSearchDTO.class);
            log.debug("Ping succeeded for ArticleSearchDTO, it contains {} entities", count);
        } catch (Exception e) {
            log.debug("Ping failed for ArticleSearchDTO");
        }
    }
}
