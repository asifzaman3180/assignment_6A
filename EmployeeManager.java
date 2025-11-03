public class EmployeeManager {
    public static void main(String[] args) {

        // ✅ Task #2: Validate command-line arguments
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options: l | s | +<name> | ?<name> | c | u<name> | d<name>");
            return;
        }

        String command = args[0];  // store argument in a clear variable

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String emp : employees) {
                    System.out.println(emp.trim());
                }
                reader.close();
            } catch (Exception e) {
                System.out.println("Error reading employees.txt");
            }
            System.out.println("Data Loaded.");
        }

        // (keep the rest of the existing code as-is for now)
    }
}
