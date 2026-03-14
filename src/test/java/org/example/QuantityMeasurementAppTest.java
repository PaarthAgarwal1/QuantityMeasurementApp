package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 0.01;

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE).subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(120.0, LengthUnit.INCHES).subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE).subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(0.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(-2.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NonCommutative() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertNotEquals(a.subtract(b), b.subtract(a));
    }

    @Test
    void testSubtraction_WithLargeValues() {

        Quantity<WeightUnit> result = new Quantity<>(1e6, WeightUnit.KILOGRAM).subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));

        assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testSubtraction_WithSmallValues() {

        Quantity<LengthUnit> result = new Quantity<>(0.001, LengthUnit.FEET).subtract(new Quantity<>(0.0005, LengthUnit.FEET));

        assertEquals(new Quantity<>(0.0005, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NullOperand() {

        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit() {

        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(5.0, LengthUnit.FEET), null));
    }

    @Test
    void testSubtraction_CrossCategory() {

        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            length.subtract((Quantity) weight);
        });
    }

    @Test
    void testSubtraction_ChainedOperations() {

        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.FEET)).subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    // -------------------------
    // DIVISION TESTS
    // -------------------------

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {

        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {

        double result = new Quantity<>(10.0, VolumeUnit.LITRE).divide(new Quantity<>(5.0, VolumeUnit.LITRE));

        assertEquals(2.0, result);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {

        double result = new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result);
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {

        double result = new Quantity<>(2.0, WeightUnit.KILOGRAM).divide(new Quantity<>(2000.0, WeightUnit.GRAM));

        assertEquals(1.0, result);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {

        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result);
    }

    @Test
    void testDivision_RatioLessThanOne() {

        double result = new Quantity<>(5.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(0.5, result);
    }

    @Test
    void testDivision_RatioEqualToOne() {

        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(1.0, result);
    }

    @Test
    void testDivision_NonCommutative() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertNotEquals(a.divide(b), b.divide(a));
    }

    @Test
    void testDivision_ByZero() {

        assertThrows(ArithmeticException.class, () -> new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    void testDivision_NullOperand() {

        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(10.0, LengthUnit.FEET).divide(null));
    }

    @Test
    void testDivision_CrossCategory() {

        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            length.divide((Quantity) weight);
        });
    }

    // -------------------------
    // INTEGRATION
    // -------------------------

    @Test
    void testSubtractionAndDivision_Integration() {

        double result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.FEET)).divide(new Quantity<>(4.0, LengthUnit.FEET));

        assertEquals(2.0, result);
    }

    @Test
    void testSubtractionAddition_Inverse() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b).subtract(b);

        assertEquals(a, result);
    }

    @Test
    void testSubtraction_Immutability() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        a.subtract(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), b);
    }

    @Test
    void testDivision_Immutability() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        a.divide(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), b);
    }
}