package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 0.001;

    // -------------------------
    // Equality Tests
    // -------------------------

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        assertTrue(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        assertTrue(new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> a = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> temp = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        assertTrue(temp.equals(temp));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS)));
    }

    // -------------------------
    // Conversion Tests
    // -------------------------

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        assertEquals(122.0,
                new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);

        assertEquals(-4.0,
                new Quantity<>(-20.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        assertEquals(50.0,
                new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);

        assertEquals(-20.0,
                new Quantity<>(-4.0, TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {
        Quantity<TemperatureUnit> c = new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        double result =
                c.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS)
                        .getValue();

        assertEquals(25.0, result, EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {
        Quantity<TemperatureUnit> temp = new Quantity<>(30.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = temp.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(30.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_ZeroValue() {
        assertEquals(32.0,
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_NegativeValues() {
        assertEquals(-40.0,
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_LargeValues() {
        assertEquals(1832.0,
                new Quantity<>(1000.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    // -------------------------
    // Unsupported Operations
    // -------------------------

    @Test
    void testTemperatureUnsupportedOperation_Add() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_ErrorMessage() {
        UnsupportedOperationException ex =
                assertThrows(UnsupportedOperationException.class,
                        () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                                .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));

        assertTrue(ex.getMessage().toLowerCase().contains("temperature"));
    }

    // -------------------------
    // Cross Category Checks
    // -------------------------

    @Test
    void testTemperatureVsLengthIncompatibility() {
        assertFalse(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100.0, LengthUnit.FEET)));
    }

    @Test
    void testTemperatureVsWeightIncompatibility() {
        assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(50.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testTemperatureVsVolumeIncompatibility() {
        assertFalse(new Quantity<>(25.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(25.0, VolumeUnit.LITRE)));
    }

    // -------------------------
    // Operation Support Tests
    // -------------------------

    @Test
    void testOperationSupportMethods_TemperatureUnitAddition() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_TemperatureUnitDivision() {
        assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_LengthUnitAddition() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_WeightUnitDivision() {
        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
    }

    // -------------------------
    // Interface Compatibility
    // -------------------------

    @Test
    void testIMeasurableInterface_Evolution_BackwardCompatible() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = length.add(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(15.0, result.getValue(), EPSILON);
    }

    // -------------------------
    // Temperature Unit Tests
    // -------------------------

    @Test
    void testTemperatureUnit_AllConstants() {
        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
    }

    @Test
    void testTemperatureEnumImplementsIMeasurable() {
        assertTrue(TemperatureUnit.CELSIUS instanceof IMeasurable);
    }

    // -------------------------
    // Null & Validation Tests
    // -------------------------

    @Test
    void testTemperatureNullOperandValidation_InComparison() {
        Quantity<TemperatureUnit> temp = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        assertFalse(temp.equals(null));
    }

    // -------------------------
    // Precision Tests
    // -------------------------

    @Test
    void testTemperatureConversionPrecision_Epsilon() {
        double result =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue();

        assertEquals(122.0, result, EPSILON);
    }

    @Test
    void testTemperatureConversionEdgeCase_VerySmallDifference() {
        double result =
                new Quantity<>(0.001, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue();

        assertNotEquals(0.0, result);
    }

    // -------------------------
    // Operation Validation
    // -------------------------

    @Test
    void testTemperatureValidateOperationSupport_MethodBehavior() {
        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS.validateOperationSupport("addition"));
    }

    // -------------------------
    // Generic Integration
    // -------------------------

    @Test
    void testTemperatureIntegrationWithGenericQuantity() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertEquals(25.0, temp.getValue());
        assertEquals(TemperatureUnit.CELSIUS, temp.getUnit());
    }

}