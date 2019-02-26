package com.haohuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.haohuo.transactional.dao"})
@EnableTransactionManagement
public class WorkBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkBookApplication.class, args);
    }

}

