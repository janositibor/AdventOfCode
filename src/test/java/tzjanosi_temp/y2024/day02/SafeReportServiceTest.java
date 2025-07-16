package tzjanosi_temp.y2024.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafeReportServiceTest {
    @Test
    void numberOfSafeReportsTest(){
        SafeReportService safeReportService;
        safeReportService=new SafeReportService("testInputReports.txt");
        assertEquals(2,safeReportService.getNumberOfSafeReports());

        safeReportService=new SafeReportService("testDataInput.txt");
        assertEquals(321,safeReportService.getNumberOfSafeReports());
    }
    @Test
    void numberOfDampenerSafeReportsTest(){
        SafeReportService safeReportService;
        safeReportService=new SafeReportService("testInputReports.txt");
        assertEquals(4,safeReportService.getNumberOfDampenerSafeReports());

        safeReportService=new SafeReportService("testDataInput.txt");
        assertEquals(386,safeReportService.getNumberOfDampenerSafeReports());
    }

}