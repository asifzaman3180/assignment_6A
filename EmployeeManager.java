// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // ✅ Task #2 — Argument Validation
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options:");
            System.out.println("  l              - List all employees");
            System.out.println("  s              - Show a random employee");
            System.out.println("  +<name>        - Add a new employee");
            System.out.println("  ?<name>        - Search for an employee");
            System.out.println("  c              - Count employees");
            System.out.println("  u<name>        - Update employee name to 'Updated'");
            System.out.println("  d<name>        - Delete an employee");
            return; // Stop safely without crashing
        }

        String command = args[0].trim();

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String emp : employees) {
                    System.out.println(emp.trim());
                }
                reader.close();
            } catch (Exception e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx].trim());
                reader.close();
            } catch (Exception e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                String name = command.substring(1).trim();
                if (name.isEmpty()) {
                    System.out.println("Please provide a name to add.");
                } else {
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("employees.txt", true));
                    writer.write(", " + name);
                    writer.close();
                    System.out.println("Employee added: " + name);
                }
            } catch (Exception e) {
                System.out.println("Error writing data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String search = command.substring(1).trim();
                boolean found = false;

                for (String emp : employees) {
                    if (emp.trim().equalsIgnoreCase(search)) {
                        System.out.println("Employee found: " + emp.trim());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found.");
                }
                reader.close();
            } catch (Exception e) {
                System.out.println("Error searching data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                System.out.println(employees.length + " employee(s) found.");
                reader.close();
            } catch (Exception e) {
                System.out.println("Error counting data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                String name = command.substring(1).trim();
                if (name.isEmpty()) {
                    System.out.println("Please provide a name to update.");
                } else {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(new FileInputStream("employees.txt")));
                    String line = reader.readLine();
                    String[] employees = line.split(",");
                    boolean updated = false;

                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].trim().equalsIgnoreCase(name)) {
                            employees[i] = "Updated";
                            updated = true;
                        }
                    }

                    if (updated) {
                        BufferedWriter writer = new BufferedWriter(
                                new FileWriter("employees.txt"));
                        writer.write(String.join(",", employees));
                        writer.close();
                        System.out.println("Employee updated successfully.");
                    } else {
                        System.out.println("Employee not found to update.");
                    }

                    reader.close();
                }
            } catch (Exception e) {
                System.out.println("Error updating data: " + e.getMessage());
            }
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                String name = command.substring(1).trim();
                if (name.isEmpty()) {
                    System.out.println("Please provide a name to delete.");
                } else {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(new FileInputStream("employees.txt")));
                    String line = reader.readLine();
                    String[] employees = line.split(",");
                    List<String> list = new ArrayList<>(Arrays.asList(employees));
                    boolean removed = list.removeIf(emp -> emp.trim().equalsIgnoreCase(name));

                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("employees.txt"));
                    writer.write(String.join(",", list));
                    writer.close();
                    reader.close();

                    if (removed) {
                        System.out.println("Employee deleted: " + name);
                    } else {
                        System.out.println("Employee not found to delete.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Invalid argument provided. Run program without arguments to see usage instructions.");
        }
    }
}
