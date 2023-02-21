package View.Panels;

import Model.Reservation;
import Model.Room;
import View.Panels.PanelListeners.ButtonsPanelButtonsListener;
import View.CustomTableCellRenderer;
import View.Style;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomPanel extends JPanel {
    public LocalDate selectedDate;
    public ArrayList<Reservation> displayedReservations ;
    private JTable roomTable;
    private ButtonsPanel buttonsPanel;
    private DefaultTableModel roomTableModel;
    public Reservation selectedReservation;
    public Room selectedRoom;
    private ButtonsPanelButtonsListener buttonsPanelButtonsListener = new ButtonsPanelButtonsListener(this);

    public RoomPanel() {
        setLayout(new BorderLayout());

        createRoomTableAndSetProperties();

        loadRooms(LocalDate.now());

        add(new JScrollPane(roomTable), BorderLayout.CENTER);

        buttonsPanel = new ButtonsPanel();
        add(buttonsPanel, BorderLayout.SOUTH);




    }
    private void createRoomTableAndSetProperties(){
        String[] columnNames = {"Room ","Room Type", "Status" ,"Guest Name", "Phone Number", "Check-In", "Check-Out"};
        roomTableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(roomTableModel);
         /*      //roomTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        //roomTable.getTableHeader().setBackground(new Color(32,136,203));
        roomTable.getTableHeader().setBackground(Color.DARK_GRAY);
        roomTable.getTableHeader().setForeground(new Color(255,255,255));
        //roomTable.getSelectedRow();
     //   roomTable.setCellSelectionEnabled(false);
        roomTable.setGridColor(Color.DARK_GRAY);
*/
        roomTable.setRowHeight(25);
        roomTable.setDefaultEditor(Object.class, null);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(roomTable.getSelectedRow() == -1)
                {
                    buttonsPanel.paymentButton.setEnabled(false);
                    buttonsPanel.editButton.setEnabled(false);
                    Style.EnableButtons(false, new JButton[]{buttonsPanel.addReservationButton,
                            buttonsPanel.cancelReservationButton, buttonsPanel.paymentButton, buttonsPanel.editButton});
                    selectedRoom = null;
                    selectedReservation = null;
                }
                else {
                    Style.EnableButtons(true, new JButton[]{buttonsPanel.addReservationButton,
                            buttonsPanel.cancelReservationButton, buttonsPanel.paymentButton, buttonsPanel.editButton});

                    selectedRoom = Room.rooms.get((int)roomTable.getValueAt(roomTable.getSelectedRow(),0));
                    selectedReservation = displayedReservations.get(((int)roomTable.getValueAt(roomTable.getSelectedRow(),0)));
                }
            }
        });

    }



    public void loadRooms(LocalDate selectedDate) {
        this.selectedDate = selectedDate ;

        roomTableModel.setRowCount(0);
        roomTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer(selectedDate));
        displayedReservations = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Room room : Room.rooms) {
            Object[] status = room.CheckStatus(selectedDate);
            Reservation reservation = (Reservation)status[1];
            displayedReservations.add(reservation);

            if(reservation == null)
                roomTableModel.addRow(new Object[] {
                        room.roomId,
                        room.roomType.toString(),
                        status[0],
                        "----",
                        "----",
                        "----",
                        "----"
                });

            else
                roomTableModel.addRow(new Object[] {
                        room.roomId,
                        room.roomType.toString(),
                        status[0],
                        reservation.guest.surname,
                        reservation.guest.phoneNumeber,
                        reservation.checkIn.format(formatter),
                        reservation.checkOut.format(formatter)
                });
        }
    }

    class ButtonsPanel extends JPanel {
        private JButton paymentButton = new JButton("Payment");
        private JButton editButton = new JButton("Edit");
        private JButton addReservationButton = new JButton("Add");
        private JButton cancelReservationButton = new JButton("Cancel");

        public ButtonsPanel() {
            AddButtonsToPanel();

            Style.EnableButtons(false,
                    new JButton[]{addReservationButton, paymentButton, cancelReservationButton, editButton});

            Style.SetButtonStyle(new JButton[]{addReservationButton, paymentButton, cancelReservationButton, editButton});

        }

        private void AddButtonsToPanel() {
            addReservationButton.addActionListener(buttonsPanelButtonsListener);
            add(addReservationButton);

            paymentButton.addActionListener(buttonsPanelButtonsListener);
            add(paymentButton);

            editButton.addActionListener(buttonsPanelButtonsListener);
            add(editButton);

            cancelReservationButton.addActionListener(buttonsPanelButtonsListener);
            add(cancelReservationButton);
        }


    }
}
