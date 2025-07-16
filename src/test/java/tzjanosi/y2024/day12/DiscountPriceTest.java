package tzjanosi.y2024.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountPriceTest {
    @Test
    void DiscountPriceTest(){
        ReadData readData=new ReadData("testInput.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(80,service.calculateDiscountCost());
    }
    @Test
    void DiscountPrice2Test(){
        ReadData readData=new ReadData("testInput2.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(436,service.calculateDiscountCost());
    }
    @Test
    void DiscountPrice3Test(){
        ReadData readData=new ReadData("testInput4.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(236,service.calculateDiscountCost());
    }
    @Test
    void DiscountPrice4Test(){
        ReadData readData=new ReadData("testInput5.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(368,service.calculateDiscountCost());
    }
    @Test
    void DiscountPrice5Test(){
        ReadData readData=new ReadData("testInput3.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(1206,service.calculateDiscountCost());
    }
    @Test
    void DiscountPriceWithProblemDataTest(){
        ReadData readData=new ReadData("input.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(886364,service.calculateDiscountCost());
    }
    @Test
    void DiscountPriceWithProblemDataTestEditOK(){
        ReadData readData=new ReadData("input_editOK.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(560,service.calculateDiscountCost());
    }
    @Test
    void DiscountPriceWithProblemDataTestEditMiddle(){
        ReadData readData=new ReadData("input_editMiddle.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(44,service.calculateDiscountCost());
    }@Test
    void DiscountPriceWithProblemDataTestEditNOK(){
        ReadData readData=new ReadData("input_editNOK.txt");
        Service service=new Service(readData.getOutput());
        service.calculation();
        assertEquals(188,service.calculateDiscountCost());
    }
}
