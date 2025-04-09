package TZJanosi.y2024.day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Calculator {
    private long registerA;
    private long registerB;
    private long registerC;
    private String output="";
    private List<Integer> operatorsWithComboOperands=List.of(0,2,5,6,7);
    private Map<Integer, Consumer<Long>> operators=new HashMap<>();
    private List<List<Integer>> program=new ArrayList<>();

    public Calculator(long registerA, int registerB, int registerC) {
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
    public long findIdentity(String program, long incomingNumber, int lengthOfIncomingNumber){
        int length=program.length()/2+1;
        if(length+1==lengthOfIncomingNumber){
            return incomingNumber;
        }
        long temp=8*incomingNumber;
        long output;

        for (int i = 0; i < 8; i++) {
            long numberToCheck=temp+i;
            if(checkNumber(program, numberToCheck,lengthOfIncomingNumber)) {
//                System.out.println("Found! Level: "+lengthOfIncomingNumber+" number: "+numberToCheck);
                output= findIdentity(program, numberToCheck, lengthOfIncomingNumber + 1);
                if(output>0) {
                    return output;
                }
            }
        }
        return -1;
    }

    private boolean checkNumber(String program, long incomingNumber, int lengthOfIncomingNumber) {
        initialize(incomingNumber);
        setProgram(program);
        exec();
        return output.equals(program.substring(program.length() - (2 * lengthOfIncomingNumber - 1)));
    }

    private void initialize(long n) {
        registerA= n;
        registerB=0;
        registerC=0;
        resetOutput();
    }

    public void exec(){
        for (int i = 0; i < program.size(); i++) {
            int operatorCode=program.get(i).get(0);
            int inputOperand=program.get(i).get(1);
            if(operatorCode!=3) {
                executeOperation(operatorCode, inputOperand);
//                System.out.println(this);
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
        Consumer<Long> operator= operators.get(operatorCode);
        operator.accept(getOperand(inputOperand,comboOperand));
    }

    private long getOperand(int inputOperand, boolean comboOperand){
        if(!comboOperand){
            return inputOperand;
        }
        else {
            long output;
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
    private void adv(Long operand){
        long result=registerA/((int) Math.pow(2, operand));
        registerA=result;
    }
    private void bxl(Long operand){
        long result=registerB ^ operand;
        registerB=result;
    }
    private void bst(Long operand){
        long result=operand%8;
        registerB=result;
    }
    private void bxc(Long operand){
        long result=registerB ^ registerC;
        registerB=result;
    }
    private void out(Long operand){
        long result=operand%8;
        concatenateToOutput(result);
    }
    private void bdv(Long operand){
        long result=registerA/((int) Math.pow(2, operand));
        registerB=result;
    }
    private void cdv(Long operand){
        long result=registerA/((int) Math.pow(2, operand));
        registerC=result;
    }


    private void concatenateToOutput(long result) {
        if(!output.isEmpty()){
            output+=",";
        }
        output+=result;
    }

    private void resetOutput(){
        output="";
    }


    public long getRegisterA() {
        return registerA;
    }

    public long getRegisterB() {
        return registerB;
    }

    public long getRegisterC() {
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
                ", program=" + program +
                '}';
    }
}