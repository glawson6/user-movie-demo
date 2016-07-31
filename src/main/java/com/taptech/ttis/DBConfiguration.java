package com.taptech.ttis;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.taptech.ttis.util.DBConfigUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Gregory Lawson on 1/31/16.
 */
@Configuration
@EntityScan({ "com.taptech.ttis.entity" })
@EnableJpaRepositories("com.taptech.ttis.repository")
@EnableTransactionManagement
@Profile({"development"})
public class DBConfiguration implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(DBConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;
    private Environment env;
    public final static String APP_NAME = "cfa";

    /*
    @Autowired(required = false)
    private DBProperties dbProperties;
    */

    private final static String DATABASE_URL_MESSAGE = "DATABASE_URL has not been set as an environment variable or " +
            "spring.datasource.databaseURL has not been set as a system property. " +
            "Ex. jdbcDBName://user:password@host:port/databaseName?optionKey1=optionValue1&optionKey2=optionValue2 =>  " +
            "mysql://user:pass@localhost:3306/notification?autoReconnect=true";

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    private static final String TEST_QUERY = "SELECT 1";

    @Bean(destroyMethod = "shutdown")
    @Qualifier(value = "dataSource")
    public DataSource dataSource() {
        String dbURLStr = env.getProperty("DATABASE_URL");
        if (null == dbURLStr || dbURLStr.trim().toString().equals("")){
            dbURLStr = propertyResolver.getProperty("databaseURL");
        }
        if (null == dbURLStr || dbURLStr.trim().toString().equals("")){
            dbURLStr = propertyResolver.getProperty("url");
        }
        logger.debug("DATABASE URL is {}",dbURLStr);
        if (null == dbURLStr || dbURLStr.trim().toString().equals("")){
            throw new BeanCreationException(DATABASE_URL_MESSAGE);
        }
        Map<String, String> dbInfo = DBConfigUtil.extractDatabaseConnectionInfo(APP_NAME,dbURLStr,true);
        String jdbcUrl = DBConfigUtil.createJdbcUrl(APP_NAME,dbURLStr, true);

        HikariDataSource dataSource = getHikariDataSource(dbInfo, jdbcUrl,propertyResolver, env);

        configureFlyway(jdbcUrl, dbInfo);
        return dataSource;
    }

    private static  HikariDataSource getHikariDataSource(Map<String, String> dbInfo, String jdbcUrl,RelaxedPropertyResolver propertyResolver,Environment env) {
        HikariConfig config = new HikariConfig();
        //config.setDataSourceClassName(propertyResolver.getProperty("dataSourceClassName"));
        //config.setDriverClassName("com.mysql.jdbc.Driver");
        logger.debug("Creating JDBC URL => {}",jdbcUrl);
        config.setJdbcUrl(jdbcUrl);
        config.addDataSourceProperty(DBConfigUtil.DATABASE_NAME, dbInfo.get(DBConfigUtil.DATABASE_NAME));
        config.addDataSourceProperty(DBConfigUtil.SERVER_NAME, dbInfo.get(DBConfigUtil.SERVER_NAME));
        config.addDataSourceProperty(DBConfigUtil.USER, dbInfo.get(DBConfigUtil.USER));
        config.addDataSourceProperty(DBConfigUtil.PASSWORD, dbInfo.get(DBConfigUtil.PASSWORD));

        String profiles = Arrays.toString(env.getActiveProfiles());
        logger.info("############################DATABASE CONFIG######################");
        logger.info("Profiles => {}",profiles);
        HikariDataSource dataSource = new HikariDataSource(config);
        // TODO configure pool size and other needed performance tuning fields.
        int maximumPoolSize = propertyResolver.getProperty("maximumPoolSize") == null?5: Integer.parseInt(propertyResolver.getProperty("maximumPoolSize"));
        String testQuery = propertyResolver.getProperty("validationQuery") == null?TEST_QUERY:propertyResolver.getProperty("validationQuery");
        logger.info("Setting maximum database connection pool size to {}",maximumPoolSize);
        logger.info("Test validation query {}",testQuery);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setConnectionTestQuery(testQuery);
        return dataSource;
    }

    private void configureFlyway(String jdbcUrl, Map<String, String> dbInfo){
        System.setProperty("flyway.user",dbInfo.get(DBConfigUtil.USER));
        System.setProperty("flyway.password",dbInfo.get(DBConfigUtil.PASSWORD));
        System.setProperty("flyway.url",jdbcUrl);
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        Hibernate4Module module = new Hibernate4Module();
        return module;
    }


    public void something(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.migrate();

    }


}
