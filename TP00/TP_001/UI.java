import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public void make_window(){
        ReservationHandler handler = new ReservationHandler();
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
                handler.submit_reservation(frame, roomInput, nameInput, checkinInput, checkoutInput, remarkInput);
            }
        });

        showlist_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handler.show_all_reservation(frame);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               handler.search(frame, searchCustomerInput, searchRoomInput);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handler.cancel_reservation(frame, searchCustomerInput, searchRoomInput);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handler.edit_reservation(frame, searchCustomerInput, searchRoomInput);
            }
        });

        frame.setVisible(true);
    }
}

//TODO: Edit reservation, when time is not yet started
//TODO: Swap room when 2 room are booked at identical time

