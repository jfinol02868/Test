package junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTemperatureTest {

    private ConvertTemperature convertTemperature;

    @BeforeEach
    void setUp() {
        convertTemperature = new ConvertTemperature();
    }

    @AfterEach
    void tearDown() {
        convertTemperature = null;
    }

    @Test
    @DisplayName("Conversion de grados celsius a grados fahrenheit")
    void convertDegreesToFahrenheit() {
        assertEquals(33.8, convertTemperature.ConvertDegreesToFahrenheit(1), 0.01);
    }
}