package com.example.javaweb1;

import jakarta.servlet.MultipartConfigElement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Configuration
@SpringBootApplication
@MapperScan("com.example.javaweb1.mapper")
public class Javaweb1Application {

    public static void main(String[] args) {
        SpringApplication.run(Javaweb1Application.class, args);
    }
    @Bean
    public MultipartConfigElement getMultipartConfigElement(){
        /*创建一个配置类的工厂类对象*/
        MultipartConfigFactory factory = new MultipartConfigFactory();

        /*设置需要创建对象的相关信息*/
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));

        /*通过工厂类来创建MultipartConfigElement对象*/
        return factory.createMultipartConfig();
    }
}
