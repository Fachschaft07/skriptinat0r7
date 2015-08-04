package edu.hm.cs.fs.scriptinat0r7;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Scriptinat0r7Application {
    public static void main(String[] args) {
        SpringApplication.run(Scriptinat0r7Application.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.main")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.ifw")
    public DataSource ifwDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplateIfw(@Qualifier("ifwDataSource") DataSource ifwDataSource) {
        return new JdbcTemplate(ifwDataSource);
    }
}
