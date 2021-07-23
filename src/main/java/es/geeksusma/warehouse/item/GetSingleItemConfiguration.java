package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.data.ItemsDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetSingleItemConfiguration {

    @Autowired
    private ItemsDataSource itemsDataSource;

    @Bean
    public GetSingleItem getSingleItemItem() {
        return new GetSingleItemComponent(getItemRepository());
    }


    private GetItemRepository getItemRepository() {
        return new ItemRepository(itemsDataSource);
    }
}
