package com.shinelon.sharding.sphere.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Author syq
 * @Date 2019/9/24 11:18
 **/
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private JdbcTemplate pointsJdbcTemplate;

    @RequestMapping("/")
    public String index(){
        logger.info("sphere is start up!");
        return "sphere is start up!";
    }

    @RequestMapping("/pointsJdbc")
    public String jdbc(){
        String jdbcStr = pointsJdbcTemplate.toString();
        logger.info("jdbc:{}",jdbcStr);
        return jdbcStr;
    }
}
