package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 0.0001;

    // ---------- Equality Tests ----------

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

        assertFalse(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        Weight w1 = new Weight(1000.0, WeightUnit.GRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

    @Test
    void testEquality_NullComparison() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertFalse(w1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w1));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    @Test
    void testEquality_TransitiveProperty() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_ZeroValue() {
        Weight w1 = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(0.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_NegativeWeight() {
        Weight w1 = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(-1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_LargeWeightValue() {
        Weight w1 = new Weight(1000000.0, WeightUnit.GRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_SmallWeightValue() {
        Weight w1 = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    // ---------- Conversion Tests ----------

    @Test
    void testConversion_PoundToKilogram() {
        Weight w = new Weight(2.20462, WeightUnit.POUND);

        Weight result =new Weight (w.convertTo(WeightUnit.KILOGRAM),WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    void testConversion_KilogramToPound() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result =new Weight (w.convertTo(WeightUnit.POUND),WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 0.01);
    }

    @Test
    void testConversion_SameUnit() {
        Weight w = new Weight(5.0, WeightUnit.KILOGRAM);

        Weight result =new Weight (w.convertTo(WeightUnit.KILOGRAM),WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        Weight w = new Weight(0.0, WeightUnit.KILOGRAM);

        Weight result =new Weight (w.convertTo(WeightUnit.GRAM),WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        Weight w = new Weight(-1.0, WeightUnit.KILOGRAM);

        Weight result =new Weight (w.convertTo(WeightUnit.GRAM),WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        Weight w = new Weight(1.5, WeightUnit.KILOGRAM);
        double gramValue=w.convertTo(WeightUnit.GRAM);
        Weight gram =new Weight(gramValue,WeightUnit.GRAM);
        Weight result=new Weight(gram.convertTo(WeightUnit.KILOGRAM),WeightUnit.KILOGRAM);



        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ---------- Addition Tests ----------

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

        Weight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        Weight w1 = new Weight(2.20462, WeightUnit.POUND);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result = w1.add(w2);

        assertEquals(4.40924, result.getValue(), 0.01);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Kilogram() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {

        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);

        Weight r1 = a.add(b);
        Weight r2 = b.add(a);

        assertTrue(r1.equals(r2));
    }

    @Test
    void testAddition_WithZero() {

        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(0.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {

        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(-2000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_LargeValues() {

        Weight w1 = new Weight(1e6, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1e6, WeightUnit.KILOGRAM);

        Weight result = w1.add(w2);

        assertEquals(2e6, result.getValue(), EPSILON);
    }
}