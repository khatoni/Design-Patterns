package strategy;

import hash.ChecksumCalculator;
import hash.MD5Sum;

import java.io.IOException;
import java.io.InputStream;

public class MD5SumAlgorithm implements CheckSumCalculatorStrategy{



    @Override
    public String calculate(InputStream is, ChecksumCalculator calculator) throws IOException {
        return calculator.calculate(is);
    }
}
