package strategy;

import hash.ChecksumCalculator;

import java.io.IOException;
import java.io.InputStream;

public interface CheckSumCalculatorStrategy {

    String calculate(InputStream is, ChecksumCalculator calculator) throws IOException;
}
