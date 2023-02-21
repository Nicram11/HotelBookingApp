package View.Dialogs;





import Model.Reservation;
import View.Dialogs.Validators.DialogDataValidator;
import View.Panels.RoomPanel;
import View.Style;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AddReservationDialog extends JDialog {

    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 2;
    public static final int RESULT_ERROR = 3;
    public int result;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private DatePicker checkInDatePicker;
    private DatePicker checkOutDatePicker;
    private JButton bookButton;
    private JButton cancelButton;
    private RoomPanel roomPanel;

    public AddReservationDialog(RoomPanel roomPanel) {
        this.roomPanel = roomPanel;
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Edit Reservation");
        //mainPanel stworzony aby dodać odstęp od granicy okna
        JPanel mainPanel = new JPanel();

        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.setPreferredSize(new Dimension(400,150));


        panel.add(new JLabel("First Name:"));

        firstNameField = new JTextField();
        panel.add(firstNameField);


        panel.add(new JLabel("Last Name:"));

        lastNameField = new JTextField(12);
        panel.add(lastNameField);


        panel.add(new JLabel("Phone Number:"));

        phoneField = new JTextField(12);
        panel.add(phoneField);


        panel.add(new JLabel("Check-in Date:"));

        checkInDatePicker = new DatePicker();
        panel.add(checkInDatePicker);


        panel.add(new JLabel("Check-out Date:"));

        checkOutDatePicker = new DatePicker();
        panel.add(checkOutDatePicker);


        bookButton = new JButton("Book");
        panel.add(bookButton);

        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                LocalDate checkInDate = checkInDatePicker.getDate();
                LocalDate checkOutDate = checkOutDatePicker.getDate();


                if(RESULT_OK == DialogDataValidator.AddDialogValidate(roomPanel,AddReservationDialog.this, firstName,lastName,checkInDate,checkOutDate)){

                    roomPanel.selectedReservation = new Reservation(roomPanel.selectedRoom.roomId,
                            checkInDatePicker.getDate(),
                            checkOutDatePicker.getDate(),
                            firstNameField.getText(),
                            lastNameField.getText(),
                            phoneField.getText()
                    );


                    roomPanel.loadRooms(roomPanel.selectedDate);
                    dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = RESULT_CANCEL;
                dispose();
            }
        });




        Style.SetButtonStyle(new JButton[]{bookButton, cancelButton,
                checkInDatePicker.getComponentToggleCalendarButton(), checkOutDatePicker.getComponentToggleCalendarButton()});
        mainPanel.add(panel);
        add(mainPanel);

        pack();
        setVisible(true);

    }
}


