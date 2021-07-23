package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Mapper;
import es.geeksusma.warehouse.data.ItemEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemMappersConfiguration {

    @Bean
    public Mapper<Item, ItemEntity> itemItemEntityMapper() {
        return new ItemItemEntityMapper();
    }
}
