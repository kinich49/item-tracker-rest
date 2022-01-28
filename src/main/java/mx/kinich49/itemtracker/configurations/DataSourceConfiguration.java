package mx.kinich49.itemtracker.configurations;

import org.mariadb.jdbc.MariaDbDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration()
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    @Profile({"production", "local"})
    public DataSource mariaDbDataSource() {
        return new MariaDbDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "azure.datasource")
    @Profile("azure")
    public DataSource postgresDataSource() {
        return new PGSimpleDataSource();
    }
}
