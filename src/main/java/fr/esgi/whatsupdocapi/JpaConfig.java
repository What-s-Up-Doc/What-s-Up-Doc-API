package fr.esgi.whatsupdocapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dbUser;
//
//    @Value("${spring.datasource.password}")
//    private String dbPass;

    @Bean(name = "mySqlDataSource")
    @Primary
    public DataSource mySqlDataSource()
    {
//        final String dbUrl = System.getProperty(SPRING_PROFILES_ACTIVE);

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mariadb://82.124.121.197:58654/whats-up-doc-db");
        dataSourceBuilder.username("WHATS_UP_USER_APP");
        dataSourceBuilder.password("8Iw0VvMXfnlAWct1p467");
        return dataSourceBuilder.build();
    }
}
