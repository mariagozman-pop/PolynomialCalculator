package model;

public class DivisionResult {
    Polynomial quotient;
    Polynomial remainder;

    public DivisionResult(Polynomial quotient, Polynomial remainder){
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public Polynomial getQuotient(){
        return quotient;
    }

    public Polynomial getRemainder() {
        return remainder;
    }

    @Override
    public String toString() {
        return "Quotient: " + quotient.toString() + ", Remainder: " + remainder.toString();
    }
}