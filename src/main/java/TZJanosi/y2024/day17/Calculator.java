package TZJanosi.y2024.day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Calculator {
    private int registerA;
    private int registerB;
    private int registerC;

    private String output="";

    private List<Integer> operatorsWithComboOperands=List.of(0,2,5,6,7);

    private Map<Integer, Consumer<Integer>> operators=new HashMap<>();
    private List<List<Integer>> program=new ArrayList<>();

    public Calculator(int registerA, int registerB, int registerC) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        setOperators();
    }

    public void setProgram(String input){
        program.clear();
        String[] numbersAsStrings=input.split(",");
        if(numbersAsStrings.length%2!=0){
            throw new IllegalArgumentException("The length of program is invalid: "+numbersAsStrings.length);
        }
        for (int i = 0; i < numbersAsStrings.length; i+=2) {
            int operatorCode=Integer.parseInt(numbersAsStrings[i]);
            int comboOperand=Integer.parseInt(numbersAsStrings[i+1]);
            if(operatorCode>7){
                throw new IllegalArgumentException("Invalid Operator Code: "+operatorCode);
            }
            program.add(List.of(operatorCode,comboOperand));
        }
    }

    private void setOperators() {
        operators.put(0,t->adv(t));
        operators.put(1,t->bxl(t));
        operators.put(2,t->bst(t));
        operators.put(4,t->bxc(t));
        operators.put(5,t->out(t));
        operators.put(6,t->bdv(t));
        operators.put(7,t->cdv(t));
    }
    public void exec(){
        for (int i = 0; i < program.size(); i++) {
            int operatorCode=program.get(i).get(0);
            int inputOperand=program.get(i).get(1);
            if(operatorCode!=3) {
                executeOperation(operatorCode, inputOperand);
            }
            else{
                if(registerA!=0){
                    i=inputOperand-1;
                }
            }
        }
    }

    private void executeOperation(int operatorCode,int inputOperand){
        boolean comboOperand=operatorsWithComboOperands.contains(operatorCode);
        Consumer<Integer> operator= operators.get(operatorCode);
        operator.accept(getOperand(inputOperand,comboOperand));
    }

    private int getOperand(int inputOperand, boolean comboOperand){
        if(!comboOperand){
            return inputOperand;
        }
        else {
            int output;
            if (inputOperand < 4) {
                output = inputOperand;
            } else {
                switch (inputOperand) {
                    case 4:
                        output = registerA;
                        break;
                    case 5:
                        output = registerB;
                        break;
                    case 6:
                        output = registerC;
                        break;
                    default:
                        throw new IllegalArgumentException("The provided Combo Operand is not valid: " + comboOperand);
                }
            }
            return output;
        }
    }
    private void adv(Integer operand){
        int result=registerA/((int) Math.pow(2, operand));
        registerA=result;
    }
    private void bxl(Integer operand){
        int result=registerB ^ operand;
        registerB=result;
    }
    private void bst(Integer operand){
        int result=operand%8;
        registerB=result;
    }
    private void bxc(Integer operand){
        int result=registerB ^ registerC;
        registerB=result;
    }
    private void out(Integer operand){
        int result=operand%8;
        concatenateToOutput(result);
    }
    private void bdv(Integer operand){
        int result=registerA/((int) Math.pow(2, operand));
        registerB=result;
    }
    private void cdv(Integer operand){
        int result=registerA/((int) Math.pow(2, operand));
        registerC=result;
    }


    private void concatenateToOutput(int result) {
        if(!output.isEmpty()){
            output+=",";
        }
        output+=result;
    }


    public int getRegisterA() {
        return registerA;
    }

    public int getRegisterB() {
        return registerB;
    }

    public int getRegisterC() {
        return registerC;
    }

    public String getOutput() {
        return output;
    }

    public List<List<Integer>> getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "registerA=" + registerA +
                ", registerB=" + registerB +
                ", registerC=" + registerC +
                ", output='" + output + '\'' +
                ", operators=" + operators +
                '}';
    }
}
