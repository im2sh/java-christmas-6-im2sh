package christmas.view;

import static christmas.view.constants.EventMessage.printMessage;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.constants.EventMessage;

public class InputView {
    public static String inputReservationDate() {
        printMessage(EventMessage.INTRODUCTION.getMessage());
        printMessage(EventMessage.INPUT_DATE.getMessage());
        return Console.readLine();
    }

    public static String inputOrder() {
        printMessage(EventMessage.INPUT_ORDER.getMessage());
        return Console.readLine();
    }

}
