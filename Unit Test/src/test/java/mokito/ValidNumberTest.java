package mokito;

import org.junit.jupiter.api.*;
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
    void checkZeroTest() {
        assertEquals(true ,validNumber.checkZero(-57));
    }

    @Test
    void checkZeroStringTest() {
        assertEquals(false, validNumber.checkZero("5"));
    }

    @Test
    void checkZero0Test() {
        assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
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
    void doubleToIntTest() {
        assertInstanceOf(Integer.class, validNumber.doubleToInt(2.5));
    }

    @Test
    void doubleToIntErrorTest() {
        assertEquals(0, validNumber.doubleToInt("a"));
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