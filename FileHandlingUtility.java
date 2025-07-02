import java.io.*;
import java.util.Scanner;

/**
 * FileHandlingUtility demonstrates basic file operations in Java:
 * 1. Writing to a file
 * 2. Reading from a file
 * 3. Modifying content in a file
 */
public class FileHandlingUtility {

    private static final String FILE_NAME = "sample.txt"; // Target file

    /**
     * Writes user input content to the file.
     */
    public static void writeToFile(String content) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(content);
            System.out.println("✅ Content written to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and prints content of the file.
     */
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n📄 File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Modifies the file by replacing all occurrences of oldWord with newWord.
     */
    public static void modifyFile(String oldWord, String newWord) {
        StringBuilder modifiedContent = new StringBuilder();

        // Read existing file content
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Replace old word with new word in each line
                modifiedContent.append(line.replace(oldWord, newWord)).append("\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading for modification: " + e.getMessage());
            return;
        }

        // Write updated content back to file
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(modifiedContent.toString());
            System.out.println("✏️ File modified successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error writing modified content: " + e.getMessage());
        }
    }

    /**
     * Main method – simple command-line menu to test the utility.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Menu loop
        do {
            System.out.println("\n=== FILE HANDLING UTILITY ===");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter text to write:");
                    String content = scanner.nextLine();
                    writeToFile(content);
                    break;

                case 2:
                    readFromFile();
                    break;

                case 3:
                    System.out.print("Word to replace: ");
                    String oldWord = scanner.nextLine();
                    System.out.print("New word: ");
                    String newWord = scanner.nextLine();
                    modifyFile(oldWord, newWord);
                    break;

                case 4:
                    System.out.println("👋 Exiting program.");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
