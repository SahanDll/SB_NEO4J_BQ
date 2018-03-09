package com.dev.sv.server.conf;

import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by N5608296 on 13/09/17.
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "com.dev.db.data.graph", transactionManagerRef = "neo4jTransactionManager",
        sessionFactoryRef = "sessionFactory")
@EntityScan({ "com.dev.db.data.graph", "BOOT-INF.classes.com.dev.db.data.graph" })
@ComponentScan("com.dev.db.data.graph")
public class GraphDataConfig {
    private static final Logger logger = LoggerFactory.getLogger(GraphDataConfig.class);
    @Autowired
    private Environment env;

/*    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        System.out.println("NEO4J : "+env.getProperty("spring.data.neo4j.username"));
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();
        configuration.ddriverConfiguration().setDriverClassName(HttpDriver.class.getName())
        .setURI(env.getProperty("spring.data.neo4j.uri"))
        .setCredentials(env.getProperty("spring.data.neo4j.username"), env.getProperty("spring.data.neo4j.password"));
        return configuration;
    }*/

    @Bean
    public SessionFactory sessionFactory() {
        System.out.println("NEO4J : "+env.getProperty("spring.data.neo4j.username"));
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri(env.getProperty("spring.data.neo4j.uri"))
                .credentials(env.getProperty("spring.data.neo4j.username"), env.getProperty("spring.data.neo4j.password"))
                .build();
        return new SessionFactory(configuration, "com.dev.db.data.graph");
    }

    @Bean
    public Neo4jTransactionManager neo4jTransactionManager(SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }
}
