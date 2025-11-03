// File Name: EmployeeManager.java
import java.io.IOException;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No command provided. Use: l, s, +name, ?name, uName, dName, c");
            return;
        }

        String command = args[0];

        try {
            switch (command.charAt(0)) {
                case 'l':
                    listEmployees();
                    break;
                case 's':
                    showRandomEmployee();
                    break;
                case '+':
                    EmployeeDatabase.addEmployee(command.substring(1));
                    System.out.println("Employee Added.");
                    break;
                case '?':
                    searchEmployee(command.substring(1));
                    break;
                case 'u':
                    EmployeeDatabase.updateEmployee(command.substring(1));
                    System.out.println("Data Updated.");
                    break;
                case 'd':
                    EmployeeDatabase.deleteEmployee(command.substring(1));
                    System.out.println("Data Deleted.");
                    break;
                case 'c':
                    countWordsAndChars();
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listEmployees() throws IOException {
        System.out.println("Loading data...");
        for (String emp : EmployeeDatabase.readEmployees()) {
            System.out.println(emp.trim());
        }
        System.out.println("Data Loaded.");
    }

    private static void showRandomEmployee() throws IOException {
        System.out.println("Loading data...");
        String emp = EmployeeDatabase.getRandomEmployee();
        if (emp != null) {
            System.out.println("Random Employee: " + emp);
        } else {
            System.out.println("No employees found.");
        }
        System.out.println("Data Loaded.");
    }

    private static void searchEmployee(String name) throws IOException {
        System.out.println("Searching employee...");
        boolean found = EmployeeDatabase.searchEmployee(name);
        System.out.println(found ? "Employee found!" : "Employee not found.");
        System.out.println("Search Complete.");
    }

    private static void countWordsAndChars() throws IOException {
        int[] count = EmployeeDatabase.countWordsAndChars();
        System.out.println(count[0] + " word(s), " + count[1] + " character(s) found.");
    }
}
