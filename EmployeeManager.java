// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Validate command line arguments
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands:");
            System.out.println("  l - List all employees");
            System.out.println("  s - Show random employee");
            System.out.println("  +<name> - Add new employee");
            System.out.println("  ?<name> - Search for employee");
            System.out.println("  c - Count words and characters");
            System.out.println("  u<name> - Update employee");
            System.out.println("  d<name> - Delete employee");
            return;
        }

        // Check if command is valid
        String command = args[0];
        if (!isValidCommand(command)) {
            System.out.println("Error: Invalid command format.");
            System.out.println("Valid commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }

        // Check arguments
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                
                for (String employee : employees) {
                    System.out.println(employee);
                }
                
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
            
        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                System.out.println(line);
                
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
                
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
            
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                // Validate that there's a name after the + symbol
                if (command.length() <= 1) {
                    System.out.println("Error: Missing employee name after '+'");
                    return;
                }
                
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true));
                String newEmployee = command.substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
            
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                // Validate that there's a name after the ? symbol
                if (command.length() <= 1) {
                    System.out.println("Error: Missing employee name after '?'");
                    return;
                }
                
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = command.substring(1);
                
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
                
                if (!found) {
                    System.out.println("Employee not found!");
                }
                
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
            
        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                
                for (char c : characters) {
                    if (c == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                
                System.out.println(wordCount + " word(s) found " + characters.length);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
            
        } else if (command.contains("u")) {
            System.out.println("Loading data ...");
            try {
                // Validate that there's a name after the u symbol
                if (command.length() <= 1) {
                    System.out.println("Error: Missing employee name after 'u'");
                    return;
                }
                
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String targetName = command.substring(1);
                boolean updated = false;
                
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(targetName)) {
                        employees[i] = "Updated";
                        updated = true;
                    }
                }
                
                if (!updated) {
                    System.out.println("Employee not found for update!");
                    reader.close();
                    return;
                }
                
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");
            
        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            try {
                // Validate that there's a name after the d symbol
                if (command.length() <= 1) {
                    System.out.println("Error: Missing employee name after 'd'");
                    return;
                }
                
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String targetName = command.substring(1);
                
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                boolean removed = employeeList.remove(targetName);
                
                if (!removed) {
                    System.out.println("Employee not found for deletion!");
                    reader.close();
                    return;
                }
                
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }
    
    /**
     * Validates if the command format is correct
     * @param command the command string to validate
     * @return true if command is valid, false otherwise
     */
    private static boolean isValidCommand(String command) {
        if (command == null || command.isEmpty()) {
            return false;
        }
        
        // Single character commands
        if (command.equals("l") || command.equals("s") || command.equals("c")) {
            return true;
        }
        
        // Commands with parameters (must have at least 2 characters)
        if (command.startsWith("+") || command.startsWith("?") || 
            command.startsWith("u") || command.startsWith("d")) {
            return command.length() >= 2;
        }
        
        return false;
    }
}