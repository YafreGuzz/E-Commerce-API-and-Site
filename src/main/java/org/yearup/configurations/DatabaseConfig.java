package org.yearup.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.data.mysql.MySqlCategoryDao;
import org.yearup.data.mysql.MySqlProductDao;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig
{
    private final BasicDataSource basicDataSource;

    @Bean
    public BasicDataSource dataSource()
    {
        return basicDataSource;
    }

    @Bean
    public ProductDao productDao(DataSource dataSource)
    {
        return new MySqlProductDao(dataSource);
    }

    @Bean
    public CategoryDao categoryDao(DataSource dataSource)
    {
        return new MySqlCategoryDao(dataSource);
    }

    @Autowired
    public DatabaseConfig(@Value("${datasource.url}") String url,
                          @Value("${datasource.username}") String username,
                          @Value("${datasource.password}") String password)
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

}