package model;

public class Monomial{
    private double coefficient;
    private final int exponent;

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public String toString() {
        if (coefficient == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        if (exponent == 0 || coefficient != 1) {
            result.append(formatCoefficient(coefficient));
        }

        if (exponent != 0) {
            result.append("x");
            if (exponent != 1) {
                result.append("^").append(exponent);
            }
        }

        return result.toString();
    }

    public String toStringWithoutSign() {
        if (coefficient == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        if (exponent == 0 || Math.abs(coefficient) != 1) {
            result.append(formatCoefficient(Math.abs(coefficient)));  // Ensure positive value for fractions
        }

        if (exponent != 0) {
            result.append("x");
            if (exponent != 1) {
                result.append("^").append(exponent);
            }
        }

        return result.toString();
    }

    private String formatCoefficient(double coefficient) {
        if (coefficient % 1 == 0) {
            // If the coefficient is an integer, remove the decimal part
            return String.valueOf((int) coefficient);
        } else {
            return String.valueOf(coefficient);
        }
    }

    public void setCoefficient(double newCoefficient) {
        this.coefficient = newCoefficient;
    }
}