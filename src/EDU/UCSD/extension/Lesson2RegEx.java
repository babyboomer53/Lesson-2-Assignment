package EDU.UCSD.extension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Lesson2RegEx class implements a number of methods which can be invoked to
 * parse  data  stored in  a text file.  The name  of the file  to parse must be
 * passed  to  the program  as a  commandline argument.  When invoked without an
 * argument,  the program displays an error message and a command syntax summary
 * on  the  console. Lesson2RegEx  uses regular  expressions in conjunction with
 * Java's  Pattern and  Matcher classes  (java.util.regex) to  extract character
 * sequences  from  the data.  When  invoked,  Lesson2RegEx generates  a  report
 * resembling the following:
 * <p>
 * Processed the following input file:
 * neighbor-dump.txt
 * Results are as follows:
 * <p>
 * - List of PANIDs (Total of 2):
 * <p>
 * PANID = 04fa
 * PANID = 329d
 * <p>
 * - List of MAC addresses (Total of 4):
 * <p>
 * 000781fe0000326f
 * 000781fe0000614e
 * 000781fe00002f76
 * 000781fe0000313e
 * <p>
 * - List of RF_RSSI_M values for each MAC address
 * <p>
 * 000781fe0000326f -51.984
 * 000781fe0000614e -24.294
 * 000781fe00002f76 -25.5
 * 000781fe0000313e -36.953
 * <p>
 * The  regular expression "PANID\\s+=\\s+[0-9a-f]{4}" is used to find character
 * sequences  in the data that match the pattern. This pattern matches sequences
 * of  characters beginning  with the letters P-A-N-I-D, followed by one or more
 * spaces (\\s+), followed by an equals sign (=), followed by one or more spaces
 * (\\s+)  and  ending in  any four  hexadecimal digits ([0-9a-f]{4}). Sequences
 * matching this pattern represent PANIDs, and are listed on individual lines in
 * the first section of the report.
 * <p>
 * The  regular  expression "\\b([0-9a-f]{16})\\b"  is  used  to find  character
 * sequences  in the data that match the pattern. This pattern matches sequences
 * of   characters  consisting  of  sixteen  hexadecimal  digits  ([0-9a-f]{16})
 * surrounded  by  "non-word" characters  (\\b…  \\b).  Sequences matching  this
 * pattern  represent  MAC addresses, and are  listed on individual lines in the
 * middle section of the report.
 * <p>
 * The regular expression "\\b[0-9a-f]{16}\\b|(-[1-9]*\\.[0-9]*" is used to find
 * character  sequences in the data that match either pattern. The bar character
 * (|)  in  this expression  is  the  "or"  operator.  It means  that  character
 * sequences  in the  input will be compared to both expressions. The expression
 * to  the  left  of  the  "or" operator  (|)  will  match  character  sequences
 * consisting  of  sixteen hexadecimal digits ([0-9a-f]{16}) surrounded by "non-
 * word"  characters (\\b…\\b).  Sequences matching  this pattern  represent MAC
 * addresses.
 * <p>
 * The  expression to  the  right  of the  "or"  operator  will match  character
 * sequences  that  begin with  a hyphen  (-), followed by  any number of digits
 * between  one and  nine inclusive ([1-9]*), followed by a decimal point (\\.),
 * followed  by  any number of digits  between zero and nine inclusive ([0-9]*).
 * Sequences matching this pattern represent RSSI values.
 * <p>
 * MAC  addresses  and their corresponding  RSSI values are listed on individual
 * lines in the last section of the report.
 *
 * @author Edgar Cole
 */

public class Lesson2RegEx {

    static int index = 0;

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
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        final String RSSI = "(\\b[0-9a-f]{16}\\b)|(-[1-9]*\\.[0-9]*)";
        Pattern pattern = Pattern.compile(RSSI);
        System.out.println("\n- List of RF_RSSI_M values for each MAC address\n");
        pattern.matcher(data)
                .results()
                .map(MatchResult::group)
                .forEach(x -> System.out.print(index++ % 2 != 0 ? x + LINE_SEPARATOR : x + " "));
    }

    private static void list_Of_MAC_Addresses(String data) {
        final String MACaddress = "\\b([0-9a-f]{16})\\b";
        Pattern pattern = Pattern.compile(MACaddress);
        System.out.printf("%n- List of MAC addresses (Total of %d): %n%n",
                pattern.matcher(data).results().map(MatchResult::group).count());
        pattern.matcher(data)
                .results()
                .map(MatchResult::group)
                .forEach(System.out::println);
    }

    private static void list_Of_PANIDs(String data) {
        final String PANID = "PANID\\s+=\\s+[0-9a-f]{4}";
        Pattern pattern = Pattern.compile(PANID);
        System.out.printf("%n- List of PAN IDs (Total of %d): %n%n",
                pattern.matcher(data).results().map(MatchResult::group).count());
        pattern.matcher(data.replaceAll("\\s+", " "))
                .results()
                .map(MatchResult::group)
                .forEach(System.out::println);
    }
}
