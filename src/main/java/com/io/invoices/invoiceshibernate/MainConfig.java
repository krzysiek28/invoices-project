package com.io.invoices.invoiceshibernate;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class MainConfig {


    private String dbUrl;

    private String userName;

    private String password;

    public MainConfig(@Value("${spring.datasource.url}") String dbUrl, @Value("${spring.datasource.data-username}")
            String userName, @Value("${spring.datasource.data-password}")
                              String password) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.password = password;
    }

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
