// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a valid argument.");
            return;
        }

        // Load data
        if (args[0].equals("l")) {
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

        } else if (args[0].equals("s")) {
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

        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String name = args[0].substring(1);
                writer.write(", " + name);
                writer.close();
            } catch (Exception e) {
                System.out.println("Error writing data: " + e.getMessage());
            }
            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String search = args[0].substring(1).trim();
                boolean found = false;

                for (String emp : employees) {
                    if (emp.trim().equalsIgnoreCase(search)) {
                        System.out.println("Employee found!");
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

        } else if (args[0].equals("c")) {
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

        } else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String name = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equalsIgnoreCase(name)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception e) {
                System.out.println("Error updating data: " + e.getMessage());
            }
            System.out.println("Data Updated.");

        } else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String name = args[0].substring(1);

                List<String> list = new ArrayList<>(Arrays.asList(employees));
                list.removeIf(emp -> emp.trim().equalsIgnoreCase(name));

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", list));
                writer.close();
                reader.close();
            } catch (Exception e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
            System.out.println("Data Deleted.");
        } else {
            System.out.println("Invalid argument provided.");
        }
    }
}
