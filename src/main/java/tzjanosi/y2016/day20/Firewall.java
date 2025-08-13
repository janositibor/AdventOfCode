package tzjanosi.y2016.day20;

import java.util.*;

public class Firewall {
    private List<Block> blocks = new ArrayList<>();

    public Firewall(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    public long findFirstFreeIP() {
        long ipToCheck = 0;
        OptionalLong result;
        do {
            result = findBlockedTo(ipToCheck);
            if (result.isPresent()) {
                ipToCheck = result.getAsLong() + 1;
            }
        }
        while (result.isPresent());
        return ipToCheck;
    }

    private OptionalLong findBlockedTo(long valueToCheck) {
        return blocks.stream()
                .filter(b -> b.isBlocked(valueToCheck))
                .mapToLong(Block::getTo)
                .max();
    }

    private void processLine(String line) {
        String[] words = line.split("-");
        blocks.add(new Block(Long.parseLong(words[0]), Long.parseLong(words[1])));
    }
}
