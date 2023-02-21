package View;

import javax.swing.*;
import java.awt.*;

public class Style {
    public static Font headerFont = new Font("SansSerif", Font.BOLD, 48);
    public static void SetButtonStyle(JButton[] buttons){
        for(JButton button : buttons){
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
        }
    }
    public static void EnableButtons(boolean bool, JButton[] buttons) {
        for(JButton button : buttons){
            button.setEnabled(bool);
        }
    }

}
