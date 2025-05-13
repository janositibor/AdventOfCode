package TZJanosi.y2024.day22.part1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecretNumberGeneratorHandler {
    private SecretNumberGenerator secretNumberGenerator;

    public SecretNumberGeneratorHandler(int numberOfIterations) {
        this.secretNumberGenerator = new SecretNumberGenerator(0, numberOfIterations);
    }

    public long calculateSumOfFinalValues(List<Integer> initValues) {
        long output = 0;
        for (int i = 0; i < initValues.size(); i++) {
            secretNumberGenerator.setValue(initValues.get(i));
            output += secretNumberGenerator.createFinalValue();
        }
        return output;
    }
}
