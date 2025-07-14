package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);

        Pattern numberPattern = Pattern.compile("(\\d+(?:\\s+\\d+)+)$");

        if (dateFromLocal.isAfter(dateToLocal)) {
            return "Invalid date range";
        }

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String workerName : data) {
                String datePart = workerName.split(" ")[0];
                LocalDate lineDate = LocalDate.parse(datePart, formatter);

                String[] parts = workerName.split(" ");
                String employeeName = parts[1];
                if (employeeName.equals(name)) {
                    if ((lineDate.isEqual(dateFromLocal) || lineDate.isAfter(dateFromLocal))
                            && (lineDate.isEqual(dateToLocal) || lineDate.isBefore(dateToLocal))) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        totalSalary += hours * rate;
                    }
                }
            }
            resultBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return resultBuilder.toString();
    }
}

