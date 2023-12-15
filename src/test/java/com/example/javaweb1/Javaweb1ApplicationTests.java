package com.example.javaweb1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Javaweb1ApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws Exception{
        System.out.println(dataSource.getConnection());
    }
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());

    }
}
