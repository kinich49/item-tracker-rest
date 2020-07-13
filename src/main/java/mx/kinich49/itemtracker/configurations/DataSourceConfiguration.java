package mx.kinich49.itemtracker.configurations;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration()
@Profile(value = {"production", "local"})
public class DataSourceConfiguration {

    Environment environment;

    @Autowired
    public DataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource dataSource() throws SQLException {
        //Password must be injected via command-line argument
        return new MariaDbDataSource();
    }
}
