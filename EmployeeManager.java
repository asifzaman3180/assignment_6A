// File: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Argument Validation
        if (args.length != 1) {
            System.out.println(" Invalid number of arguments!");
            System.out.println(" Usage: java EmployeeManager [option]");
            System.out.println("Available options: l, s, +name, ?name, c, uName, dName");
            return;
        }

        try {
            if (args[0].equals("l")) { // Load All Employees
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }

                System.out.println("Data Loaded.");

            } else if (args[0].equals("s")) { // Show Random Employee
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                Random random = new Random();
                int randomIndex = random.nextInt(employees.size());
                System.out.println(employees.get(randomIndex).trim());

                System.out.println("Data Loaded.");

            } else if (args[0].contains("+")) { // Add Employee
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                String newEmployee = args[0].substring(1);
                employees.add(" " + newEmployee);
                writeEmployees(employees);

                System.out.println("Data Loaded.");

            } else if (args[0].contains("?")) { // Search Employee
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                String searchName = args[0].substring(1).trim();

                boolean found = false;
                for (String employee : employees) {
                    if (employee.trim().equalsIgnoreCase(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found!");
                }

                System.out.println("Data Loaded.");

            } else if (args[0].contains("c")) { // Count Employees
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                System.out.println("Total employees: " + employees.size());

                System.out.println("Data Loaded.");

            } else if (args[0].contains("u")) { // Update Employee
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                String nameToUpdate = args[0].substring(1).trim();

                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).trim().equals(nameToUpdate)) {
                        employees.set(i, "Updated");
                    }
                }

                writeEmployees(employees);
                System.out.println("Data Updated.");

            } else if (args[0].contains("d")) { // Delete Employee
                System.out.println("Loading data ...");

                List<String> employees = readEmployees();
                String nameToDelete = args[0].substring(1).trim();
                employees.removeIf(emp -> emp.trim().equals(nameToDelete));
                writeEmployees(employees);

                System.out.println("Data Deleted.");

            } else {
                System.out.println("Invalid option! Use: l, s, +name, ?name, c, uName, dName");
            }

        } catch (IOException e) {
            System.out.println("File operation failed: " + e.getMessage());
        }
    }

    //
    private static List<String> readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
        String line = reader.readLine();
        reader.close();
        return new ArrayList<>(Arrays.asList(line.split(",")));
    }

    //
    private static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
        writer.write(String.join(",", employees));
        writer.close();
    }
}
