import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class H2DBConfig {

    @Bean(name = "dataSourceTest")
    public DataSource getDatasourceTest() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .setName("testdb;MODE=Oracle;DB_CLOSE_DELAY=-1;")
                .setScriptEncoding("UTF-8")
                .addScript("classpath:sql/schema.sql")
                .addScript("classpath:sql/data.sql")
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate template() {
        return new NamedParameterJdbcTemplate(getDatasourceTest());
    }
}
