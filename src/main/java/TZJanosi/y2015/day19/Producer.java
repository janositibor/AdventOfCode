package TZJanosi.y2015.day19;

import java.util.*;

public class Producer {
    private Map<String, List<String>> replacements = new HashMap<>();
    private String basicMolecule;
    private Set<String> createdMolecules = new HashSet<>();

    public Producer(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    public int findNumberOfNewMolecules() {
        replace();
        return createdMolecules.size();
    }

    private void replace() {
        for (Map.Entry<String, List<String>> entry : replacements.entrySet()) {
            String old = entry.getKey();
            List<String> newParts = entry.getValue();
            for (int i = 0; i < newParts.size(); i++) {
                String newPart = newParts.get(i);
                int fromIndex = 0;
                while (basicMolecule.indexOf(old, fromIndex) > -1) {
                    int replaceFrom = basicMolecule.indexOf(old, fromIndex);
                    int replaceTo = replaceFrom + old.length();
                    String createdMolecule = createMolecule(replaceFrom, replaceTo, newPart);
                    createdMolecules.add(createdMolecule);
                    fromIndex = replaceTo;
                }
            }
        }
    }

    private String createMolecule(int replaceFrom, int replaceTo, String newPart) {
        StringBuilder output = new StringBuilder();
        if (replaceFrom > 0) {
            output.append(basicMolecule.substring(0, replaceFrom));
        }
        output.append(newPart);
        if (replaceTo < basicMolecule.length()) {
            output.append(basicMolecule.substring(replaceTo));
        }
        return output.toString();
    }

    private void processLine(String line) {
        if (line.length() > 0) {
            if (line.contains(" => ")) {
                createReplacementFromLine(line);
            } else {
                basicMolecule = line;
            }
        }
    }

    private void createReplacementFromLine(String line) {
        String[] words = line.split(" => ");
        addToReplacements(words[0], words[1]);
    }

    private void addToReplacements(String original, String newPart) {
        if (!replacements.keySet().contains(original)) {
            replacements.put(original, new ArrayList<>());
        }
        List<String> newParts = replacements.get(original);
        newParts.add(newPart);
    }

    public Map<String, List<String>> getReplacements() {
        return replacements;
    }

    public String getBasicMolecule() {
        return basicMolecule;
    }

    public Set<String> getCreatedMolecules() {
        return createdMolecules;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "replacements=" + replacements +
                ", basicMolecule='" + basicMolecule + '\'' +
                ", createdMolecules=" + createdMolecules +
                '}';
    }
}
