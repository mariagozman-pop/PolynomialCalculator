package logic;

import model.DivisionResult;
import model.Monomial;
import model.Polynomial;

public class MathematicalOperations {
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial sum = new Polynomial();

        for (Monomial monomial : polynomial1.getTerms().values()) {
            sum.addMonomial(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        for (Monomial monomial : polynomial2.getTerms().values()) {
            sum.addMonomial(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        return sum.sortTermsByExponent();
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial difference = new Polynomial();

        for (Monomial monomial : polynomial1.getTerms().values()) {
            difference.addMonomial(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        for (Monomial monomial : polynomial2.getTerms().values()) {
            difference.addMonomial(new Monomial(-monomial.getCoefficient(), monomial.getExponent()));
        }

        return difference.sortTermsByExponent();
    }

    public static Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial product = new Polynomial();

        for (Monomial monomial1 : polynomial1.getTerms().values()) {
            for (Monomial monomial2 : polynomial2.getTerms().values()) {
                double newCoefficient = monomial1.getCoefficient() * monomial2.getCoefficient();
                int newExponent = monomial1.getExponent() + monomial2.getExponent();
                Monomial newMonomial = new Monomial(newCoefficient, newExponent);
                product.addMonomial(newMonomial);
            }
        }

        return product.sortTermsByExponent();
    }

    public static DivisionResult divide(Polynomial dividend, Polynomial divisor) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial(dividend);

        while (remainder.degree() >= divisor.degree() && !remainder.isZero()) {
            Monomial leadingTermDividend = remainder.getTerms().values().iterator().next();
            Monomial leadingTermDivisor = divisor.getTerms().values().iterator().next();

            double coefficientQuotient = leadingTermDividend.getCoefficient() / leadingTermDivisor.getCoefficient();
            int expQuotient = leadingTermDividend.getExponent() - leadingTermDivisor.getExponent();

            Polynomial termQuotient = new Polynomial();
            termQuotient.addMonomial(new Monomial(coefficientQuotient, expQuotient));

            quotient = add(quotient, termQuotient);

            Polynomial termProduct = multiply(termQuotient, divisor);
            remainder = subtract(remainder, termProduct);
        }

        return new DivisionResult(quotient, remainder);
    }

    public static Polynomial derive(Polynomial polynomial) {
        Polynomial derivative = new Polynomial();
        for (Monomial monomial : polynomial.getTerms().values()) {
            if (monomial.getExponent() > 0) { // Only differentiate terms with exponents greater than 0
                double newCoefficient = monomial.getCoefficient() * monomial.getExponent();
                int newExponent = monomial.getExponent() - 1;
                Monomial newMonomial = new Monomial(newCoefficient, newExponent);
                derivative.addMonomial(newMonomial);
            }
        }
        return derivative;
    }

    public static Polynomial integrate(Polynomial polynomial) {
        Polynomial integral = new Polynomial();

        for (Monomial monomial : polynomial.getTerms().values()) {

            double newCoefficient = monomial.getCoefficient() /(monomial.getExponent()+1);
            int newExponent = monomial.getExponent() + 1;

            Monomial newMonomial = new Monomial(newCoefficient, newExponent);
            integral.addMonomial(newMonomial);
        }

        return integral.sortTermsByExponent();
    }

}