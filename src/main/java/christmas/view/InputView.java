package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.utils.EventMessage;

public class InputView {
    public static String inputReservationDate(){
        System.out.println(EventMessage.INTRODUCTION.getMessage());
        System.out.println(EventMessage.INPUT_DATE.getMessage());
        return Console.readLine();
    }
    public static String inputOrder(){
        System.out.println(EventMessage.INPUT_ORDER.getMessage());
        return Console.readLine();
    }

}
