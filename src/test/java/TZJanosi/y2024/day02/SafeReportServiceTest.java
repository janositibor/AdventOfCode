package TZJanosi.y2024.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafeReportServiceTest {
    @Test
    void safeReportServiceTest(){
        SafeReportService safeReportService;
        safeReportService=new SafeReportService("testInputReports.txt");
        assertEquals(2,safeReportService.getNumberOfSafeReports());

        safeReportService=new SafeReportService("testDataInput.txt");
        assertEquals(321,safeReportService.getNumberOfSafeReports());
    }

}