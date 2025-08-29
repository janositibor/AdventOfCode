package tzjanosi.y2017.day01;

public class Captcha {
    private String inputString;

    public Captcha(String inputString) {
        this.inputString = inputString;
    }

    public int solveCaptcha() {
        int sum = 0;
        char[] chars = inputString.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                sum += chars[i - 1] - '0';
            }
        }
        if (chars[0] == chars[chars.length - 1]) {
            sum += chars[0] - '0';
        }
        return sum;
    }
}
