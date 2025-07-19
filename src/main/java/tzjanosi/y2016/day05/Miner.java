package tzjanosi.y2016.day05;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Locale;

public class Miner {
    private String secretKey;
    private String prefix = "00000";
    private String password = "";

    public Miner(String secretKey) {
        this.secretKey = secretKey;
    }

    public void buildPassWord() {
        int startFrom = 0;
        for (int i = 0; i < 8; i++) {
            int next = findNextMatch(startFrom);
            startFrom = next;
        }
    }

    public void buildPassWordPart2() {
        for (int i = 0; i < 8; i++) {
            findFirstMatch(i);
        }
    }

    public void findFirstMatch(int index) {
        String md5Hash;
        long numberToCheck = 0;
        String tempPrefix = prefix + index;
        do {
            numberToCheck++;
            md5Hash = generateMD5HashForANumber(numberToCheck);
        }
        while (!md5Hash.startsWith(tempPrefix));
        password += md5Hash.substring(6, 7);
    }

    private int findNextMatch(int startFrom) {
        String md5Hash;
        int numberToCheck = startFrom;
        do {
            md5Hash = generateMD5HashForANumber(numberToCheck);
            numberToCheck++;
        }
        while (!md5Hash.startsWith(prefix));
        password += md5Hash.substring(5, 6);
        return numberToCheck;
    }

    private String generateMD5HashForANumber(long input) {
        String password = secretKey + input;
        return DigestUtils.md5Hex(password).toLowerCase(Locale.US);
    }

    public String getPassword() {
        return password;
    }
}
