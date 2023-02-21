package View.Dialogs;





import View.Dialogs.Validators.DialogDataValidator;
import View.Panels.RoomPanel;
import View.Style;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EditReservationDialog extends JDialog {

    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 2;
    public int result;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private DatePicker checkInDatePicker;
    private DatePicker checkOutDatePicker;
    private JButton bookButton;
    private JButton cancelButton;
    private RoomPanel roomPanel;


    public EditReservationDialog(RoomPanel roomPanel) {
        this.roomPanel = roomPanel;
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Edit Reservation");

        JPanel mainPanel = new JPanel();
        JPanel panel = new JPanel(new GridLayout(0,2));

        panel.add(new JLabel("First Name:"));

        firstNameField = new JTextField(10);
        firstNameField.setText(roomPanel.selectedReservation.guest.name);
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));

        lastNameField = new JTextField();
        lastNameField.setText(roomPanel.selectedReservation.guest.surname);
        panel.add(lastNameField);

        panel.add(new JLabel("Check-in Date:"));

        checkInDatePicker = new DatePicker();
        checkInDatePicker.setDate(roomPanel.selectedReservation.checkIn);
        panel.add(checkInDatePicker);

        panel.add(new JLabel("Check-out Date:"));

        checkOutDatePicker = new DatePicker();
        checkOutDatePicker.setDate(roomPanel.selectedReservation.checkOut);
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


                if (RESULT_OK == DialogDataValidator.EditDialogValidate(roomPanel, EditReservationDialog.this, firstName, lastName, checkInDate, checkOutDate)) {
                    roomPanel.selectedReservation.guest.name = firstNameField.getText();
                    roomPanel.selectedReservation.guest.surname = lastNameField.getText();
                    roomPanel.selectedReservation.checkIn = checkInDatePicker.getDate();
                    roomPanel.selectedReservation.checkOut = checkOutDatePicker.getDate();
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


