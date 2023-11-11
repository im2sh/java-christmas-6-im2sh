package christmas.domain;

public class User {
    private final int reservationDate;

    public User(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean checkDate(){
        if(reservationDate <= 25)
            return true;
        return false;
    }

    public int checkChristmasEvent(int discount){
        return discount + (reservationDate * 100 - 100);
    }
}
