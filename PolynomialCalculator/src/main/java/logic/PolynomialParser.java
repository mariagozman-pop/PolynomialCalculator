package logic;

import model.Monomial;
import model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialParser {

    public static Polynomial parse(String polynomialString) {
        Polynomial polynomial = new Polynomial();
        String[] monomialStrings = polynomialString.split("(?=[+-])");

        int position = 0; // Position counter
        for (String monomialStr : monomialStrings) {
            position += monomialStr.length(); // Increment position by the length of the monomial string

            Monomial monomial = parseMonomial(monomialStr.trim(), position);
            if (monomial == null) {
                throw new IllegalArgumentException("Input string does not match the form of a polynomial at position " + position + ".");
            }
            polynomial.addMonomial(monomial);

            position++; // Increment position for the operator
        }

        polynomial.combineTermsWithSameExponents(); // Combine terms with the same exponents

        return polynomial;
    }

    private static Monomial parseMonomial(String monomialString, int position) {
        double coefficient = 1.0; // Default coefficient
        int exponent = 1; // Default exponent

        // Check if the monomial starts with a '+' or '-'
        boolean negative = monomialString.startsWith("-");
        if (negative || monomialString.startsWith("+")) {
            monomialString = monomialString.substring(1); // Remove the sign
        }

        // Define the monomial pattern
        String monomialPattern = "([+-]?(\\d+\\.?\\d*)?\\*?x(\\^\\d+)?)|(\\d+)";
        // Match either a term with coefficient and exponent or an integer

        // Create a pattern object
        Pattern pattern = Pattern.compile(monomialPattern);

        // Create a matcher object
        Matcher matcher = pattern.matcher(monomialString);

        // Check if the monomial matches the pattern
        if (matcher.matches()) {
            if (matcher.group(4) != null) { // Integer case
                coefficient = Double.parseDouble(matcher.group(4));
                exponent = 0; // If it's just a number, exponent is 0
            } else { // Term case
                // Extract coefficient and exponent from the matched groups
                String coefStr = matcher.group(2);
                String expStr = matcher.group(3);

                // Parse coefficient
                if (coefStr != null && !coefStr.isEmpty()) {
                    coefficient = Double.parseDouble(coefStr);
                }

                // Parse exponent
                if (expStr != null && !expStr.isEmpty()) {
                    exponent = Integer.parseInt(expStr.substring(1)); // Exclude the '^' character
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid monomial format at position " + position + ": " + monomialString);
        }

        // Adjust coefficient for negative monomials
        if (negative) {
            coefficient = -coefficient;
        }

        return new Monomial(coefficient, exponent);
    }

    public static void main(String[] args) {
        Polynomial poly1 = PolynomialParser.parse("x");
        Polynomial poly2 = PolynomialParser.parse("x");

        System.out.println("Polynomial 1:");
        poly1 = poly1.sortTermsByExponent();
        System.out.println(poly1.toString());
        System.out.println("Polynomial 2:");
        System.out.println(poly2.toString());
        poly2 = poly2.sortTermsByExponent();

        // Add polynomials
        Polynomial sum = MathematicalOperations.add(poly1, poly2);

        System.out.println("Sum of polynomials:");
        System.out.println(sum.toString());
    }
}
