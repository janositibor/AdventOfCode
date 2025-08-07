package tzjanosi.y2016.day14.part1;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class KeyGenerator {
    private String secretKey;
    private List<Integer> keys = new ArrayList<>();


    public KeyGenerator(String secretKey) {
        this.secretKey = secretKey;
    }

    public int foundKeys() {
        int index = 0;
        while (keys.size() < 64) {
            int key = findFirstKeyFromIndex(index);
            keys.add(key);
            index = key + 1;
        }
        return keys.get(63);
    }

    public String generateMd5HashForIndex(int index) {
        String password = secretKey + index;
        return DigestUtils.md5Hex(password).toLowerCase(Locale.US);
    }

    private Optional<String> containsTriple(String wordToCheck) {
        for (int i = 0; i < wordToCheck.length() - 2; i++) {
            if (wordToCheck.charAt(i) == wordToCheck.charAt(i + 1) && wordToCheck.charAt(i + 1) == wordToCheck.charAt(i + 2)) {
                return Optional.of(wordToCheck.substring(i, i + 1));
            }
        }
        return Optional.empty();
    }

    private boolean nextKeysContainsFive(int howManyKeys, int indexFrom, String letterToCheck) {
        String stringToCheck = letterToCheck.repeat(5);
        String md5;
        for (int i = indexFrom; i < indexFrom + howManyKeys; i++) {
//            if(indexFrom==40 && i==816){
//                System.out.println("debug");
//            }
            md5 = generateMd5HashForIndex(i);
            if (md5.contains(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    public int findFirstKeyFromIndex(int indexFrom) {
        int index = indexFrom;
        String md5;
        Optional<String> keyCandidate;
        do {
            md5 = generateMd5HashForIndex(index);
            keyCandidate = containsTriple(md5);
            index++;
//            if(keyCandidate.isPresent()){
//                System.out.println(index-1+": "+keyCandidate.get());
//            }
        } while (keyCandidate.isEmpty() || !nextKeysContainsFive(1000, index, keyCandidate.get()));

        return index - 1;
    }


}
