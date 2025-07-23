package tzjanosi.y2016.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Organizer {
    private List<Bot> bots = new ArrayList<>();
    private List<Integer> numbersToCompare;
    private long multiplicationFromOutput = 1;

    public Organizer(List<String> input, List<Integer> numbersToCompare) {
        this.numbersToCompare = numbersToCompare;
        initialize(input);
    }

    public int run(boolean part2) {
        Optional<Bot> optionalBot = getBotToProceed();
        while (optionalBot.isPresent()) {
            Bot bot = optionalBot.get();
            Optional<Bot> successor = findSuccessor();
            if (!part2 && successor.isPresent()) {
                return successor.get().getId();
            }
            proceedBot(bot);
            optionalBot = getBotToProceed();
        }
        return 0;
    }

    private void proceedBot(Bot bot) {
        int botIdLow = bot.getGiveLowTo();
        int botIdHigh = bot.getGiveHighTo();
        int lowValue = bot.getLow();
        int highValue = bot.getHigh();
        if (botIdLow >= 0) {
            Bot botLow = findBotById(botIdLow);
            botLow.receive(lowValue);
        } else {
            if (-3 <= botIdLow && botIdLow <= -1) {
                multiplicationFromOutput *= lowValue;
            }
        }
        if (botIdHigh >= 0) {
            Bot botHigh = findBotById(botIdHigh);
            botHigh.receive(highValue);
        } else {
            if (-3 <= botIdLow && botIdLow <= -1) {
                multiplicationFromOutput *= highValue;
            }
        }
        bot.erase();
    }

    private Optional<Bot> findSuccessor() {
        return bots.stream()
                .filter(b -> b.containsBoth(numbersToCompare))
                .findFirst();
    }

    private Optional<Bot> getBotToProceed() {
        return bots.stream()
                .filter(Bot::hasBoth)
                .findFirst();
    }

    private void initialize(List<String> input) {
        createBots(input);
        giveMicroChipsToBots(input);
    }

    private void giveMicroChipsToBots(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if ("value".equals(line.substring(0, 5))) {
                processLine(line);
            }
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        int value = Integer.parseInt(words[1]);
        int botId = Integer.parseInt(words[5]);
        Bot bot = findBotById(botId);
        bot.receive(value);
    }

    private void createBots(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if ("bot".equals(line.substring(0, 3))) {
                createBot(line);
            }
        }
    }

    private void createBot(String line) {
        String[] words = line.split(" ");
        int id = Integer.parseInt(words[1]);
        int giveLowTo = Integer.parseInt(words[6]);
        if ("output".equals(words[5])) {
            giveLowTo = (-1 * giveLowTo) - 1;
        }
        int giveHighTo = Integer.parseInt(words[11]);
        if ("output".equals(words[10])) {
            giveHighTo = (-1 * giveHighTo) - 1;
        }

        Bot bot = new Bot(id, giveLowTo, giveHighTo);
        bots.add(bot);
    }

    private Bot findBotById(int id) {
        return bots.stream()
                .filter(b -> b.isIdEqualTo(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("No bot found with id: %d", id)));
    }

    public long getMultiplicationFromOutput() {
        return multiplicationFromOutput;
    }
}
