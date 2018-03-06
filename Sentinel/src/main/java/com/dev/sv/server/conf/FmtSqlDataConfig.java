package com.dev.sv.server.conf;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by N5608296 on 13/09/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.dev.db.data.sql.fmt"}, entityManagerFactoryRef = "fmtEntityManagerFactory",
        transactionManagerRef = "fmtTransactionManager")
@EntityScan({ "com.dev.db.data.sql.fmt", "BOOT-INF.classes.com.dev.db.data.sql.fmt" })
@ComponentScan("com.dev.db.data.sql.fmt")
public class FmtSqlDataConfig {
    private static final Logger logger = LoggerFactory.getLogger(FmtSqlDataConfig.class);
    @Autowired
    private Environment env;

    @Bean(name = "fmtDataSource")
    public DataSource getDataSource() {
        System.out.println("FMT : "+env.getProperty("fmt.spring.datasource.username"));
        return DataSourceBuilder
                .create()
                .url(env.getProperty("fmt.spring.datasource.url"))
                .username(env.getProperty("fmt.spring.datasource.username"))
                .password(env.getProperty("fmt.spring.datasource.password"))
                .driverClassName(env.getProperty("fmt.spring.datasource.driverClassName"))
                .build();
    }

    @Bean
    public EntityManager entityManager() {
        return fmtEntityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean fmtEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setJpaProperties(getJpaProperties());
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPackagesToScan("com.dev.db.data.sql.fmt.bean");
        return em;
    }

    @Bean
    public PlatformTransactionManager fmtTransactionManager(@Qualifier("fmtEntityManagerFactory")EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public Properties getJpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("org.hibernate.envers.store_data_at_delete",true);
        jpaProperties.put("hibernate.default_catalog","FMT_DB");
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.SQLServerDialect");
        jpaProperties.put("hibernate.show_sql",false);
        jpaProperties.put("hibernate.format_sql",false);
        jpaProperties.put("hibernate.hbm2ddl.auto","update");
        jpaProperties.put("hibernate.id.new_generator_mappings",true);
        jpaProperties.put("hibernate.connection.autocommit",true);
        jpaProperties.put("hibernate.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("hibernate.c3p0.min_size","5");
        jpaProperties.put("hibernate.c3p0.max_size","200");
        jpaProperties.put("hibernate.c3p0.timeout","60000");
        jpaProperties.put("hibernate.c3p0.max_statements","0");
        jpaProperties.put("hibernate.c3p0.idle_test_period","5000");
        jpaProperties.put("hibernate.c3p0.test-while-idle",true);
        jpaProperties.put("hibernate.c3p0.test-on-borrow",true);
        jpaProperties.put("hibernate.c3p0.validation-query","SELECT 1 FROM DUAL");
        jpaProperties.put("hibernate.c3p0.time-between-eviction-runs-millis",5000);
        jpaProperties.put("hibernate.c3p0.min-evictable-idle-time-millis",60000);

        return jpaProperties;
    }
}
