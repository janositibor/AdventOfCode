package tzjanosi_temp.y2024.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    @Test
    void lineTest(){
        char[] inputLine={'o','X','M','A','S','A','M','X','o','o','X','X','M','A','S','o'};
        Line line=new Line(inputLine,"XMAS");
        assertEquals(3,line.getNumberOfKeywordInLine());
        char[] inputLine2={'X','M','A','S','A','M','X','o','o','X','X','M','A','S','o'};
        Line line2=new Line(inputLine2,"XMAS");
        assertEquals(3,line2.getNumberOfKeywordInLine());
    }

}