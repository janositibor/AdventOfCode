package tzjanosi.y2024.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulParametersExtractor {
    private String inputString;
    private List<List<Integer>> mulParameters=new ArrayList<>();
    private long resultOfMultiplication;

    public MulParametersExtractor(String inputString) {
        this.inputString = inputString;
    }

    public void calculateResultForAllParameters(){
        extractParametersFromString(inputString);
        calculateResult();
    }
    public void calculateResultForEnabledParameters(){
        extractEnabledStrings(inputString);
        calculateResult();
    }

    private void calculateResult(){
        long result=0;
        for (List<Integer> parameters:mulParameters) {
            result+=(parameters.get(0)*parameters.get(1));
        }
        resultOfMultiplication+=result;
    }


    private void extractEnabledStrings(String input){
        String stop="don't()";
        String start="do()";
        String stringToCheck = input.indexOf(start) == 0 ? input.substring(start.length()) : input;
        int stopLocation = stringToCheck.indexOf(stop);
        stopLocation = (stopLocation > 0 ? stopLocation : stringToCheck.length());
        String enabled = stringToCheck.substring(0, stopLocation);
        extractParametersFromString(enabled);
        int startLocation = stringToCheck.indexOf(start, stopLocation);
        if(startLocation>0){
            String needMoreProcessing = stringToCheck.substring(startLocation);
            extractEnabledStrings(needMoreProcessing);
        }
    }

    private void extractParametersFromString(String input){
        Pattern myPattern = Pattern.compile("mul\\([0-9]{1,3}+,[0-9]{1,3}+\\)");
        Matcher m = myPattern.matcher(input);
        while (m.find()) {
            String s = m.group(0);
            mulParameters.add(extractNumbersFromString(s));
        }
    }
    private List<Integer> extractNumbersFromString(String input){
        int locationOfComa=input.indexOf(',');
        int number1=Integer.parseInt(input.substring(4,locationOfComa));
        int number2=Integer.parseInt(input.substring(locationOfComa+1,input.length()-1));
        return List.of(number1,number2);
    }

    public List<List<Integer>> getMulParameters() {
        return mulParameters;
    }

    public long getResultOfMultiplication() {
        return resultOfMultiplication;
    }
}
