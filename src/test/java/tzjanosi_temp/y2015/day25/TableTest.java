package tzjanosi_temp.y2015.day25;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    @ParameterizedTest
    @MethodSource("argumentsForCalculateOrderOfHeaderTest")
    void calculateOrderOfHeaderTest(int row, int column, int value){
        Table table =new Table(row,column);
        assertEquals(value,table.getOrdinalOfHeader());
    }

    private static Stream<Arguments> argumentsForCalculateOrderOfHeaderTest(){
        return Stream.of(
                    Arguments.of(1,1,1),
                    Arguments.of(5,1,11),
                    Arguments.of(6,1,16)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForCalculateOrderOfCellTest")
    void calculateOrderOfCellTest(int row, int column, int value){
        Table table =new Table(row,column);
        assertEquals(value,table.getOrdinalOfCell());
    }

    private static Stream<Arguments> argumentsForCalculateOrderOfCellTest(){
        return Stream.of(
                Arguments.of(1,1,1),
                Arguments.of(1,2,3),
                Arguments.of(1,6,21),
                Arguments.of(2,4,14),
                Arguments.of(5,1,11),
                Arguments.of(5,2,17),
                Arguments.of(6,1,16)
        );
    }

    @Test
    void CellValueTest(){
        Table table =new Table(3010,3019);
        assertEquals(8997277,table.cellValue());
    }

}