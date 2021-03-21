package EDU.UCSD.extension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class StreamTester {
    static int index = 0;
    private static void justTesting(String data) {
        //final String firstAndLast = "[a-zA-Z]+[ ]+[a-zA-Z]+";
        final String firstOrLast = "[a-zA-Z]+";
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        Pattern pattern = Pattern.compile(firstOrLast);
        System.out.println("\nJust some random names…\n");
        pattern.matcher(data)
                .results()
                .map(MatchResult::group)
                .forEach(x -> System.out.print(index++ % 2 != 0 ? x + LINE_SEPARATOR : x + " "));
    }

    public static void main(String[] arguments) throws FileNotFoundException {
        justTesting(new Scanner(new File("TestData.txt")).useDelimiter("\\Z").next());
    }

}
