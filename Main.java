import java.sql.*;

import javax.swing.*;

import java.awt.FlowLayout;

import java.util.ArrayList;

public class Main {

    public static void queryTable(Statement statement) {
        String test = "SELECT * from reminders";
        ArrayList<Reminder> allReminders = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(test);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String date =  resultSet.getString("date");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                allReminders.add(new Reminder(id, title, content, date));
                System.out.println("Created Note onjects");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db/data.sqlite3");
        Statement statement = connection.createStatement();

        String createTableSQL = "CREATE TABLE IF NOT EXISTS reminders (id INTEGER PRIMARY KEY, title TEXT, content TEXT, date TEXT, day INT, month INT, year INT, priority TEXT DEFAULT WHITE)";
        statement.executeUpdate(createTableSQL);

        int frameWidth = 400;
        int frameHeight = 400;

        JFrame frame = new JFrame();
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);

        JPanel datePannel = new JPanel();

        JTextArea dayInput = new JTextArea();
        dayInput.setSize(frameWidth, 30);
        dayInput.setText("Day");
        JTextArea monthInput = new JTextArea();
        monthInput.setText("Month");
        monthInput.setSize(frameWidth, 30);
        JTextArea yearInput = new JTextArea();
        yearInput.setText("Year");
        yearInput.setSize(frameWidth, 30);

        frame.setLayout(new FlowLayout());
        datePannel.add(dayInput);
        datePannel.add(monthInput);
        datePannel.add(yearInput);

        frame.add(datePannel);

        JButton lookupButton = new JButton();
        lookupButton.addActionListener(e -> queryTable(statement));

        JPanel reminderPanel = new JPanel();
        
        JTextArea title = new JTextArea();
        title.setSize(frameWidth, frameHeight);
        title.setText("Title");

        reminderPanel.add(title);
        frame.add(reminderPanel);
        frame.add(lookupButton);
        frame.update(null);

    }
}