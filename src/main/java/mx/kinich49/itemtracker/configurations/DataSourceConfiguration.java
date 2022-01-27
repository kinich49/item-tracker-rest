package mx.kinich49.itemtracker.configurations;

import org.mariadb.jdbc.MariaDbDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration()
public class DataSourceConfiguration {

    Environment environment;

    @Autowired
    public DataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    @Profile({"production", "local"})
    public DataSource mariaDbDataSource() throws SQLException {
        return new MariaDbDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "azure.datasource")
    @Profile("azure")
    public DataSource postgresDataSource() throws SQLException {
        return new PGSimpleDataSource();
    }
}
