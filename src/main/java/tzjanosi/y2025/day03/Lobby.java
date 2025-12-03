package tzjanosi.y2025.day03;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private List<Bank> banks = new ArrayList<>();

    public Lobby(List<String> input) {
        processInput(input);
    }

    public int calculate() {
        return banks.stream()
                .mapToInt(Bank::calculateValue)
                .sum();
    }

    public long calculateMaxValueByDigits(int digits) {
        return banks.stream()
                .mapToLong(b -> b.calculateMaxValueByDigits(digits))
                .sum();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            Bank bank = new Bank(input.get(i));
            banks.add(bank);
        }
    }
}
