// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Validate command-line arguments
        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please specify a valid option.");
            return;
        }

        String command = args[0];

        // Load data from employees.txt based on command
        if (command.equals("l")) {
            // List all employees
            System.out.println("Loading data...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] employees = line.split(",");
                    for (String emp : employees) {
                        System.out.println(emp.trim());
                    }
                } else {
                    System.out.println("No employee data found.");
                }
            } catch (IOException e) {
                System.out.println("Error reading employee data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            // Show a random employee
            System.out.println("Loading data...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] employees = line.split(",");
                    Random rand = new Random();
                    int index = rand.nextInt(employees.length);
                    System.out.println("Random Employee: " + employees[index].trim());
                } else {
                    System.out.println("No employee data available.");
                }
            } catch (IOException e) {
                System.out.println("Error reading employee data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            // Add a new employee
            String newEmployee = command.substring(1).trim();
            System.out.println("Loading data...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
                writer.write(", " + newEmployee);
            } catch (IOException e) {
                System.out.println("Error adding employee: " + e.getMessage());
            }
            System.out.println("Employee added. Data Loaded.");

        } else if (command.startsWith("?")) {
            // Search for an employee
            String searchName = command.substring(1).trim();
            System.out.println("Loading data...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] employees = line.split(",");
                    boolean found = false;
                    for (String emp : employees) {
                        if (emp.trim().equalsIgnoreCase(searchName)) {
                            System.out.println("Employee found!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Employee not found.");
                    }
                } else {
                    System.out.println("No employee data found.");
                }
            } catch (IOException e) {
                System.out.println("Error searching employee: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {
            // Count total employees
            System.out.println("Loading data...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    System.out.println(employees.length + " employee(s) found.");
                } else {
                    System.out.println("No employees found.");
                }
            } catch (IOException e) {
                System.out.println("Error counting employees: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {
            // Update an employee (replace with "Updated")
            String nameToUpdate = command.substring(1).trim();
            System.out.println("Loading data...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] employees = line.split(",");
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].trim().equals(nameToUpdate)) {
                            employees[i] = "Updated";
                        }
                    }
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                        writer.write(String.join(",", employees));
                    }
                    System.out.println("Employee updated successfully.");
                } else {
                    System.out.println("No employee data found.");
                }
            } catch (IOException e) {
                System.out.println("Error updating employee: " + e.getMessage());
            }
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {
            // Delete an employee
            String nameToDelete = command.substring(1).trim();
            System.out.println("Loading data...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String line = reader.readLine();
                if (line != null) {
                    List<String> employees = new ArrayList<>(Arrays.asList(line.split(",")));
                    if (employees.removeIf(e -> e.trim().equalsIgnoreCase(nameToDelete))) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                            writer.write(String.join(",", employees));
                        }
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                } else {
                    System.out.println("No employee data found.");
                }
            } catch (IOException e) {
                System.out.println("Error deleting employee: " + e.getMessage());
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Error: Unsupported command \"" + command + "\".");
            System.out.println("Valid options: l, s, +name, ?name, c, uname, dname");
        }
    }
}
