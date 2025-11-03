// File Name: EmployeeDatabase.java
import java.io.*;
import java.util.*;

public class EmployeeDatabase {

    private static final String FILE_NAME = "employees.txt";

    public static List<String> readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line = reader.readLine();
        reader.close();
        if (line == null || line.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(line.split(",")));
    }

    public static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        writer.write(String.join(",", employees));
        writer.close();
    }

    public static void addEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        employees.add(name.trim());
        writeEmployees(employees);
    }

    public static boolean searchEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        return employees.contains(name.trim());
    }

    public static void updateEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).trim().equals(name)) {
                employees.set(i, "Updated");
            }
        }
        writeEmployees(employees);
    }

    public static void deleteEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        employees.removeIf(emp -> emp.trim().equals(name));
        writeEmployees(employees);
    }

    public static int[] countWordsAndChars() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line = reader.readLine();
        reader.close();
        if (line == null) return new int[]{0, 0};
        String[] words = line.split(",");
        return new int[]{words.length, line.length()};
    }

    public static String getRandomEmployee() throws IOException {
        List<String> employees = readEmployees();
        if (employees.isEmpty()) return null;
        Random rand = new Random();
        return employees.get(rand.nextInt(employees.size())).trim();
    }
}
