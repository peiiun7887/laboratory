package com.example;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class H2DataSourceConfig {

    // Embedded H2 database configuration: db1~db4
    @Bean(name = "dataSource1")
    public DataSource getDatasource1() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                // need file [db1.mv.db] under path C:\Users\{username}\
                .url("jdbc:h2:~/db1")
                .username("sa")
                .password("")
                .build();
    }

    @Bean(name = "dataSource2")
    public DataSource getDatasource2() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                // need file [db2.mv.db] under path {disk}:\laboratory\database\target\
                .url("jdbc:h2:file:./database/target/db2")
                .username("sa")
                .password("")
                .build();
    }

    @Bean(name = "dataSource3")
    public DataSource getDatasource3() {
        return DataSourceBuilder.create().type(HikariDataSource.class)
                .driverClassName("org.h2.Driver")
                // need file [db3.mv.db] under path {disk}:\h2db\
                .url("jdbc:h2:file:/h2db/db3'")
                .username("sa")
                .password("")
                .build();
    }

    @Bean(name = "dataSource4")
    public DataSource getDatasource4() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                // need file [db2.mv.db] under path D:\h2db\
                .url("jdbc:h2:file:D:/h2db/db4'")
                .username("sa")
                .password("")
                .build();
    }

    // In-memory H2 database configuration: db5、db6
    @Bean(name = "dataSource5")
    public DataSource getDatasource5() {
        return DataSourceBuilder.create().type(HikariDataSource.class)
                .driverClassName("org.h2.Driver")
                // need file [db2.mv.db] under path D:\h2db\
                .url("jdbc:h2:mem:db5;INIT=RUNSCRIPT FROM 'classpath:sql/schema.sql'\\;RUNSCRIPT FROM 'classpath:sql/data.sql'")
                .username("sa")
                .password("")
                .build();
    }

    // spring
    @Bean(name = "dataSource6")
    public DataSource getDatasource6() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                // url=jdbc:h2:mem:db6
                .setName("db6")
                // need file [schema.sql] under path src/main/resources/
                .addScript("classpath:sql/schema.sql").build();
    }

}
