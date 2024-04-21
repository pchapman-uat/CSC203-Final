// Package with the Classes folder
package Classes;
// Import the java Color classes
import java.awt.Color;
// Import SQL Clases
import java.sql.*;
// Import the java.text.SimpleDateFormat class
import java.text.SimpleDateFormat;
// Import the Array List class
import java.util.ArrayList;
// Import the calandar class
import java.util.Calendar;
// Import the custom Color class
import Values.colors;
// Import the show message dialog function
import static javax.swing.JOptionPane.showMessageDialog;
// Import the current reminder
import static mainScreen.Screen.currentReminder;

public class SQL {
    // Declare the connection and statement
    public Connection connection = null;
    public Statement statement = null;
    // Declare the create table SQL
    private String createTableSQL = "CREATE TABLE IF NOT EXISTS reminders (id INTEGER PRIMARY KEY, title TEXT, content TEXT, date TEXT, day INT, month INT, year INT, priority TEXT DEFAULT WHITE)";
    public SQL(){
        // Attempt to connect to the database
        try {
            // Load the driver
            this.connection = DriverManager.getConnection("jdbc:sqlite:db/data.sqlite3");
            // Create a statement
            this.statement = connection.createStatement();
            // Create the table if it doesn't exist
            this.statement.executeUpdate(this.createTableSQL);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    // Get a list of the current reminders
    public ArrayList<Reminder> getTodayReminders(){
        // Initialize the array list
        ArrayList<Reminder> todayReminders = new ArrayList<>();
        // Get the current date
        java.util.Date now = Calendar.getInstance().getTime();
        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formatedDate = dateFormat.format(now);
        // Create the query
        String query = "SELECT * from reminders WHERE date='"+formatedDate+"'";
        try {
            // Execute the query
            ResultSet resultSet = this.statement.executeQuery(query);
            todayReminders = this.createReminder(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        // Return the array list
        return todayReminders;
    }
    // Get the reminders from the database
    public ArrayList<Reminder> queryTable() {
        String test = "SELECT * from reminders";
        // Initialize the array list
        ArrayList<Reminder> allReminders = new ArrayList<>();
        try {
            // Execute the query
            ResultSet resultSet = this.statement.executeQuery(test);
            allReminders = this.createReminder(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the array list
        return allReminders;
    }
    private ArrayList<Reminder> createReminder(ResultSet resultSet) throws SQLException{
        // Create array list for reminders
        ArrayList<Reminder> queryReminders = new ArrayList<>();
        // Initilaze the size of the array list
        int size = 0;
        // Using the result set, create a reminder object for each row
        while (resultSet.next()){
            size++;
            int id = resultSet.getInt("id");
            String date =  resultSet.getString("date");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            String priorityString = resultSet.getString("priority");
            Color priority = colors.allColors.get(priorityString);
            Reminder reminder = new Reminder(id, title, content, date, priority);
            reminder.setPriorityString(priorityString);
            queryReminders.add(reminder);

            System.out.println("Created Note objects");
        }
        if(size == 0) {
            showMessageDialog(null,"No Reminders Found");
            return null;
        } else {
            return queryReminders;
        }
    }
    public void addItem() {
        // Create a new note object
        System.out.println("Save as note");
        // Insert the note into the database
        String statement = "INSERT INTO reminders (title, content, date, day, month, year, priority) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(statement)) {
            // Prepare the statement using the current reminder
            pstmt.setString(1, currentReminder.title);
            pstmt.setString(2, currentReminder.content);
            pstmt.setString(3, currentReminder.date.dateString);
            pstmt.setInt(4, currentReminder.date.day);
            pstmt.setInt(5, currentReminder.date.month);
            pstmt.setInt(6, currentReminder.date.year);
            pstmt.setString(7, currentReminder.priorityString);
            // Execute the statement
            pstmt.executeUpdate();
            // Get the id of the note
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    // Set the id of the current reminder
                    currentReminder.id = id;
                } else {
                    throw new SQLException("Creating reminder failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Update the current reminder
    public void updateItem(){
        System.out.println("Updating Note");
        // Create the statement
        String statement = "UPDATE reminders SET title = ?, content = ?, day = ?, month = ?, year = ?, date = ?, priority = ? WHERE id = ?";
        try (PreparedStatement pstmt = this.connection.prepareStatement(statement)) {
            {
                // Prepare the statement using the current reminder
                pstmt.setString(1, currentReminder.title);
                pstmt.setString(2, currentReminder.content);
                pstmt.setInt(3, currentReminder.date.day);
                pstmt.setInt(4, currentReminder.date.month);
                pstmt.setInt(5, currentReminder.date.year);
                pstmt.setString(6, currentReminder.date.dateString);
                pstmt.setString(7, currentReminder.priorityString);
                pstmt.setInt(8, currentReminder.id);
   
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } ;
    }
}
