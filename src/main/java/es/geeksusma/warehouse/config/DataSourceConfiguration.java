package es.geeksusma.warehouse.config;

import es.geeksusma.warehouse.data.ItemsDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public ItemsDataSource itemsDataSource() {
        return new ItemsDataSource();
    }
}
