package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Validator;
import es.geeksusma.warehouse.data.ItemsDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class CreateItemConfiguration {

    @Bean
    public CreateItem createItem() {
        return new CreateItemComponent(createItemValidators(), saveItemRepository());
    }

    @Bean
    public ItemsDataSource itemsDataSource() {
        return new ItemsDataSource();
    }

    private SaveItemRepository saveItemRepository() {
        return new ItemRepository(itemsDataSource());
    }

    private ItemExistsRepository itemExistsRepository() {
        return new ItemRepository(itemsDataSource());
    }

    private List<Validator<Item>> createItemValidators() {
        return Collections.singletonList(new ItemExistsValidator(itemExistsRepository()));
    }
}
