package christmas.view;

public class OutputView {
    public static void printErrorMessage(Exception exception) {
        System.out.println((exception.getMessage() + "\n"));
    }
}
