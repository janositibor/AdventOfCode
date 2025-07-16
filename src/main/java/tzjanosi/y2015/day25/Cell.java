package tzjanosi.y2015.day25;

import java.math.BigInteger;

public class Cell {
    public static final BigInteger FIRST_NUMBER=new BigInteger("20151125");

    private static final BigInteger MULTIPLICATOR = new BigInteger("252533");
    private static final BigInteger DIVIDER = new BigInteger("33554393");

    private BigInteger previous;
    private BigInteger value;


    public Cell(BigInteger previous) {
        this.previous = previous;
        calculateValue();
    }

    private void calculateValue(){

        value = (MULTIPLICATOR.multiply(previous)).mod(DIVIDER);
    }

    public BigInteger getValue() {
        return value;
    }
}
