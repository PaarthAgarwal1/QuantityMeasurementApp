package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 =
                new QuantityMeasurementApp.Feet(1.0);

        assertEquals(f1, f2);
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 =
                new QuantityMeasurementApp.Feet(2.0);

        assertNotEquals(f1, f2);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(f1, null);
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);

        assertEquals(f1, f1);
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(f1, "Not a Feet Object");
    }
}
