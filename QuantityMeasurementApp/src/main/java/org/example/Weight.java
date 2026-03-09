package org.example;

public class Weight {
    private double value;
    private WeightUnit unit;

    public Weight(double value,WeightUnit unit){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if(unit==null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value=value;
        this.unit=unit;
    }

    public double getValue(){
        return value;
    }

    public WeightUnit getUnit(){
        return unit;
    }

    //Convert to another unit
    public double convertTo(WeightUnit targetUnit){
        if(targetUnit==null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue=unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    //Addition
    public Weight add(Weight other){
        if(other==null){
            throw new IllegalArgumentException("Second operand cannot be null");
        }

        double thisBase=unit.convertToBaseUnit(value);
        double otherBase=other.unit.convertToBaseUnit(other.value);

        double sumBase=thisBase+otherBase;

        double resultValue=unit.convertFromBaseUnit(sumBase);
        return new Weight(resultValue,unit);
    }

    //Addition with explicit target unit
    public Weight add(Weight other,WeightUnit targetUnit){
        if(other==null){
            throw new IllegalArgumentException("Second operand cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double thisBase =unit.convertToBaseUnit(value);
        double otherBase =other.unit.convertToBaseUnit(other.value);

        double sumBase=thisBase+otherBase;

        double resultValue=targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(resultValue,targetUnit);
    }

    public boolean compare(Weight thatWeight){
        if(thatWeight==null){
            return false;
        }
        double epsilon=0.0001;

        double thisBase=unit.convertToBaseUnit(value);
        double thatBase=thatWeight.unit.convertToBaseUnit(thatWeight.value);

        return Math.abs(thatBase-thatBase)<epsilon;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Weight other = (Weight) obj;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        double epsilon = 0.000001;

        return Math.abs(thisBase - otherBase) < epsilon;
    }

    @Override
    public int hashCode(){
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

}
