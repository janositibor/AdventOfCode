package TZJanosi.y2024.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrosswordTest {
    @Test
    void crosswordTest(){
        String keyword="XMAS";
        ReadData readData = new ReadData("testMatrix.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        Crossword crossword=new Crossword(inputMatrix,keyword);
        System.out.println(crossword.getNumberOfFoundKeyword());
        assertEquals(8,crossword.getNumberOfFoundKeyword());
    }
    @Test
    void crosswordWithProblemDataTest(){
        String keyword="XMAS";
        ReadData readData = new ReadData("inputCrossword.txt");
        char[][] inputMatrix=readData.getInputMatrix();
        Crossword crossword=new Crossword(inputMatrix,keyword);
        System.out.println(crossword.getNumberOfFoundKeyword());
//        assertEquals(8,crossword.getNumberOfFoundKeyword());
    }

}