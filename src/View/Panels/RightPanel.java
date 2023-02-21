package View.Panels;

import View.Components.CustomCalendar;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    public RightPanel(RoomPanel roomPanel) {
        CustomCalendar calendar =  new CustomCalendar(roomPanel);
        add(calendar);


        setBackground(Color.white);
        setPreferredSize(new Dimension(300,0));

    }
}