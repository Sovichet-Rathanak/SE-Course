package TP00.TP_001;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UI {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        LocalDateTime checkIn = LocalDateTime.parse("2024-10-15 14:00", formatter);  
        LocalDateTime checkOut = LocalDateTime.parse("2024-10-17 11:00", formatter);

        Reservation reservation = new Reservation("F-109", "Sovichet Rathanak", checkIn, checkOut,"No");
        System.out.println(reservation.getRoom_number());
        System.out.println(reservation.getCustomer());
        System.out.println(reservation.getCheckin());
        System.out.println(reservation.getCheckout());
        System.out.println(reservation.getRemark());
    }
}
