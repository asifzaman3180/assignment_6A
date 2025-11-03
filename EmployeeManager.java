// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // ✅ Step 1: Validate command-line arguments
        if (args.length == 0) {
            System.out.println("Error: No command-line arguments provided.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l (list), s (search), + (add), ? (check), c (count), u (update), d (delete)");
            return; // stop program gracefully
        }

        if (args.length > 1) {
            System.out.println("Error: Too many arguments provided.");
            System.out.println("Usage: java EmployeeManager <command>");
            return; // stop program gracefully
        }

        // ✅ Step 2: Proceed safely — args[0] now guaranteed to exist
        String command = args[0];

        if (command.equals("l")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
                reader.close();
            } catch (Exception exception) {
                System.out.println("Error loading data: " + exception.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println("Random employee: " + employees[randomIndex]);
                reader.close();
            } catch (Exception exception) {
                System.out.println("Error loading data: " + exception.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("+")) {

            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String newEmployee = command.substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception exception) {
                System.out.println("Error writing data: " + exception.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("?")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = command.substring(1);
                for (String employee : employees) {
                    if (employee.trim().equalsIgnoreCase(searchName.trim())) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Employee not found.");
                }
                reader.close();
            } catch (Exception exception) {
                System.out.println("Error reading data: " + exception.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("c")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                System.out.println("Employee count: " + employees.length);
                reader.close();
            } catch (Exception exception) {
                System.out.println("Error reading data: " + exception.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("u")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToUpdate = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception exception) {
                System.out.println("Error updating data: " + exception.getMessage());
            }
            System.out.println("Data Updated.");

        } else if (command.contains("d")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToDelete = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(nameToDelete);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();
            } catch (Exception exception) {
                System.out.println("Error deleting data: " + exception.getMessage());
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Error: Invalid command '" + command + "'");
            System.out.println("Usage: java EmployeeManager <command>");
        }
    }
}
