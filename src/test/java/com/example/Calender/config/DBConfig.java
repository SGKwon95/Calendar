package com.example.Calender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Bean
    public DataSource dataSource() {
        // JPA를 사용하기 위한 목적으로 설정하는 것들
        // MySQL의 사용자 비번 구동 드라이버 위치등을 지정하면 된다.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/calendar?serverTimezone=UTC&useSSL=false"
        );
        dataSource.setUsername("bitai");
        dataSource.setPassword("456123");
        return dataSource;
    }
}
