package org.california.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan("org.california")
@PropertySource("classpath:hibernate.properties")
public class HibernateUtil {

    public StandardServiceRegistry registry;
    public SessionFactory sessionFactory;

    @Bean
    public static DataSource getDataSource() {
        Properties props = Environment.getProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(props.getProperty(Environment.DRIVER));
        dataSource.setUrl(props.getProperty(Environment.URL));
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }


    @Bean
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties settings = Environment.getProperties();
        settings.setProperty("show_sql", "true");

        factoryBean.setHibernateProperties(settings);
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("org.california.model", "java.util");

        return factoryBean;
    }



    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

}
