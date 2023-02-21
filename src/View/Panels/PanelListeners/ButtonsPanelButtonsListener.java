package View.Panels.PanelListeners;

import View.Dialogs.AddReservationDialog;
import View.Dialogs.EditReservationDialog;
import View.Dialogs.PaymentDialog;
import View.Panels.RoomPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanelButtonsListener implements ActionListener {
    private RoomPanel roomPanel;
    private EditReservationDialog editReservationDialog;
    private AddReservationDialog addReservationDialog;
    private PaymentDialog paymentDialog;
    public ButtonsPanelButtonsListener(RoomPanel roomPanel){
        this.roomPanel = roomPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if (e.getActionCommand().equals("Add")) {
                addReservationDialog = new AddReservationDialog(roomPanel);
        }
        else if (roomPanel.selectedReservation == null) {
            JOptionPane.showMessageDialog(roomPanel, "Please select an existing reservation",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (e.getActionCommand().equals("Payment")) {
                paymentDialog = new PaymentDialog(roomPanel);
       }

        else if (e.getActionCommand().equals("Edit")) {
                editReservationDialog = new EditReservationDialog(roomPanel);
          /*  if (dialog.getResult() == AddReservationDialog.RESULT_OK) {
                Reservation reservation = dialog.getReservation();*/
        }

        else if (e.getActionCommand().equals("Cancel")) {
                roomPanel.selectedRoom.reservations.remove(roomPanel.selectedReservation);
                roomPanel.loadRooms(roomPanel.selectedDate);
               // cancelReservationDialog = new CancelReservationDialog(roomPanel);
        }

    }


}
