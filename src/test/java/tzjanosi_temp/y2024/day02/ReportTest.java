package tzjanosi_temp.y2024.day02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @Test
    void reportValidityTest(){
        Report report;
        report=new Report(Arrays.asList(7,6,4,2,1));
        assertEquals(true,report.isSafe());

        report=new Report(Arrays.asList(1,2,7,8,9));
        assertEquals(false,report.isSafe());

        report=new Report(Arrays.asList(9,7,6,2,1));
        assertEquals(false,report.isSafe());

        report=new Report(Arrays.asList(1,3,2,4,5));
        assertEquals(false,report.isSafe());

        report=new Report(Arrays.asList(8,6,4,4,1));
        assertEquals(false,report.isSafe());

        report=new Report(Arrays.asList(1,3,6,7,9));
        assertEquals(true,report.isSafe());
    }

    @Test
    void reportDampenerValidityTest(){
        Report report;
        report=new Report(Arrays.asList(7,6,4,2,1));
        assertEquals(true,report.isDampenerSafe());

        report=new Report(Arrays.asList(1,2,7,8,9));
        assertEquals(false,report.isDampenerSafe());

        report=new Report(Arrays.asList(9,7,6,2,1));
        assertEquals(false,report.isDampenerSafe());

        report=new Report(Arrays.asList(1,3,2,4,5));
        assertEquals(true,report.isDampenerSafe());

        report=new Report(Arrays.asList(8,6,4,4,1));
        assertEquals(true,report.isDampenerSafe());

        report=new Report(Arrays.asList(1,3,6,7,9));
        assertEquals(true,report.isDampenerSafe());
    }


}