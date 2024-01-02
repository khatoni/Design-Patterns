package flyweight;

import transformations.CensorTransformation;

import java.util.HashMap;
import java.util.Map;

public class CensorTransformationFactory {
    private static Map<String, CensorTransformation> recentWords = new HashMap<>();

    private static CensorTransformation getCensoredWordTransformationFromFlyweight(String censoredWord) {
        CensorTransformation word = null;
        if (recentWords.containsKey(censoredWord)) {
            word = recentWords.get(censoredWord);
        }
        if (word == null) {
            word = new CensorTransformation(censoredWord);
            recentWords.put(censoredWord, word);
        }
        return word;
    }

    public static CensorTransformation createCensoredWordTransformation(String wordToCensor) {
        if (wordToCensor == null) {
            throw new IllegalArgumentException("The parameter wordToCensor is null");
        }
        if (wordToCensor.length() <= 4) {
            return CensorTransformationFactory.getCensoredWordTransformationFromFlyweight(wordToCensor);
        } else {
            return new CensorTransformation(wordToCensor);
        }
    }
}
