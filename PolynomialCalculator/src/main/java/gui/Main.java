package gui;

import logic.MathematicalOperations;
import model.DivisionResult;
import model.Monomial;
import model.Polynomial;

public class Main {
    public static void main(String[] args) {

        Polynomial polynomial1 = new Polynomial();
        polynomial1.addMonomial(new Monomial(3, 2));
        polynomial1.addMonomial(new Monomial(5, 1));
        polynomial1.addMonomial(new Monomial(7, 0));

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addMonomial(new Monomial(2, 2));
        polynomial2.addMonomial(new Monomial(4, 1));
        polynomial2.addMonomial(new Monomial(1, 0));

        System.out.println("Polynomial 1:" + polynomial1.toString());

        System.out.println("\nPolynomial 2:" + polynomial2.toString());

        // Test addition
        Polynomial sum = MathematicalOperations.add(polynomial1, polynomial2);
        System.out.println("\nAddition Result:" + sum.toString());

        // Test subtraction
        Polynomial difference = MathematicalOperations.subtract(polynomial2, polynomial1);
        System.out.println("\nSubtraction Result:" + difference.toString());

        // Test multiplication
        Polynomial product = MathematicalOperations.multiply(polynomial1, polynomial2);
        System.out.println("\nMultiplication Result:" + product.toString());

        // Test derivation
        Polynomial derivative = MathematicalOperations.derive(polynomial1);
        System.out.println("\nDerivation Result:" + derivative.toString());

        // Test integration
        Polynomial integral = MathematicalOperations.integrate(polynomial1);
        System.out.println("\nIntegration Result:" + integral.toString());

        // Create new polynomials
        Polynomial dividend = new Polynomial();
        //dividend.addMonomial(new Monomial(6, 3));
        dividend.addMonomial(new Monomial(1, 2));
        dividend.addMonomial(new Monomial(2, 1));
        dividend.addMonomial(new Monomial(1, 0));

        Polynomial divisor = new Polynomial();
        //divisor.addMonomial(new Monomial(2, 2));
        divisor.addMonomial(new Monomial(1, 1));
        divisor.addMonomial(new Monomial(1, 0));

        // Display polynomials
        System.out.println("\nDividend:" + dividend.toString());

        System.out.println("\nDivisor:" + divisor.toString());

        // Test division
        DivisionResult result = MathematicalOperations.divide(dividend, divisor);
        System.out.println("\nDivision Result:" + result.getQuotient().toString());
        System.out.println("Remainder:" + result.getRemainder().toString());
    }
}