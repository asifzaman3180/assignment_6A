// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a valid argument.");
            return;
        }

        String command = args[0];

        try {
            if (command.equals("l")) {
                // List all employees
                System.out.println("Loading data...");
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] employees = line.split(",");
                        for (String emp : employees) {
                            System.out.println(emp.trim());
                        }
                    }
                }
                System.out.println("Data Loaded.");

            } else if (command.equals("s")) {
                // Show a random employee
                System.out.println("Loading data...");
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] employees = line.split(",");
                        Random rand = new Random();
                        String randomEmp = employees[rand.nextInt(employees.length)].trim();
                        System.out.println("Random Employee: " + randomEmp);
                    }
                }
                System.out.println("Data Loaded.");

            } else if (command.startsWith("+")) {
                // Add a new employee
                String newEmployee = command.substring(1);
                System.out.println("Loading data...");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                    writer.write(", " + newEmployee);
                }
                System.out.println("Employee added successfully.");
                System.out.println("Data Loaded.");

            } else if (command.startsWith("?")) {
                // Search for an employee
                String searchName = command.substring(1);
                System.out.println("Loading data...");
                boolean found = false;
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] employees = line.split(",");
                        for (String emp : employees) {
                            if (emp.trim().equalsIgnoreCase(searchName)) {
                                System.out.println("Employee found!");
                                found = true;
                                break;
                            }
                        }
                    }
                }
                if (!found) {
                    System.out.println("Employee not found.");
                }
                System.out.println("Data Loaded.");

            } else if (command.equals("c")) {
                // Count number of employees
                System.out.println("Loading data...");
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] employees = line.split(",");
                        System.out.println(employees.length + " employee(s) found.");
                    }
                }
                System.out.println("Data Loaded.");

            } else if (command.startsWith("u")) {
                // Update employee name
                String nameToUpdate = command.substring(1);
                System.out.println("Loading data...");
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] employees = line.split(",");
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i].trim().equalsIgnoreCase(nameToUpdate)) {
                                employees[i] = "Updated";
                            }
                        }
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                            writer.write(String.join(",", employees));
                        }
                    }
                }
                System.out.println("Data Updated.");

            } else if (command.startsWith("d")) {
                // Delete an employee
                String nameToDelete = command.substring(1);
                System.out.println("Loading data...");
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line = reader.readLine();
                    if (line != null) {
                        List<String> employees = new ArrayList<>();
                        for (String emp : line.split(",")) {
                            if (!emp.trim().equalsIgnoreCase(nameToDelete)) {
                                employees.add(emp.trim());
                            }
                        }
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                            writer.write(String.join(", ", employees));
                        }
                    }
                }
                System.out.println("Data Deleted.");

            } else {
                System.out.println("Invalid command.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
