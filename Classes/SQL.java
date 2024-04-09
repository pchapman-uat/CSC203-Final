package Classes;
import java.sql.*;
import java.util.ArrayList;

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
    

    public ArrayList<Reminder> queryTable() {
        String test = "SELECT * from reminders";
        ArrayList<Reminder> allReminders = new ArrayList<>();
        try {
            ResultSet resultSet = this.statement.executeQuery(test);
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
        return allReminders;
    }

    public void updateItem(){
        System.out.println("Updating Note");
        String statement = "UPDATE reminders SET title = ?, content = ? WHERE id = ?";
        try (PreparedStatement pstmt = this.connection.prepareStatement(statement)) {
            {
                pstmt.setString(1, currentReminder.title);
                pstmt.setString(2, currentReminder.content);
                pstmt.setInt(3, currentReminder.id);
                // TODO: Update date values
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } ;
    }
}
