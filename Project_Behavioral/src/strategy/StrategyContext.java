package strategy;

import java.io.IOException;
import java.io.InputStream;

public class StrategyContext {

    private CheckSumCalculatorStrategy strategy;

    public StrategyContext(CheckSumCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CheckSumCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    /*public String calculateHash(InputStream is) throws IOException {
        return strategy.calculate(is);
    }*/
}
