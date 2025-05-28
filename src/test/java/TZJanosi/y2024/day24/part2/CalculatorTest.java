package TZJanosi.y2024.day24.part2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private static LogicCircuitHandler handler;

    @BeforeAll
    static void init() {
        System.out.println("init lefutott");
        ReadData readData = new ReadData("input.txt");
        handler = new LogicCircuitHandler(readData.getOutput());
        handler.checkCircuit();
    }

    static Stream<Arguments> inputForCheckCalculator() {
        long maxInputValue = (long) Math.pow(2, 45) - 1;
        List<Arguments> outputList = new ArrayList<>(List.of(
                Arguments.arguments(0, 0, 0),
                Arguments.arguments(maxInputValue, maxInputValue, 2 * maxInputValue)
        ));
        Random random = new Random(1984);
        for (int i = 0; i < 10_000; i++) {
            long input1 = random.nextLong(maxInputValue + 1);
            long input2 = random.nextLong(maxInputValue + 1);
            long result = input1 + input2;
            outputList.add(Arguments.arguments(input1, input2, result));
        }
        return outputList.stream();
    }

    @ParameterizedTest
    @MethodSource("inputForCheckCalculator")
    void checkCalculator(double input1, double input2, long result) {
        assertEquals(result, handler.calculateSum(input1, input2));
    }
}
