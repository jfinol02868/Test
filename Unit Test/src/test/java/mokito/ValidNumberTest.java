package mokito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        validNumber = new ValidNumber();
    }

    @AfterEach
    void tearDown() {
        validNumber= null;
    }

    @Test
    void checkIsNumberTest() {
        assertTrue(validNumber.check(5));
    }

    @Test
    void checkNegativeTest() {
        assertFalse(validNumber.check(-5));
    }

    @Test
    void checkIsNotNumberTest() {
        assertFalse(validNumber.check("Hello"));
    }

    @Test
    @DisplayName("Validando distintos resultados de 'validNumber' ")
    void assertAllTest() {
        assertAll(
                () -> assertTrue(validNumber.check(5)),
                () -> assertFalse(validNumber.check(-5)),
                () -> assertFalse(validNumber.check("Hello"))
        );
    }

    @ParameterizedTest(name = "{index} => o={0}")
    @MethodSource("providerData")
    void validateSetDataTest(Object o) {
        assertFalse(validNumber.check(o));
    }

    private static Stream<Arguments> providerData(){
        return Stream.of(
                Arguments.of(15),
                Arguments.of(-5),
                Arguments.of("Hello")
        );
    }
}