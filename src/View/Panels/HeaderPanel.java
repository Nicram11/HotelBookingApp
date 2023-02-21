package View.Panels;

import View.Style;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel(){

        JLabel headerText = new JLabel("HOTEL APP");
        headerText.setFont(Style.headerFont);
        add(headerText);
        setPreferredSize(new Dimension(500,70));
        setBackground(Color.white);
    }
}