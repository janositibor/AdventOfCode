package TZJanosi.y2024.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulParametersExtractor {
    private String inputString;
    private List<List<Integer>> mulParameters=new ArrayList<>();
    private long resultOfMultiplication;

    public MulParametersExtractor(String inputString) {
        this.inputString = inputString;
        extractParametersFromInputString();
        calculateResult();
    }

    private void calculateResult(){
        long result=0;
        for (List<Integer> parameters:mulParameters) {
            result+=(parameters.get(0)*parameters.get(1));
        }
        resultOfMultiplication=result;
    }

    private void extractParametersFromInputString(){
        Pattern MY_PATTERN = Pattern.compile("mul\\([0-9]{1,3}+,[0-9]{1,3}+\\)");
        Matcher m = MY_PATTERN.matcher(inputString);
        while (m.find()) {
            String s = m.group(0);
//            System.out.println(s);
            mulParameters.add(extractNumbersFromString(s));
        }
    }
    private List<Integer> extractNumbersFromString(String input){
        int locationOfComa=input.indexOf(',');
//        System.out.println(input.substring(4,locationOfComa));
//        System.out.println(input.substring(locationOfComa+1,input.length()-1));
        int number1=Integer.parseInt(input.substring(4,locationOfComa));
        int number2=Integer.parseInt(input.substring(locationOfComa+1,input.length()-1));
//        System.out.println(number1 + " * " +number2);
        return List.of(number1,number2);
    }

    public List<List<Integer>> getMulParameters() {
        return mulParameters;
    }

    public long getResultOfMultiplication() {
        return resultOfMultiplication;
    }
}
