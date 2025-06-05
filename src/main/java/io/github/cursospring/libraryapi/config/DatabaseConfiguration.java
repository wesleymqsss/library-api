package io.github.cursospring.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    public DataSource dataSource(){
        DriverManagerDataSource ds =  new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    }

    @Bean
    public DataSource hikariDataSource(){

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); //maximo de conexoes liberadas
        config.setMinimumIdle(1); //minimo de conexoes para iniciar
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); // 600 mil ms conexao com o banco dura no maximo(10min)
        config.setConnectionTimeout(100000); // 100 mil ms tempo para tentar conexao, se passar de (3min) exibe error
        config.setConnectionTestQuery("select 1"); // testando conexao com o banco de dados

        return new HikariDataSource(config);

    }
}
