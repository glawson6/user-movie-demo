package com.taptech.ttis;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by tap on 7/31/16.
 */
@Configuration
@EntityScan({ "com.taptech.ttis.entity" })
@EnableJpaRepositories("com.taptech.ttis.repository")
@EnableTransactionManagement
@Profile({"production","test"})
public class DatabaseConfiguration implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(DBConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;
    private Environment env;
    public final static String APP_NAME = "cfa";

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    private static final String TEST_QUERY = "SELECT 1";

    @Bean(destroyMethod = "shutdown")
    @Qualifier(value = "dataSource")
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
                .addScript("ddl.sql")
                .addScript("sample_data.sql")
                .build();
        return db;
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        Hibernate4Module module = new Hibernate4Module();
        return module;
    }


}
