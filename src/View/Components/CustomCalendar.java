package View.Components;
import View.Panels.RoomPanel;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

public class CustomCalendar extends JPanel{
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 20);
    private final Color selectedDayBgColor = Color.DARK_GRAY;
    private final Color selectedDayFgColor = Color.white;
    private JPanel monthPanel;
    private JPanel daysPanel;
    private JButton[] days;
    private Calendar calendar;
    private JLabel monthLabel;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int choosenMonth;
    private int firstDayOfCurrentMonth;
    private JLabel actualDateLabel;

    public LocalDate selectedDate;
    private RoomPanel roomPanel;
    private final String[] months = {"Styczeń ", "Luty ", "Marzec ", "Kwiecień ", "Maj ", "Czerwiec ",
            "Lipiec ", "Sierpień ", "Wrzesień ", "Październik ", "Listopad ", "Grudzień "};

    private final String[] daysOfWeek = {"Pon", "Wt", "Śr", "Czw", "Pią", "Sob", "Nie"};

    private static JButton prevSelectedButton = new JButton();
    public LocalDate getSelectedDate() {
        return LocalDate.of(currentYear,currentMonth+1,currentDay);

    }


    public CustomCalendar(RoomPanel roomPanel)
    {
        setCalendarPanelProperties();
        this.roomPanel = roomPanel;
    }
    public CustomCalendar() {

        setCalendarPanelProperties();
    }
    private void setCalendarPanelProperties()
    {
        setPreferredSize(new Dimension(320,220));
        setLayout(new BorderLayout());
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        choosenMonth = currentMonth;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);


        monthPanel = new MonthPanel();
        daysPanel = new DayPanel();
        JPanel actualDatePanel = new JPanel();
      //  actualDatePanel.setBackground();
        actualDateLabel = new JLabel();
        actualDatePanel.add(actualDateLabel);
        actualDateLabel.setText("Wybrano datę: " + currentDay+ " " + months[choosenMonth] + " " + currentYear);
        add(monthPanel, BorderLayout.NORTH);
        add(daysPanel, BorderLayout.CENTER);
        add(actualDatePanel, BorderLayout.SOUTH);

        updateCalendar();

    }

    private void updateCalendar() {

        monthLabel.setText(months[currentMonth] + currentYear);

        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        firstDayOfCurrentMonth = calendar.get(Calendar.DAY_OF_WEEK)  - 2;
        if(firstDayOfCurrentMonth < 0) firstDayOfCurrentMonth = 6;

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);



        for (int i = 0; i < 42; i++) {
            if (i < firstDayOfCurrentMonth || i >= firstDayOfCurrentMonth + daysInMonth) {
                days[i].setText("");
                days[i].setEnabled(false);

            }
            else {
                int day = i - firstDayOfCurrentMonth + 1;
                days[i].setText(Integer.toString(day));
                days[i].setEnabled(true);
            }

            days[i].setBackground(Color.white);
        }
        //Zaznacza wybraną wcześniej datę po wybraniu miesiąca
        if(currentMonth == choosenMonth) {
            prevSelectedButton.setBackground(selectedDayBgColor);
            prevSelectedButton.setForeground(selectedDayFgColor);
        }
        //Odznacza wybraną wcześniej datę jeżeli miesiąc się nie zgadza
        else {
            prevSelectedButton.setBackground(Color.WHITE);
            prevSelectedButton.setForeground(Color.BLACK);
        }


        monthPanel.revalidate();
        monthPanel.repaint();
    }


//Testowanie kalendarza
    public static void main(String[] args) {

        JFrame frame = new JFrame();

        CustomCalendar calendar = new CustomCalendar();
        frame.add(calendar);
        frame.pack();
        frame.setVisible(true);
    }

    class DayPanel extends JPanel{

        public DayPanel() {
            setLayout(new GridLayout(0, 7));


            for (String day : daysOfWeek) {
                JLabel label = new JLabel(day);
                label.setHorizontalAlignment(JLabel.CENTER);
                add(label);
            }
            days = new JButton[42];
            for (int i = 0; i < 42; i++) {
                days[i] = new JButton();
                days[i].setBackground(Color.WHITE);
                days[i].setBorder(null);
                days[i].setFocusable(false);
                days[i].addActionListener(new CalendarDayButtonListener(i));
               add(days[i]);
            }
        }

        class CalendarDayButtonListener implements ActionListener{
            int iterator;


            CalendarDayButtonListener(int i)
            {
                iterator = i;
            }
            public void actionPerformed(ActionEvent e) {
                choosenMonth = currentMonth;
                currentDay = iterator - firstDayOfCurrentMonth+1;
                roomPanel.loadRooms(getSelectedDate());
                days[iterator].setBackground(selectedDayBgColor);
                days[iterator].setForeground(selectedDayFgColor);
                if(prevSelectedButton != days[iterator]){
                    prevSelectedButton.setBackground(Color.white);
                    prevSelectedButton.setForeground(Color.BLACK);
                }

                prevSelectedButton = days[iterator];
                actualDateLabel.setText("Wybrano datę: " + currentDay+ " " + months[choosenMonth] + " " + currentYear);
                System.out.println("Wybrano datę: " + currentDay+ " " + months[currentMonth] + " " + currentYear);
                System.out.println(getSelectedDate());
            }
        }
    }
    class MonthPanel extends JPanel{


        private void SetButtonProperties(JButton button)
        {
            button.setBorder(null);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFont(buttonFont);
        }

        public MonthPanel() {
            setLayout(new FlowLayout());

            JButton prevMonthButton = new JButton("<<");
            JButton nextMonthButton = new JButton(">>");
            SetButtonProperties(prevMonthButton);
            SetButtonProperties(nextMonthButton);

            monthLabel = new JLabel(months[ currentMonth ] + currentYear);

            add(prevMonthButton);
            add(monthLabel);
            add(nextMonthButton);


            nextMonthButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentMonth++;
                    if (currentMonth > 11) {
                        currentMonth = 0;
                        currentYear++;
                    }
                    updateCalendar();
                }
            });
            prevMonthButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentMonth--;
                    if (currentMonth < 0) {
                        currentMonth = 11;
                        currentYear--;
                    }
                    updateCalendar();
                }
            });

        }
    }
}