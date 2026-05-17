package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<String> correctDateList = getCorrectDateList(data, dateFrom, dateTo);

        return getStringResult(names, correctDateList,  dateFrom, dateTo);
    }

    private String getStringResult (String[] names, List<String> correctDateList, String dateFrom, String dateTo) {
        String salaryInfo = "Report for period " + dateFrom + " - " + dateTo;
        for(String name : names) {
            int salary = 0;
            for(String dateElement : correctDateList) {
                String[] recordsElement = dateElement.split(" ");
                if(name.equals(recordsElement[1])) {
                    salary += Integer.parseInt(recordsElement[2]) * Integer.parseInt(recordsElement[3]);
                }
            }
            salaryInfo += "\n" + name + " - "  + salary;
        }
        return salaryInfo;
    }

    private List<String> getCorrectDateList (String[] data, String dateFrom, String dateTo) {
        List<String> resultList = new ArrayList<>();
        for(String record : data) {
            String[] dataElement = record.split(" ");
            LocalDate workDay = LocalDate.parse(dataElement[0], formatter);
            LocalDate startDate = LocalDate.parse(dateFrom, formatter);
            LocalDate endDate = LocalDate.parse(dateTo, formatter);
            if(!workDay.isBefore(startDate) && !workDay.isAfter(endDate)) {
                resultList.add(record);
            }
        }
        return resultList;
    }
}
