package org.california.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan("org.california")
public class HibernateUtil {

    public StandardServiceRegistry registry;
    public SessionFactory sessionFactory;


    @Bean
    public DataSource getDataSource()  {
        BasicDataSource dataSource = new BasicDataSource();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
        }

        dataSource.setUrl("jdbc:mysql://localhost:3306/fridge?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setPoolPreparedStatements(true);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory()  {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties settings = new Properties();

        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "false");

        factoryBean.setHibernateProperties(settings);
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
