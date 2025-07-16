package tzjanosi_temp.y2015.day04;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Locale;

public class Miner {
    private String secretKey;
    private String prefix;

    public Miner(String secretKey, String prefix) {
        this.secretKey = secretKey;
        this.prefix = prefix;
    }

    public long findFirstMatch() {
        String md5Hash;
        long numberToCheck = 0;
        do {
            numberToCheck++;
            md5Hash = generateMD5HashForANumber(numberToCheck);
        }
        while (!md5Hash.startsWith(prefix));
        return numberToCheck;
    }

    private String generateMD5HashForANumber(long input) {
        String password = secretKey + input;
        return DigestUtils.md5Hex(password).toUpperCase(Locale.US);
    }

}
