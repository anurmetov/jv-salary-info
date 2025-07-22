package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();

        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        int[] salaryPerEmployee = new int[names.length];
        // Get each employee and check if its date is valid
        for (String date : data) {
            String[] parts = date.split(" ");
            LocalDate employeeDate = LocalDate.parse(parts[0], DATE_TIME_FORMATTER);
            if (employeeDate.isAfter(from) && employeeDate.isBefore(to)) {
                // Iterating through names to get each salary
                for (int j = 0; j < names.length; j++) {
                    if (parts[1].equals(names[j])) {
                        salaryPerEmployee[j] += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    }
                }
            }
        }

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < salaryPerEmployee.length; i++) {
            result.append(names[i]).append(" - ").append(salaryPerEmployee[i]).append(System.lineSeparator());
        }

        return result.toString();
    }
}
