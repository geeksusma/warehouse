package es.geeksusma.warehouse.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemExistsValidatorShould {

    private ItemExistsValidator itemExistsValidator;
    @Mock
    private ItemExistsRepository itemExistsRepository;

    @BeforeEach
    void setUp() {
        itemExistsValidator = new ItemExistsValidator(itemExistsRepository);
    }

    @Test
    void throwNothing_when_serialNumberFree() {
        final String freeSn = "123";
        final Item newItem = ItemBuilder.builder()
                .serialNumber(freeSn)
                .build();

        assertDoesNotThrow(() -> itemExistsValidator.validate(newItem));

    }

    @Test
    void throwError_when_serialNumberInUse() {
        final String takenSn = "123";
        final Item newItem = ItemBuilder.builder()
                .serialNumber(takenSn)
                .build();

        given(itemExistsRepository.isSerialNumberInUse(takenSn)).willReturn(true);

        final Throwable error = Assertions.catchThrowable(() -> itemExistsValidator.validate(newItem));

        Assertions.assertThat(error).isInstanceOf(SerialNumberDuplicityException.class)
                .hasMessage("The serial number 123 is already taken");
    }
}