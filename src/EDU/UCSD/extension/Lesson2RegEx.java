package EDU.UCSD.extension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Lesson2RegEx {

    public void syntaxSummary() {
        var commandName = getClass().getSimpleName();
        System.out.printf("%n%-7s%-13s%-11s%s%n",
                "Usage:",
                commandName,
                "<filename>",
                "# Parses the file specified by the filename argument");
    }

    public static void main(String[] arguments) {
        Lesson2RegEx lesson2RegEx = new Lesson2RegEx();
        String fileName = null;
        String data = null;
        try {
            fileName = arguments[0];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println("Oops, an argument is required!");
            lesson2RegEx.syntaxSummary();
            System.exit(1);
        }

        try {
            File file = new File(fileName);
            try (Scanner scanner = new Scanner(file)) {
                scanner.useDelimiter("\\Z");
                data = scanner.next();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.exit(1);
        }

        System.out.printf("%n%s%n%s%n%s%n",
                "Processed the following input file:",
                fileName,
                "Results are as follows:");

        list_Of_PANIDs(data);
        list_Of_MAC_Addresses(data);
        list_Of_RF_RSSI_M_Values(data);
        
    }

    private static void list_Of_RF_RSSI_M_Values(String data) {
        System.out.println(data);
    }

    private static void list_Of_MAC_Addresses(String data) {
        System.out.println(data);
    }

    private static void list_Of_PANIDs(String data) {
        System.out.println(data);
    }
}
