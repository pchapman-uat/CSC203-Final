package Classes;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import static javax.swing.JOptionPane.showMessageDialog;
import static mainScreen.Screen.currentReminder;

public class SQL {
    public Connection connection = null;
    public Statement statement = null;
    private String createTableSQL = "CREATE TABLE IF NOT EXISTS reminders (id INTEGER PRIMARY KEY, title TEXT, content TEXT, date TEXT, day INT, month INT, year INT, priority TEXT DEFAULT WHITE)";
    public SQL(){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:db/data.sqlite3");
            this.statement = connection.createStatement();
            this.statement.executeUpdate(this.createTableSQL);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Reminder> getTodayReminders(){
        ArrayList<Reminder> todayReminders = new ArrayList<>();
        java.util.Date now = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formatedDate = dateFormat.format(now);

        String query = "SELECT * from reminders WHERE date='"+formatedDate+"'";
        try {
            ResultSet resultSet = this.statement.executeQuery(query);
            todayReminders = this.createReminder(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return todayReminders;
    }
    public ArrayList<Reminder> queryTable() {
        String test = "SELECT * from reminders";
        ArrayList<Reminder> allReminders = new ArrayList<>();
        try {
            ResultSet resultSet = this.statement.executeQuery(test);
            allReminders = this.createReminder(resultSet);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allReminders;
    }
    private ArrayList<Reminder> createReminder(ResultSet resultSet) throws SQLException{
        ArrayList<Reminder> queryReminders = new ArrayList<>();
        int size = 0;
        while (resultSet.next()){
            size++;
            int id = resultSet.getInt("id");
            String date =  resultSet.getString("date");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            queryReminders.add(new Reminder(id, title, content, date));
            System.out.println("Created Note onjects");
        }
        if(size == 0) showMessageDialog(null,"No Reminders Found");
        return queryReminders;
    }
    public void addItem() {
        System.out.println("Save as note");
        String statement = "INSERT INTO reminders (title, content, date, day, month, year) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(statement)) {
            pstmt.setString(1, currentReminder.title);
            pstmt.setString(2, currentReminder.content);
            pstmt.setString(3, currentReminder.date.dateString);
            pstmt.setInt(4, currentReminder.date.day);
            pstmt.setInt(5, currentReminder.date.month);
            pstmt.setInt(6, currentReminder.date.year);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1); // Assuming the ID is of type LONG
                    // Now you can use the id for further processing or store it in your Reminder object
                    currentReminder.id = id;
                } else {
                    throw new SQLException("Creating reminder failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateItem(){
        System.out.println("Updating Note");
        String statement = "UPDATE reminders SET title = ?, content = ?, day = ?, month = ?, year = ?, date = ? WHERE id = ?";
        try (PreparedStatement pstmt = this.connection.prepareStatement(statement)) {
            {
                pstmt.setString(1, currentReminder.title);
                pstmt.setString(2, currentReminder.content);
                pstmt.setInt(3, currentReminder.date.day);
                pstmt.setInt(4, currentReminder.date.month);
                pstmt.setInt(5, currentReminder.date.year);
                pstmt.setString(6, currentReminder.date.dateString);
                pstmt.setInt(7, currentReminder.id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } ;
    }
}
