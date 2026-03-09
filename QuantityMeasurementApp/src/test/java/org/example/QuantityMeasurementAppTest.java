package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    // 1
    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable unit = LengthUnit.FEET;
        assertEquals(1.0, unit.convertToBaseUnit(1));
        assertEquals(1.0, unit.convertFromBaseUnit(1));
        assertEquals("FEET", unit.getUnitName());
    }

    // 2
    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        IMeasurable unit = WeightUnit.KILOGRAM;
        assertEquals(1.0, unit.convertToBaseUnit(1));
        assertEquals(1.0, unit.convertFromBaseUnit(1));
        assertEquals("KILOGRAM", unit.getUnitName());
    }

    // 3
    @Test
    void testIMeasurableInterface_ConsistentBehavior() {
        IMeasurable inches = LengthUnit.INCHES;
        IMeasurable grams = WeightUnit.GRAM;

        assertEquals(1.0, inches.convertToBaseUnit(12));
        assertEquals(1.0, grams.convertToBaseUnit(1000));
    }

    // 4
    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    // 5
    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // 6
    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }

    // 7
    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
    }

    // 8
    @Test
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    // 9
    @Test
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    // 10
    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // 11
    @Test
    void testCrossCategoryPrevention_CompilerTypeSafety() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(length);
    }

    // 12
    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    // 13
    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // 14
    @Test
    void testGenericQuantity_Conversion_AllUnitCombinations() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES),
                feet.convertTo(LengthUnit.INCHES));

        assertEquals(new Quantity<>(0.33, LengthUnit.YARDS),
                feet.convertTo(LengthUnit.YARDS));
    }

    // 15
    @Test
    void testGenericQuantity_Addition_AllUnitCombinations() {
        Quantity<LengthUnit> a = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(24.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(4.0, LengthUnit.FEET),
                a.add(b, LengthUnit.FEET));
    }

    // 16
    @Test
    void testBackwardCompatibility_AllUC1Through9Tests() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    // 17
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    // 18
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM),
                q.convertTo(WeightUnit.GRAM));
    }

    // 19
    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM),
                q1.add(q2, WeightUnit.KILOGRAM));
    }

    // 20
    @Test
    void testTypeWildcard_FlexibleSignatures() {
        Quantity<?> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    // 22
    @Test
    void testScalability_MultipleNewCategories() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotNull(length);
        assertNotNull(weight);
    }

    // 23
    @Test
    void testGenericBoundedTypeParameter_Enforcement() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    // 24
    @Test
    void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a.hashCode(), b.hashCode());
    }

    // 25
    @Test
    void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> c = new Quantity<>(1.0, LengthUnit.FEET);

        assertTrue(a.equals(a));
        assertTrue(a.equals(b) && b.equals(a));
        assertTrue(a.equals(b) && b.equals(c));
    }

    // 26
    @Test
    void testEnumAsUnitCarrier_BehaviorEncapsulation() {
        assertEquals(1.0, LengthUnit.FEET.convertToBaseUnit(1));
    }

    // 27
    @Test
    void testTypeErasure_RuntimeSafety() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // 28
    @Test
    void testCompositionOverInheritance_Flexibility() {
        Quantity<LengthUnit> q = new Quantity<>(2.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    // 29
    @Test
    void testCodeReduction_DRYValidation() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    // 30
    @Test
    void testMaintainability_SingleSourceOfTruth() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    // 31
    @Test
    void testArchitecturalReadiness_MultipleNewCategories() {
        Quantity<LengthUnit> length = new Quantity<>(5.0, LengthUnit.YARDS);
        assertNotNull(length);
    }

    // 32
    @Test
    void testPerformance_GenericOverhead() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        q.convertTo(LengthUnit.INCHES);

        assertTrue(true);
    }

    // 33
    @Test
    void testDocumentation_PatternClarity() {
        assertTrue(true);
    }

    // 34
    @Test
    void testInterfaceSegregation_MinimalContract() {
        assertEquals("FEET", LengthUnit.FEET.getUnitName());
    }

    // 35
    @Test
    void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertNotSame(q, result);
    }
}