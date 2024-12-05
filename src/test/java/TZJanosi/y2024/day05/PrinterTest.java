package TZJanosi.y2024.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    @Test
    void testCreatePrinter(){
        ReadData readData=new ReadData();
        Printer printer=new Printer(readData.readRestriction("testRestrictions.txt"),readData.readPpageOrders("testPageOrders.txt"));
        List<Integer[]> restrictions=printer.getRestrictions();
        List<List<Integer>> pageOrders=printer.getPageOrders();
//        System.out.println(restrictions);
        assertEquals(21,restrictions.size());
        assertEquals(47,restrictions.get(0)[0]);
        assertEquals(53,restrictions.get(0)[1]);
        assertEquals(61,restrictions.get(10)[0]);
        assertEquals(53,restrictions.get(10)[1]);
        assertEquals(53,restrictions.get(restrictions.size()-1)[0]);
        assertEquals(13,restrictions.get(restrictions.size()-1)[1]);

//        System.out.println(pageOrders);
        assertEquals(6,pageOrders.size());
        assertThat(pageOrders.get(0)).containsExactly(75,47,61,53,29);
        assertThat(pageOrders.get(2)).containsExactly(75,29,13);
        assertThat(pageOrders.get(pageOrders.size()-1)).containsExactly(97,13,75,29,47);
    }
    @Test
    void findValidPageOrdersTest(){
        ReadData readData=new ReadData();
        Printer printer=new Printer(readData.readRestriction("testRestrictions.txt"),readData.readPpageOrders("testPageOrders.txt"));
        List<List<Integer>> validPageOrders=printer.getValidPageOrders();
//        System.out.println(validPageOrders);
        assertThat(validPageOrders).hasSize(3)
                .contains(List.of(75, 47, 61, 53, 29))
                .contains(List.of(97, 61, 53, 29, 13))
                .contains(List.of(75, 29, 13));
//        System.out.println(printer.getSumOfCentralElements());
        assertEquals(143,printer.getSumOfCentralElements());
    }
    @Test
    void findWithProblemDataTest(){
        ReadData readData=new ReadData();
        Printer printer=new Printer(readData.readRestriction("restrictions.txt"),readData.readPpageOrders("pageOrders.txt"));
//        System.out.println(printer.getSumOfCentralElements());
        assertEquals(5588,printer.getSumOfCentralElements());
    }

}