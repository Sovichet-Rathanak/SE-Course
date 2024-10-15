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

public class UI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Reservation System");
        frame.setSize(400, 400);
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

        // Action Listener for the button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String roomNumber = roomInput.getText();
                    String customerName = nameInput.getText();
                    String str_checkinDate = checkinInput.getText();
                    String str_checkoutDate = checkoutInput.getText();
                    String remarkText = remarkInput.getText();

                    LocalDateTime checkIn = datetime_converter(str_checkinDate);
                    LocalDateTime checkOut = datetime_converter(str_checkoutDate);

                    Reservation reservation = new Reservation(roomNumber, customerName, checkIn, checkOut, remarkText);

                    // Create a message string to display
                    String message = String.format(
                        "Room Number: %s\nCustomer: %s\nCheck-in: %s\nCheck-out: %s\nRemark: %s",
                        reservation.getRoom_number(),
                        reservation.getCustomer(),
                        reservation.getCheckin(),
                        reservation.getCheckout(),
                        reservation.getRemark()
                    );

                    // Show the popup with the reservation details
                    JOptionPane.showMessageDialog(frame, message, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception msg) {
                    JOptionPane.showMessageDialog(frame, "Error: " + msg.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
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

//Todo: Complete the last question in the assignment