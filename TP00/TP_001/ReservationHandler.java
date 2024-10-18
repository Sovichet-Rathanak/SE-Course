import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ReservationHandler {
    ArrayList<Reservation> reservations_list = new ArrayList<>();
    
    public void submit_reservation(JFrame frame, JTextField roomInput, JTextField nameInput,JTextField checkinInput, JTextField checkoutInput, JTextField remarkInput){
        try{
            String roomNumber = roomInput.getText();
            String customerName = nameInput.getText();
            String str_checkinDate = checkinInput.getText();
            String str_checkoutDate = checkoutInput.getText();
            String remarkText = remarkInput.getText();

            LocalDateTime checkIn = datetime_converter(str_checkinDate);
            LocalDateTime checkOut = datetime_converter(str_checkoutDate);
            Reservation reservation = new Reservation(roomNumber, customerName, checkIn, checkOut, remarkText);
            if (roomNumber.isEmpty() || customerName.isEmpty() || str_checkinDate.isEmpty() || str_checkoutDate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields except Remark are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            if(reservations_list.isEmpty()){
                reservations_list.add(reservation);
                String message = String.format(
                    "Room Number: %s\nCustomer: %s\nCheck-in: %s\nCheck-out: %s\nRemark: %s",
                    reservation.getRoom_number(),
                    reservation.getCustomer(),
                    reservation.getCheckin(),
                    reservation.getCheckout(),
                    reservation.getRemark()
                );
                JOptionPane.showMessageDialog(frame, message, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
            }else{
                boolean duplicate = false;
                for(int i = 0; i < reservations_list.size(); i++){
                    if(reservations_list.get(i).getRoom_number().equals(roomNumber) && reservations_list.get(i).getCheckin().equals(checkIn)){
                        duplicate = true;
                        JOptionPane.showMessageDialog(frame, "Room already booked", "Reservation Details", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }if(duplicate == false){
                    reservations_list.add(reservation);
                    String message = String.format(
                        "Room Number: %s\nCustomer: %s\nCheck-in: %s\nCheck-out: %s\nRemark: %s",
                        reservation.getRoom_number(),
                        reservation.getCustomer(),
                        reservation.getCheckin(),
                        reservation.getCheckout(),
                        reservation.getRemark()
                    );
                    JOptionPane.showMessageDialog(frame, message, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(IllegalArgumentException msg){
            JOptionPane.showMessageDialog(frame, "Error: " + msg.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void show_all_reservation(JFrame frame){
        StringBuilder message = new StringBuilder(); //Basically it let you append multiple string into one giant string
        if(reservations_list.isEmpty()){
            message.append("List is empty");
        }else{
            for(Reservation reservation: reservations_list){
                message.append(String.format("Room Number: %s\nCustomer: %s\nCheck-in: %s\nCheck-out: %s\nRemark: %s\n-------------------------------------\n", 
                    reservation.getRoom_number(),
                    reservation.getCustomer(),
                    reservation.getCheckin(),
                    reservation.getCheckout(),
                    reservation.getRemark()
                ));
            }
        }
        JOptionPane.showMessageDialog(frame, message, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public void search(JFrame frame, JTextField searchCustomerInput, JTextField searchRoomInput){
        String searchName = searchCustomerInput.getText();
        String searchRoom = searchRoomInput.getText();

        if(!searchName.isEmpty() && !searchRoom.isEmpty()){
            boolean found = false;
            for(Reservation reservation: reservations_list){
                if(reservation.getRoom_number().equals(searchRoom) && reservation.getCustomer().equalsIgnoreCase(searchName)){
                        String message = String.format("Reservation Found:\nRoom Number: %s\nCustomer: %s\nCheck-in: %s\nCheck-out: %s\nRemark: %s",
                        reservation.getRoom_number(),
                        reservation.getCustomer(),
                        reservation.getCheckin(),
                        reservation.getCheckout(),
                        reservation.getRemark()
                    );
                    JOptionPane.showMessageDialog(frame, message, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }if(found == false){
                JOptionPane.showMessageDialog(frame, "No Reservation Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Please enter a room number or customer name to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancel_reservation(JFrame frame, JTextField searchCustomerInput, JTextField searchRoomInput){
        String searchName = searchCustomerInput.getText();
        String searchRoom = searchRoomInput.getText();

        if(!searchName.isEmpty() || !searchRoom.isEmpty()){
            boolean found = false;
            for(int i = 0; i < reservations_list.size(); i++){
                if(reservations_list.get(i).getRoom_number().equals(searchRoom) && reservations_list.get(i).getCustomer().equalsIgnoreCase(searchName)){
                    found = true;
                    reservations_list.remove(i);
                    JOptionPane.showMessageDialog(frame, "Reservation for room " + reservations_list.get(i).getRoom_number() + " canceled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            
            if(found == false){
                JOptionPane.showMessageDialog(frame, "No Reservation Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Please enter a room number or customer name to cancel reservation.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void edit_reservation(JFrame frame, JTextField searchCustomerInput, JTextField searchRoomInput) {
        String searchName = searchCustomerInput.getText();
        String searchRoom = searchRoomInput.getText();

        if (!searchName.isEmpty() || !searchRoom.isEmpty()) {
            boolean found = false;
            for (int i = 0; i < reservations_list.size(); i++) {
                if (reservations_list.get(i).getCustomer().equals(searchName) || reservations_list.get(i).getRoom_number().equals(searchRoom)) {
                    found = true;
                    if (reservations_list.get(i).getCheckin().isBefore(LocalDateTime.now()) && reservations_list.get(i).getCheckout().isAfter(LocalDateTime.now())) {
                        JOptionPane.showMessageDialog(frame, "Your reservation is currently ongoing and cannot be edited.", "Editing Failed", JOptionPane.ERROR_MESSAGE);
                    }else{
                        frame.setVisible(false);    
                        JFrame subframe = new JFrame("Edit Your Reservation");
                        subframe.setSize(400, 400);
                        subframe.setLayout(null); 
                        subframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
                        JLabel nameLabel = new JLabel("Name:");
                        nameLabel.setBounds(50, 10, 200, 25); 
                        subframe.add(nameLabel);
                        
                        JTextField Edit_nameInput = new JTextField();
                        Edit_nameInput.setBounds(50, 35, 300, 25); 
                        subframe.add(Edit_nameInput);
                        
                        JLabel roomLabel = new JLabel("Room:");
                        roomLabel.setBounds(50, 70, 100, 25); 
                        subframe.add(roomLabel);
                        
                        JTextField Edit_roomInput = new JTextField();
                        Edit_roomInput.setBounds(50, 95, 300, 25); 
                        subframe.add(Edit_roomInput);
                        
                        JLabel checkinLabel = new JLabel("Check-in (yyyy-MM-dd HH:mm):");
                        checkinLabel.setBounds(50, 130, 300, 25); 
                        subframe.add(checkinLabel);
                        
                        JTextField Edit_checkinInput = new JTextField();
                        Edit_checkinInput.setBounds(50, 155, 300, 25); 
                        subframe.add(Edit_checkinInput);
                        
                        JLabel checkoutLabel = new JLabel("Check-out (yyyy-MM-dd HH:mm):");
                        checkoutLabel.setBounds(50, 190, 300, 25); 
                        subframe.add(checkoutLabel);
                        
                        JTextField Edit_checkoutInput = new JTextField();
                        Edit_checkoutInput.setBounds(50, 215, 300, 25); 
                        subframe.add(Edit_checkoutInput);
                        
                        JButton confirmButton = new JButton("Confirm");
                        confirmButton.setBounds(50, 250, 100, 25); 
                        subframe.add(confirmButton);
                        
                        subframe.setVisible(true);
                        
                        int index = i; 
                        confirmButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String name = Edit_nameInput.getText();
                                String room = Edit_roomInput.getText();
                                String str_checkin = Edit_checkinInput.getText();
                                String str_checkout = Edit_checkoutInput.getText();
    
                                LocalDateTime checkin = datetime_converter(str_checkin);
                                LocalDateTime checkout = datetime_converter(str_checkout);
    
                                Reservation currentReservation = reservations_list.get(index);
                                Reservation oldRoomReservation = find_old_Reservation(room);
    
                                if (currentReservation.getCheckin().equals(checkin) && 
                                    currentReservation.getCheckout().equals(checkout) && 
                                    currentReservation.getRoom_number().equals(room)) {
                                    JOptionPane.showMessageDialog(subframe, "You've entered the same info", "Error", JOptionPane.ERROR_MESSAGE);
    
                                } else if (oldRoomReservation != null && 
                                        oldRoomReservation.getCheckin().equals(checkin) && 
                                        oldRoomReservation.getCheckout().equals(checkout)) {
                                    JOptionPane.showMessageDialog(subframe, "The room is already booked during this time", "Error", JOptionPane.ERROR_MESSAGE);
    
                                } else if (oldRoomReservation != null) {
                                    String tempCustomer = oldRoomReservation.getCustomer();
                                    String tempRoom = oldRoomReservation.getRoom_number();
    
                                    oldRoomReservation.setCustomer(currentReservation.getCustomer());
                                    oldRoomReservation.setRoom_number(currentReservation.getRoom_number());
                                    oldRoomReservation.setCheckin(currentReservation.getCheckin());
                                    oldRoomReservation.setCheckout(currentReservation.getCheckout());
    
                                    currentReservation.setCustomer(tempCustomer);
                                    currentReservation.setRoom_number(tempRoom);
                                    currentReservation.setCheckin(checkin);
                                    currentReservation.setCheckout(checkout);
    
                                    JOptionPane.showMessageDialog(subframe, "Rooms have been swapped successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    
                                } else {
                                    currentReservation.setRoom_number(room);
                                    currentReservation.setCustomer(name);
                                    currentReservation.setCheckin(checkin);
                                    currentReservation.setCheckout(checkout);
    
                                    JOptionPane.showMessageDialog(subframe, "Reservation updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
    
                                subframe.dispose();
                                frame.setVisible(true);
    
                            } catch (IllegalArgumentException msg) {
                                JOptionPane.showMessageDialog(subframe, msg.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(frame, "No Reservation Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
                    }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a room number or customer name to edit reservation.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static LocalDateTime datetime_converter(String dateinput){
        if(dateinput.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateinput, format); //Convert string input to localdatetime object
        }else{
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    //Find the old reservation so it can be swap
    public Reservation find_old_Reservation(String room){
        for(Reservation reservation: reservations_list){
            if (reservation.getRoom_number().equals(room)) {
                return reservation;
            }
        }
        return null;
    }
}
