package strategy;

import hash.ChecksumCalculator;

import java.io.IOException;
import java.io.InputStream;

public class SHA256Algorithm implements CheckSumCalculatorStrategy{

    @Override
    public String calculate(InputStream is, ChecksumCalculator calculator) throws IOException {
        return calculator.calculate(is);
    }
}
