package EDU.UCSD.extension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // list_Of_RF_RSSI_M_Values(data);

    }

    private static void list_Of_RF_RSSI_M_Values(String data) {
        System.out.println("Not yet implemented");
    }

    private static void list_Of_MAC_Addresses(String data) {
        final String MACaddress = "\\b(?iU:[0-9a-f]{16})\\b";
        Pattern pattern = Pattern.compile(MACaddress);
        System.out.printf("- List of MAC addresses (Total of %d): %n",
                pattern.matcher(data).results().map(MatchResult::group).count());
        pattern.matcher(data)
                .results()
                .map(MatchResult::group)
                .forEach(System.out::println);
    }

    private static void list_Of_PANIDs(String data) {
        final String PANID = "(PANID)\\s+(=)\\s+([0-9a-f]{4})";
        Pattern pattern = Pattern.compile(PANID);
        System.out.printf("- List of PAN IDs (Total of %d): %n",
                pattern.matcher(data).results().map(MatchResult::group).count());
        pattern.matcher(data)
                .results()
                .map(MatchResult::group)
                .forEach(System.out::println);
    }
}
