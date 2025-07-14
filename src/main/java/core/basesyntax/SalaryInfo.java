package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
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

                if (workerName.contains(name)) {
                    if ((lineDate.isEqual(dateFromLocal) || lineDate.isAfter(dateFromLocal))
                            && (lineDate.isEqual(dateToLocal) || lineDate.isBefore(dateToLocal))) {
                        Matcher matcher = numberPattern.matcher(workerName);

                        if (matcher.find()) {
                            String[] numberStrings = matcher.group(1).split("\\s+");
                            int product = 1;

                            for (String num : numberStrings) {
                                product *= Integer.parseInt(num);
                            }
                            totalSalary += product;
                        }
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

