package TZJanosi.y2015.day04;

import org.apache.commons.codec.digest.DigestUtils;

public class Miner {
    private String secretKey;
    private String prefix = "00000";

    public Miner(String secretKey, String prefix) {
        this.secretKey = secretKey;
        this.prefix = prefix;
    }

    public long findFirstMatch() {
        String md5Hash = "";
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
        String md5Hex = DigestUtils.md5Hex(password).toUpperCase();
        return md5Hex;
    }

}
