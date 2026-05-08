package com.tomatos.lab.crud.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tomatos.lab.crud.mysql.mapper")
public class CrudExperimentMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudExperimentMysqlApplication.class, args);
    }
}
