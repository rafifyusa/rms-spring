package com.mitrais.rms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("com.mitrais.rms.dao"), @ComponentScan("com.mitrais.rms.service")})
public class AppConfig {

    private Environment env;
    @Autowired
    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        //Setting JDBC properties
        Properties properties = new Properties();
        properties.put(DRIVER, env.getProperty("mysql.driver"));
        properties.put(URL, env.getProperty("mysql.url"));
        properties.put(USER, env.getProperty("mysql.user"));
        properties.put(PASS, env.getProperty("mysql.password"));

        //Setting Hibernate
        properties.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        properties.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
