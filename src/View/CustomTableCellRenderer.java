package View;

import Model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.beans.Encoder;
import java.time.LocalDate;
import java.util.Date;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private LocalDate selectedDate;
    public CustomTableCellRenderer(LocalDate selectedDate)
    {
        this.selectedDate= selectedDate;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if ((Room.rooms.get(row).CheckStatus(selectedDate)[0] == "Room Free")){
            c.setBackground(Color.green);
        }
        else {
            c.setBackground(Color.decode("#F3533A"));
        }

        if(isSelected) {
            setBackground(Color.darkGray);
            setForeground(Color.white);
        }
        else{
            setForeground(Color.black);
        }
        return c;
    }
}