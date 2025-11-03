// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");

                for (String emp : employees) {
                    System.out.println(emp);
                }

                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");

                Random rand = new Random();
                int idx = rand.nextInt(employees.length);

                System.out.println(employees[idx]);

                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt", true));

                String name = args[0].substring(1);
                w.write(", " + name);
                w.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");
                String searchName = args[0].substring(1);
                boolean found = false;

                for (String emp : employees) {
                    if (emp.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found.");
                }

                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));

                String line = r.readLine();
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

                System.out.println(count + " word(s) found, " + chars.length + " characters.");
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");
                String nameToUpdate = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));
                w.write(String.join(",", employees));
                w.close();
                r.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));

                String line = r.readLine();
                String[] employees = line.split(",");
                String nameToDelete = args[0].substring(1);

                List<String> list = new ArrayList<>(Arrays.asList(employees));
                list.removeIf(emp -> emp.trim().equals(nameToDelete));

                BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));
                w.write(String.join(",", list));
                w.close();
                r.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }
}
