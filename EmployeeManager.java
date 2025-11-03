// File: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        //  Step 1: Argument Validation
        if (args.length != 1) {
            System.out.println(" Invalid number of arguments!");
            System.out.println(" Usage: java EmployeeManager [option]");
            System.out.println("Available options: l, s, +name, ?name, c, uName, dName");
            return; //
        }

        //
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                for (String emp : employees) {
                    System.out.println(emp.trim());
                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                Random rand = new Random();
                int index = rand.nextInt(employees.length);
                System.out.println(employees[index].trim());

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {

            System.out.println("Loading data ...");

            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true)
                );

                String newEmployee = args[0].substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                boolean found = false;
                String searchName = args[0].substring(1);

                for (String emp : employees) {
                    if (emp.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                char[] chars = line.toCharArray();

                boolean inWord = false;
                int count = 0;

                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(count + " word(s) found " + chars.length);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToUpdate = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToDelete = args[0].substring(1);

                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(nameToDelete);

                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Data Deleted.");
        }
    }
}
