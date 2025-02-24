package model;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Polynomial {
    private final LinkedHashMap<Integer, Monomial> terms;

    public Polynomial() {
        this.terms = new LinkedHashMap<>();
    }

    public Polynomial(Polynomial polynomial) {
        this.terms = new LinkedHashMap<>(polynomial.terms);
    }

    public void addMonomial(Monomial monomial) {
        int exponent = monomial.getExponent();
        Monomial existingMonomial = terms.get(exponent);

        if (existingMonomial != null) {
            double newCoefficient = existingMonomial.getCoefficient() + monomial.getCoefficient();

            if (newCoefficient == 0) {
                terms.remove(exponent);
            } else {
                existingMonomial.setCoefficient(newCoefficient);
            }
        } else {
            terms.put(exponent, monomial);
        }
    }

    public Polynomial sortTermsByExponent() {
        LinkedHashMap<Integer, Monomial> sortedMap = terms.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        Polynomial sortedPolynomial = new Polynomial();
        sortedPolynomial.getTerms().putAll(sortedMap);

        return sortedPolynomial;
    }

    public LinkedHashMap<Integer, Monomial> getTerms() {
        return terms;
    }

    public int degree() {
        return terms.isEmpty() ? 0 : terms.keySet().stream().max(Integer::compareTo).orElse(0);
    }

    public void combineTermsWithSameExponents() {
        LinkedHashMap<Integer, Monomial> combinedTerms = new LinkedHashMap<>();

        for (Monomial monomial : terms.values()) {
            int exponent = monomial.getExponent();
            double coefficient = monomial.getCoefficient();

            if (combinedTerms.containsKey(exponent)) {
                Monomial existingMonomial = combinedTerms.get(exponent);
                double newCoefficient = existingMonomial.getCoefficient() + coefficient;
                existingMonomial.setCoefficient(newCoefficient);
            } else {
                combinedTerms.put(exponent, new Monomial(coefficient, exponent));
            }
        }

        terms.clear();
        terms.putAll(combinedTerms);
    }

    @Override
    public String toString() {
        if (terms.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean firstTerm = true;

        for (Monomial monomial : terms.values()) {
            double coefficient = monomial.getCoefficient();

            // Display the sign based on the coefficient
            if (coefficient > 0) {
                if (!firstTerm) {
                    stringBuilder.append(" + ");
                }
            } else if (coefficient < 0) {
                stringBuilder.append(" - ");
            }

            // Display the term without the sign if it's the first term
            stringBuilder.append(monomial.toStringWithoutSign());

            firstTerm = false;
        }

        return stringBuilder.toString();
    }

    public boolean isZero() {
        for (Monomial term : terms.values()) {
            if (term.getCoefficient() != 0.0) {
                return false; // If any coefficient is non-zero, the polynomial is not zero
            }
        }
        return true; // If all coefficients are zero, the polynomial is zero
    }
}