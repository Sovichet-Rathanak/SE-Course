import java.time.Duration;
import java.time.LocalDateTime;

public class Reservation{
    private String room_number;
    private String customer;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private String remark;
    public Reservation(String room_number, String customer, LocalDateTime checkin, LocalDateTime checkout, String remark) {
        setRoom_number(room_number);
        setCustomer(customer);
        setCheckin(checkin);
        setCheckout(checkout);
        setRemark(remark);
    }

    public Reservation(String room_number) {
        this.room_number = room_number;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        if(!room_number.matches("^[A-Z]-\\d{0,3}") || room_number.isEmpty()){
            throw new IllegalArgumentException("Room does not exist");
        }else{
            this.room_number = room_number;
        }
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        if(!customer.matches("^(?=[^aiueo\n]*[aiueo])(?=[^b-df-hj-np-tv-z\n]*[b-df-hj-np-tv-z])[a-zA-Z ]+$") || customer.isEmpty()){
            throw new IllegalArgumentException("Customer name must contain Consonants and Vowels");
        }else{
            this.customer = customer;
        }
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        if(checkin.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Checkin date must be in the future");
        }else{
            this.checkin = checkin;
        }
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        if(checkout.isBefore(this.checkin) || checkout.equals(checkin) || Duration.between(this.checkin, checkout).toHours() < 1){
            throw new IllegalArgumentException("Checkout must be at least an hour after checkin");
        }else{
            this.checkout = checkout;
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
