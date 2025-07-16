package tzjanosi_temp.y2024.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void initTest() {
        Calculator calculator = new Calculator(16, 1, 3);
        assertThat(calculator.getRegisterA()).isEqualTo(16);
        assertThat(calculator.getRegisterB()).isEqualTo(1);
        assertThat(calculator.getRegisterC()).isEqualTo(3);
    }

    @Test
    void setProgramTest(){
        Calculator calculator=new Calculator(2,0,0);
        calculator.setProgram("0,1,2,3");
        assertThat(calculator.getProgram())
                .hasSize(2)
                .containsExactly(List.of(0,1),List.of(2,3));
    }
    @Test
    void invalidProgramTest(){
        Calculator calculator=new Calculator(2,0,0);
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,()->calculator.setProgram("0,1,2"));
        assertThat(exception)
                .hasMessage("The length of program is invalid: 3");
    }

    @Test
    void operation0Test(){
        Calculator calculator;
        calculator=new Calculator(16,1,3);
        calculator.setProgram("0,0");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(16);
        calculator.setProgram("0,0,0,1");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(8);

        calculator=new Calculator(17,1,3);
        calculator.setProgram("0,0");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(17);
        calculator.setProgram("0,0,0,1");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(8);
        calculator.setProgram("0,0,0,5");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(4);

        calculator=new Calculator(24,1,3);
        calculator.setProgram("0,0,0,6");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(3);

        calculator=new Calculator(23,1,3);
        calculator.setProgram("0,6");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(2);
    }

    @Test
    void operation1Test(){
        Calculator calculator;
        calculator=new Calculator(16,29,3);
        calculator.setProgram("1,7");
        calculator.exec();
        assertThat(calculator.getRegisterB()).isEqualTo(26);
    }
    @Test
    void operation2Test(){
        Calculator calculator;
        calculator=new Calculator(8,29,9);
        calculator.setProgram("2,5");
        calculator.exec();
        assertThat(calculator.getRegisterB()).isEqualTo(5);
        calculator.setProgram("2,0");
        calculator.exec();
        assertThat(calculator.getRegisterB()).isEqualTo(0);
        calculator.setProgram("2,2");
        calculator.exec();
        assertThat(calculator.getRegisterB()).isEqualTo(2);

        calculator.setProgram("2,6");
        calculator.exec();
        assertThat(calculator.getRegisterB()).isEqualTo(1);
    }
    @Test
    void operation4Test(){
        Calculator calculator;
        calculator=new Calculator(0,2024,43690);
        calculator.setProgram("4,0");
        calculator.exec();
        assertThat(calculator.getRegisterB()).isEqualTo(44354);
    }
    @Test
    void operation5Test(){
        Calculator calculator;
        calculator=new Calculator(10,29,9);
        calculator.setProgram("5,0,5,1,5,4");
        calculator.exec();
        assertThat(calculator.getOutput()).isEqualTo("0,1,2");
    }
    @Test
    void operationTo5Test(){
        Calculator calculator;
        calculator=new Calculator(2024,0,0);
        calculator.setProgram("0,1,5,4,3,0");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(0);
        assertThat(calculator.getOutput()).isEqualTo("4,2,5,6,7,7,7,7,3,1,0");

        calculator=new Calculator(729,0,0);
        calculator.setProgram("0,1,5,4,3,0");
        calculator.exec();
        assertThat(calculator.getRegisterA()).isEqualTo(0);
        assertThat(calculator.getOutput()).isEqualTo("4,6,3,5,6,3,5,2,1,0");
    }
    @Test
    void problemDataTest(){
        Calculator calculator;
        calculator=new Calculator(63687530,0,0);
        calculator.setProgram("2,4,1,3,7,5,0,3,1,5,4,1,5,5,3,0");
        calculator.exec();
        assertThat(calculator.getOutput()).isEqualTo("1,6,7,4,3,0,5,0,6");
    }

    @Test
    void part2SampleDataTest(){
        Calculator calculator;
        calculator=new Calculator(117440,0,0);
        String program="0,3,5,4,3,0";
        calculator.setProgram(program);
        calculator.exec();
        assertThat(calculator.getOutput()).isEqualTo(program);
    }

    @Test
    void part2SampleDataVer2Test(){
        Calculator calculator;
        calculator=new Calculator(0,0,0);
        long identity=calculator.findIdentity("0,3,5,4,3,0",0,1);
        assertEquals(117440,identity);
    }
    @Test
    void part2ProblemDataTest(){
        Calculator calculator;
        calculator=new Calculator(216148338630253L,0,0);
        String program="2,4,1,3,7,5,0,3,1,5,4,1,5,5,3,0";
        calculator.setProgram(program);
        calculator.exec();
        assertEquals(program,calculator.getOutput());
    }


    @Test
    void part2ProblemDataVer2Test(){
        Calculator calculator;
        calculator=new Calculator(0,0,0);
        long identity=calculator.findIdentity("2,4,1,3,7,5,0,3,1,5,4,1,5,5,3,0",0,1);
        assertEquals(216148338630253L,identity);
    }

}