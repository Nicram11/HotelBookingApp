package View.Dialogs.Validators;

import View.Panels.RoomPanel;

import javax.swing.*;
import java.time.LocalDate;

import static View.Dialogs.AddReservationDialog.RESULT_ERROR;
import static View.Dialogs.AddReservationDialog.RESULT_OK;

public class DialogDataValidator {


    private static int result;

    public static int AddDialogValidate(RoomPanel roomPanel, JDialog dialog, String firstName, String lastName, LocalDate checkInDate, LocalDate checkOutDate){

        if (firstName.isEmpty() || lastName.isEmpty() || checkInDate == null || checkOutDate == null) {
            JOptionPane.showMessageDialog(dialog, "Please enter all required fields", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else if( !roomPanel.selectedRoom.isFree(checkInDate, checkOutDate)) {
            JOptionPane.showMessageDialog(dialog, "Selected Room is already occupied in selected date", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else if( checkInDate.equals(checkOutDate) )
        {
            JOptionPane.showMessageDialog(dialog, "Check-In date cant be the same as check-out date ", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else if( checkInDate.isAfter(checkOutDate) )
        {
            JOptionPane.showMessageDialog(dialog, "Check-In date cant be after check-out date ", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else
            return RESULT_OK;


    };

    public static int EditDialogValidate(RoomPanel roomPanel, JDialog dialog, String firstName, String lastName, LocalDate checkInDate, LocalDate checkOutDate){

        if (firstName.isEmpty() || lastName.isEmpty() || checkInDate == null || checkOutDate == null) {
            JOptionPane.showMessageDialog(dialog, "Please enter all required fields", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else if( !roomPanel.selectedRoom.isFree(checkInDate, checkOutDate, roomPanel.selectedReservation)) {
            JOptionPane.showMessageDialog(dialog, "Selected Room is already occupied in selected date", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else if( checkInDate.equals(checkOutDate) )
        {
            JOptionPane.showMessageDialog(dialog, "Check-In date cant be the same as check-out date ", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else if( checkInDate.isAfter(checkOutDate) )
        {
            JOptionPane.showMessageDialog(dialog, "Check-In date cant be after check-out date ", "Error", JOptionPane.ERROR_MESSAGE);
            return RESULT_ERROR;
        }
        else
            return RESULT_OK;


    };

}
