package es.geeksusma.warehouse.item;

import es.geeksusma.warehouse.core.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static es.geeksusma.warehouse.item.Item.ItemBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateItemShould {

    private CreateItem itemComponent;
    @Mock
    private Validator<Item> itemExistsValidator;
    @Mock
    private SaveItemRepository saveItemRepository;

    @BeforeEach
    void setUp() {
        itemComponent = new CreateItemComponent(Collections.singletonList(itemExistsValidator), saveItemRepository);
    }

    @Test
    void throwIllegal_when_noItem() {

        Throwable error = catchThrowable(() -> itemComponent.create(null));

        assertThat(error).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Item can't be null or empty");
    }

    @Test
    void throwIllegal_when_emptyItem() {

        final Item emptyItem = setUpEmptyItem();

        Throwable error = catchThrowable(() -> itemComponent.create(emptyItem));

        assertThat(error).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Item can't be null or empty");
    }


    @Test
    void checkForDuplications_when_createFilledItem() {
        final Item hammer = setUp9PoundHammer();

        itemComponent.create(hammer);

        then(itemExistsValidator).should().validate(hammer);
    }

    @Test
    void skipSaving_when_exists() {
        final Item hammer = setUp9PoundHammer();
        doThrow(new SerialNumberDuplicityException("The serial number for the item already exists")).when(itemExistsValidator).validate(hammer);

        try {
            itemComponent.create(hammer);
            Assertions.fail();
        } catch (SerialNumberDuplicityException e) {
            then(saveItemRepository).shouldHaveNoInteractions();
        }
    }

    @Test
    void returnNewId_when_saveNewItem() {
        final Item hammer = setUp9PoundHammer();
        final Long givenId = 2L;
        when(saveItemRepository.save(hammer)).thenReturn(givenId);

        Long newId = itemComponent.create(hammer);

        then(saveItemRepository).should().save(hammer);
        assertThat(newId).isEqualTo(givenId);

    }

    private Item setUp9PoundHammer() {
        return ItemBuilder.builder().serialNumber("123").name("Big Hammer").description("9 pound hammer").stock(10).build();
    }

    private Item setUpEmptyItem() {
        return ItemBuilder.builder().build();
    }
}
