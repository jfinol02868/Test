package junit5;

import Util.UtilMessage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    private Calculator calculatorNull;

    private static Calculator calculatorStatic;

    @BeforeAll
    static void setUpAll() {
        calculatorStatic = new Calculator();
        System.out.println("@BeforeAll -> setUpAll()");
    }

    @AfterAll
    static void tearDownAll() {
        calculatorStatic = null;
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("@BeforeEach -> setUp()");
    }

    @AfterEach
    void tearDown() {
        calculator = null;
        System.out.println("@AfterEach -> tearDown()");
    }

    @Test
    @DisplayName("Validar que sea nulo el Objeto")
    void calculatorNotNull() {
        assertNotNull(calculatorStatic, UtilMessage.calculatorNotNull);
        assertNotNull(calculator, UtilMessage.calculatorNotNull);
    }

    @Test
    @DisplayName("Validar que el objeto sea null")
    void calculatorNullTest() {
        assertNull(calculatorNull, UtilMessage.calculatorNullTest);
    }

    @Test
    @DisplayName("Sumar -> 10 + 20")
    void addAssertTest() {
        assertEquals(30, calculator.add(10,20), UtilMessage.addAssertTest);
    }

    @Test
    @DisplayName("Prueba de assertTrue")
    void assertTypeTest() {
        assertTrue(1 == 1);
        //assertEquals(1,1.8, 0.5, UtilMessage.assertTypeTest);
    }

    @Test
    void addValidInputExpectedTest() {
        assertEquals(11, calculator.add(7, 4));
    }
    @Test
    void subtractValidInputExpectedTest() {
        assertEquals(11, calculator.subtract(15, 4));
    }

    @Test
    void subtractValidInputNegativeResultTest() {
        assertEquals(-10, calculator.subtract(10, 20));
    }

    @Test
    void divideValidInputResultTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    void divideInvalidInputResultTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    void divideInvalidInputExpectedExceptionTest() {
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0), "No es posible dividir por cero");
    }

    @Test
    @Disabled("Esta es la etiqueta para deshabilitar los test ")
    void disabledTest() {
        System.out.println("Esto no se debe estar mostrando");
    }

    @Test
    void addAssertAllTest() {
        assertAll(
                () -> assertEquals(30, calculator.add(10,20)),
                () -> assertEquals(20, calculator.add(10, 10)),
                () -> assertEquals(2, calculator.add( 1, 1))
        );
    }

    @Nested
    class AddTest{
        @Test
        void addPositiveTest() {
            assertEquals(30, calculator.add(10, 20));
        }

        @Test
        void addNegativeTest() {
            assertEquals(-30, calculator.add(-15, -15));
        }

        @Test
        void addZeroTest() {
            assertEquals(0, calculator.add(0, 0));
        }
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    void addParameterizedTest(int a, int b, int sum) {
        assertEquals(sum, calculator.add(a, b));
    }

    private static Stream<Arguments> addProviderData() {
        return Stream.of(
                Arguments.of(6,2,8),
                Arguments.of(-6,-2,-8),
                Arguments.of(6,-2,4),
                Arguments.of(-6,2,-4),
                Arguments.of(6,0,6)
        );
    }

    @Test
    void timeOutTest() {
        assertTimeout(Duration.ofMillis(10001), () -> {
            calculator.longTaskOperation();
        });
    }
}