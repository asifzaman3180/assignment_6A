// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  l         - List all employees");
            System.out.println("  s         - Show a random employee");
            System.out.println("  +name     - Add an employee");
            System.out.println("  ?name     - Search for an employee");
            System.out.println("  c         - Count employees");
            System.out.println("  u<name>   - Update employee to 'Updated'");
            System.out.println("  d<name>   - Delete an employee");
            return;
        }

        String command = args[0];
        switch (command.charAt(0)) {
            case 'l' -> listEmployees();
            case 's' -> showRandomEmployee();
            case '+' -> addEmployee(command.substring(1));
            case '?' -> searchEmployee(command.substring(1));
            case 'c' -> countEmployees();
            case 'u' -> updateEmployee(command.substring(1));
            case 'd' -> deleteEmployee(command.substring(1));
            default -> System.out.println("Invalid command.");
        }
    }

    private static List<String> readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(line.split(",")));
        }
    }

    private static void writeEmployees(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.join(",", employees));
        }
    }

    private static void listEmployees() {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            employees.forEach(emp -> System.out.println(emp.trim()));
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // ✅ Fixed version
    private static void showRandomEmployee() {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                Random rand = new Random();
                String randomEmployee = employees.get(rand.nextInt(employees.size())).trim();
                System.out.println(randomEmployee);
            }
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void addEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            employees.add(name.trim());
            writeEmployees(employees);
            System.out.println("Employee added: " + name);
            System.out.println("Data Saved.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void searchEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            if (employees.stream().anyMatch(emp -> emp.trim().equalsIgnoreCase(name))) {
                System.out.println("Employee found!");
            } else {
                System.out.println("Employee not found.");
            }
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void countEmployees() {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            System.out.println(employees.size() + " employee(s) found.");
            System.out.println("Data Loaded.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void updateEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            boolean updated = false;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).trim().equalsIgnoreCase(name)) {
                    employees.set(i, "Updated");
                    updated = true;
                }
            }
            if (updated) {
                writeEmployees(employees);
                System.out.println("Employee updated.");
            } else {
                System.out.println("Employee not found.");
            }
            System.out.println("Data Updated.");
        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }

    private static void deleteEmployee(String name) {
        System.out.println("Loading data ...");
        try {
            List<String> employees = readEmployees();
            boolean removed = employees.removeIf(emp -> emp.trim().equalsIgnoreCase(name));
            if (removed) {
                writeEmployees(employees);
                System.out.println("Employee deleted: " + name);
            } else {
                System.out.println("Employee not found.");
            }
            System.out.println("Data Deleted.");
        } catch (IOException e) {
            System.err.println("Error deleting from file: " + e.getMessage());
        }
    }
}
