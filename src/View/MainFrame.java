package View;

import Model.DataSeeding.Json;
import Model.DataSeeding.Seeder;
import View.Panels.HeaderPanel;
import View.Panels.RightPanel;
import View.Panels.RoomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {
    RoomPanel roomPanel;
    public MainFrame()
    {
        Seeder.seed();
        FrameProperties();

        roomPanel = new RoomPanel();
        add(roomPanel,BorderLayout.CENTER);
        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new RightPanel(roomPanel), BorderLayout.EAST);

        this.setVisible(true);

    }
    private void FrameProperties() {
        setTitle("Hotel App");
        setSize(1200,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        getContentPane().setBackground(Color.red);
        setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Json.WriteDataToJson();
                super.windowClosing(e);
            }
        });
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
