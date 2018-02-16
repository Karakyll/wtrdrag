package project.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = {"project.entity", "project.repository", "project.service.implementation"})
@PropertySource("classpath:properties/database.properties")
@EnableJpaRepositories(basePackages = "project.repository")
public class DataConfig {

    @Value("${jdbc.driverClassName}")
    private String propDatabaseDriver;
    @Value("${jdbc.url}")
    private String propDatabaseUrl;
    @Value("${jdbc.username}")
    private String propDatabaseUsername;
    @Value("${jdbc.password}")
    private String propDatabasePassword;
    @Value("${hibernate.dialect}")
    private String propHibernateDialect;
    @Value("${hibernate.show_sql}")
    private String propHibernateShowSql;
    @Value("${hibernate.packages.to.scan}")
    private String propEntitymanagerPackagesToScan;
    @Value("${hibernate.hbm2ddl.auto}")
    private String propHibernateHbm2DdlAuto;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(propDatabaseDriver);
        dataSource.setUrl(propDatabaseUrl);
        dataSource.setUsername(propDatabaseUsername);
        dataSource.setPassword(propDatabasePassword);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(propEntitymanagerPackagesToScan);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", propHibernateDialect);
        properties.put("hibernate.show_sql", propHibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", propHibernateHbm2DdlAuto);

        return properties;
    }
}
