package tzjanosi_temp.y2024.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SafeReportService {
    private List<List<Integer>> inputList;
    private List<Report> listOfReports=new ArrayList<>();
    private ReadData readData;

    public SafeReportService(String name) {
        readData=new ReadData(name);
        inputList=readData.getList();
        convertNumbersIntoReports();
    }

    private void convertNumbersIntoReports(){
        listOfReports=inputList.stream().map(l->new Report(l)).toList();
    }

    public long getNumberOfSafeReports(){
        Map<Boolean, Long> numberOfSafeAndUnsafeReports=listOfReports.stream().collect(Collectors.partitioningBy(Report::isSafe,Collectors.counting()));
        return numberOfSafeAndUnsafeReports.get(true);
    }

    public long getNumberOfDampenerSafeReports(){
        Map<Boolean, Long> numberOfSafeAndUnsafeReports=listOfReports.stream().collect(Collectors.partitioningBy(Report::isDampenerSafe,Collectors.counting()));
        return numberOfSafeAndUnsafeReports.get(true);
    }
}
