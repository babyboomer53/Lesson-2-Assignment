package EDU.UCSD.extension;

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
        String argument;
        try {
            argument = arguments[0];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println("Oops, an argument is required!");
            lesson2RegEx.syntaxSummary();
            System.exit(1);
        }

    }
}
