import java.io.*;
import java.sql.*;

public class DemoVulnerable {
    // Hardcoded sensitive data
    private static final String SECRET_KEY = "mySuperSecretKey123";

    public static void main(String[] args) throws Exception {
        // 1. Command injection
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a shell command: ");
        String userCommand = reader.readLine();
        // Vulnerable: user input is directly passed to the shell
        Runtime.getRuntime().exec(userCommand);

        // 2. Path traversal
        System.out.print("Enter a filename to read: ");
        String filename = reader.readLine();
        File file = new File(filename); // Vulnerable: no sanitization
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        System.out.println("File contents:");
        String line;
        while ((line = fileReader.readLine()) != null) {
            System.out.println(line);
        }
        fileReader.close();

        // 3. SQL injection
        System.out.print("Enter user id: ");
        String userId = reader.readLine();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement stmt = conn.createStatement();
        // Vulnerable: user input concatenated into SQL query
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = '" + userId + "'");
        while (rs.next()) {
            System.out.println("User: " + rs.getString("username"));
        }
        rs.close();
        stmt.close();
        conn.close();

        // 4. Print hardcoded secret key
        System.out.println("Secret Key: " + SECRET_KEY);
    }
}
