package View.Dialogs;

import View.Panels.RoomPanel;

import javax.swing.*;

public class CancelReservationDialog extends JDialog {
    private RoomPanel roomPanel;

    public CancelReservationDialog(RoomPanel roomPanel) {

        this.roomPanel  = roomPanel;
        setModal(true);



        pack();
        setVisible(true);
    }

    public void CancelReservation(){
        roomPanel.selectedRoom.reservations.remove(roomPanel.selectedReservation);
    }
}
