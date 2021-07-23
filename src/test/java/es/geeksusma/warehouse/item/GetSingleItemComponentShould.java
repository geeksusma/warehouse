package es.geeksusma.warehouse.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetSingleItemComponentShould {


    private GetSingleItem component;
    @Mock
    private GetItemRepository getItemRepository;

    @BeforeEach
    void setUp() {
        component = new GetSingleItemComponent(getItemRepository);
    }

    @Test
    void throwError_when_notFound() {

        final Long nonExistingId = 32131L;

        final Throwable error = catchThrowable(() -> component.byId(nonExistingId));

        assertThat(error).isInstanceOf(ItemNotFound.class).hasMessageContaining("The item 32131 was not found in the system");
    }

    @Test
    void throwError_when_notIdGiven() {

        final Throwable error = catchThrowable(() -> component.byId(null));

        assertThat(error).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("The id is mandatory to retrieve the item");
    }

    @Test
    void returnFoundItem_when_exists() {

        long existingId = 1234L;
        final Item expected = ItemBuilder.builder()
                .serialNumber("12345")
                .name("Toy Car")
                .description("Red colour medium size")
                .stock(40)
                .build();

        given(getItemRepository.getById(existingId)).willReturn(Optional.of(expected));

        final Item item = component.byId(existingId);

        assertThat(item).isEqualTo(expected);
    }
}