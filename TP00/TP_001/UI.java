import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UI {
    ArrayList<Reservation> reservations_list = new ArrayList<>();
    public void make_window(){
        System.out.println(reservations_list.size());
        JFrame frame = new JFrame("Reservation System");
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        Font font = new Font("Calibri", Font.PLAIN, 16);

        // Room Number
        JLabel roomLabel = new JLabel("Room Number (eg. F-107):");
        roomLabel.setBounds(50, 10, 200, 25);
        roomLabel.setFont(font);
        frame.add(roomLabel);

        JTextField roomInput = new JTextField();
        roomInput.setBounds(50, 35, 300, 25);
        roomInput.setFont(font);
        frame.add(roomInput);

        // Customer Name
        JLabel nameLabel = new JLabel("Your Name:");
        nameLabel.setBounds(50, 70, 100, 25);
        nameLabel.setFont(font);
        frame.add(nameLabel);

        JTextField nameInput = new JTextField();
        nameInput.setBounds(50, 95, 300, 25);
        nameInput.setFont(font);
        frame.add(nameInput);

        // Check-in Date
        JLabel checkinDateLabel = new JLabel("Check-in Date (yyyy-MM-dd HH:mm):");
        checkinDateLabel.setBounds(50, 130, 300, 25);
        checkinDateLabel.setFont(font);
        frame.add(checkinDateLabel);

        JTextField checkinInput = new JTextField();
        checkinInput.setBounds(50, 155, 300, 25);
        checkinInput.setFont(font);
        frame.add(checkinInput);

        // Check-out Date
        JLabel checkoutDateLabel = new JLabel("Check-out Date (yyyy-MM-dd HH:mm):");
        checkoutDateLabel.setBounds(50, 190, 300, 25);
        checkoutDateLabel.setFont(font);
        frame.add(checkoutDateLabel);

        JTextField checkoutInput = new JTextField();
        checkoutInput.setBounds(50, 215, 300, 25);
        checkoutInput.setFont(font);
        frame.add(checkoutInput);

        // Remark
        JLabel remark = new JLabel("Remark:");
        remark.setBounds(50, 250, 100, 25);
        remark.setFont(font);
        frame.add(remark);

        JTextField remarkInput = new JTextField();
        remarkInput.setBounds(50, 275, 300, 25);
        remarkInput.setFont(font);
        frame.add(remarkInput);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 320, 100, 25);
        submitButton.setFocusable(false);
        frame.add(submitButton);

        // Show List Button
        JButton showlist_button = new JButton("Show Reservations");
        showlist_button.setBounds(100, 350, 200, 25);
        showlist_button.setFocusable(false);
        frame.add(showlist_button);

        // Search Room Label
        JLabel search_func_label = new JLabel("Check Your Reservation:");
        search_func_label.setBounds(25, 370, 200, 25);
        frame.add(search_func_label);

        // Search Room Number
        JLabel searchRoomLabel = new JLabel("Search Room Number:");
        searchRoomLabel.setBounds(50, 390, 200, 25);
        searchRoomLabel.setFont(font);
        frame.add(searchRoomLabel);

        JTextField searchRoomInput = new JTextField();
        searchRoomInput.setBounds(50, 410, 300, 25);
        searchRoomInput.setFont(font);
        frame.add(searchRoomInput);
        
        // Search Customer Name
        JLabel searchCustomerLabel = new JLabel("Search Customer Name:");
        searchCustomerLabel.setBounds(50, 450, 200, 25);
        searchCustomerLabel.setFont(font);
        frame.add(searchCustomerLabel);

        JTextField searchCustomerInput = new JTextField();
        searchCustomerInput.setBounds(50, 470, 300, 25);
        searchCustomerInput.setFont(font);
        frame.add(searchCustomerInput);

        //Search button
        JButton searchButton = new JButton("Search Reservation");
        searchButton.setBounds(125, 500, 150, 25);
        searchButton.setFocusable(false);
        frame.add(searchButton);

        //Cancel button
        JButton cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(125, 530, 150, 25);
        cancelButton.setFocusable(false);
        frame.add(cancelButton);

        //Edit button
        JButton editButton = new JButton("Edit Reservation");
        editButton.setBounds(125, 560, 150, 25);
        editButton.setFocusable(false);
        frame.add(editButton);

//Buttons ActionListeners
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String roomNumber = roomInput.getText();
                String customerName = nameInput.getText();
                String str_checkinDate = checkinInput.getText();
                String str_checkoutDate = checkoutInput.getText();
                String remarkText = remarkInput.getText();

                if (roomNumber.isEmpty() || customerName.isEmpty() || str_checkinDate.isEmpty() || str_checkoutDate.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields except Remark are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                LocalDateTime checkIn = datetime_converter(str_checkinDate);
                LocalDateTime checkOut = datetime_converter(str_checkoutDate);
                Reservation reservation = new Reservation(roomNumber, customerName, checkIn, checkOut, remarkText);
                if(reservations_list.isEmpty()){
                    try {
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

                    } catch (IllegalArgumentException msg) {
                        JOptionPane.showMessageDialog(frame, "Error: " + msg.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    boolean duplicate = false;
                    for(int i = 0; i < reservations_list.size(); i++){
                        reservations_list.get(i);
                        if(reservations_list.get(i).getRoom_number().equals(roomNumber) && reservations_list.get(i).getCheckin().equals(checkIn)){
                            duplicate = true;
                            JOptionPane.showMessageDialog(frame, "Room already booked", "Reservation Details", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }

                    if(duplicate == false){
                        try{
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

                        }catch (IllegalArgumentException msg){
                            JOptionPane.showMessageDialog(frame, "Error: " + msg.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        showlist_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Create a StringBuilder to accumulate all reservation details
                StringBuilder message = new StringBuilder(); //Basically it let you append multiple string into one giant string
                if(reservations_list.isEmpty()){
                    message.append("List is empty");
                }else{
                    for(Reservation reservation: reservations_list){
                        message.append(String.format(" Room Number: %s\nCustomer: %s\nCheck-in: %s\nCheck-out: %s\nRemark: %s\n-------------------------------------\n", 
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
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String searchName = searchCustomerInput.getText();
                String searchRoom = searchRoomInput.getText();

                if(!searchName.isEmpty() || !searchRoom.isEmpty()){
                    boolean found = false;
                    for(Reservation reservation: reservations_list){
                        if(reservation.getRoom_number().equals(searchRoom) || reservation.getCustomer().equalsIgnoreCase(searchName)){
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
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String searchName = searchCustomerInput.getText();
                String searchRoom = searchRoomInput.getText();

                if(!searchName.isEmpty() || !searchRoom.isEmpty()){
                    boolean found = false;
                    for(int i = 0; i < reservations_list.size(); i++){
                        Reservation reservation = reservations_list.get(i);
                        if(reservation.getRoom_number().equals(searchRoom) || reservation.getCustomer().equalsIgnoreCase(searchName)){
                            found = true;
                            reservations_list.remove(i);
                            JOptionPane.showMessageDialog(frame, "Reservation for room " + reservation.getRoom_number() + " canceled.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String searchName = searchCustomerInput.getText();
                String searchRoom = searchRoomInput.getText();

                if(!searchName.isEmpty() || !searchRoom.isEmpty()){
                    boolean found = false;
                    for(int i = 0; i < reservations_list.size(); i++){
                        Reservation reservation = reservations_list.get(i);
                        if(reservation.getCustomer().equals(searchName) || reservation.getRoom_number().equals(searchRoom)){
                            found = true;
                            frame.setVisible(false);    
                            JFrame subframe = new JFrame("Edit Your Reservation");
                            subframe.setSize(400,400);
                            subframe.setLayout(null);
                            subframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            // Create and add input fields for Name, Room, Check-in, and Check-out
                            JLabel nameLabel = new JLabel("Name:");
                            nameLabel.setBounds(50, 50, 100, 30);
                            subframe.add(nameLabel);
                            
                            JTextField nameInput = new JTextField();
                            nameInput.setBounds(150, 50, 150, 30);
                            subframe.add(nameInput);
                            
                            JLabel roomLabel = new JLabel("Room:");
                            roomLabel.setBounds(50, 100, 100, 30);
                            subframe.add(roomLabel);
                            
                            JTextField roomInput = new JTextField();
                            roomInput.setBounds(150, 100, 150, 30);
                            subframe.add(roomInput);
                            
                            JLabel checkinLabel = new JLabel("Check-in:");
                            checkinLabel.setBounds(50, 150, 100, 30);
                            subframe.add(checkinLabel);
                            
                            JTextField checkinInput = new JTextField();
                            checkinInput.setBounds(150, 150, 150, 30);
                            subframe.add(checkinInput);
                            
                            JLabel checkoutLabel = new JLabel("Check-out:");
                            checkoutLabel.setBounds(50, 200, 100, 30);
                            subframe.add(checkoutLabel);
                            
                            JTextField checkoutInput = new JTextField();
                            checkoutInput.setBounds(150, 200, 150, 30);
                            subframe.add(checkoutInput);
                            
                            // Add Confirm button
                            JButton confirmButton = new JButton("Confirm");
                            confirmButton.setBounds(150, 250, 100, 30);
                            subframe.add(confirmButton);
                            subframe.setVisible(true);
                            break;
                        }
                    }
                    if (found == false){
                        JOptionPane.showMessageDialog(frame, "No Reservation Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Please enter a room number or customer name to edit reservation.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    public static LocalDateTime datetime_converter(String dateinput){
        if(dateinput.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateinput, format); //Convert string input to localdatetime object
        }else{
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}

//TODO: Edit reservation, when time is not yet started
//TODO: Swap room when 2 room are booked at identical time

