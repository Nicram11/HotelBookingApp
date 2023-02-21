package View.Dialogs;

import Model.CustomDate;
import Model.Reservation;
import Model.Room;
import View.Panels.RoomPanel;
import View.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentDialog extends JDialog {
    private RoomPanel roomPanel;
    public PaymentDialog(RoomPanel roomPanel) {
        this.roomPanel = roomPanel;
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("Payment");

        Room selectedRoom = roomPanel.selectedRoom;
        Reservation selectedReservation = roomPanel.selectedReservation;
        int daysToPayFor = CustomDate.GetDayDiff(CustomDate.LocalDateToDate(selectedReservation.checkIn), CustomDate.LocalDateToDate(selectedReservation.checkOut));
        selectedRoom.chargeGuest(selectedReservation, daysToPayFor);

        JPanel mainPanel = new JPanel();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel label = new JLabel();
        label.setText("Należy uregulować kwotę:" + selectedReservation.charge + "zł");
        JButton closeButton = new JButton("Ok");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(label);
        panel.add(closeButton);
        mainPanel.add(panel);
        add(mainPanel);
        Style.SetButtonStyle(new JButton[]{closeButton});
        pack();
        setVisible(true);

    }


}
