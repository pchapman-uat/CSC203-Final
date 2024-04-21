// Package with the classes folder
package Classes;
// Import the Java Calendar class
import java.util.Calendar;
// Import the Java Date class
import java.text.SimpleDateFormat;
public class Date {
    // Declare the variables
    public int day;
    public int month;
    public int year;
    public String dateString;

    // Using intagers, set the date as well as the date string
    public void intDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dateString = this.day + "/" + this.month + "/" + this.year;
    }
    
    // Using strings, set the date as well as the date string
    public void stringDate(String dateString) {
        // Split the array for each "/", and convert to integers
        String[] dateArr = dateString.split("/");
        this.month = Integer.parseInt(dateArr[0]);
        this.day = Integer.parseInt(dateArr[1]);
        this.year = Integer.parseInt(dateArr[2]);
        this.dateString = this.month + "/" + this.day + "/" + this.year;
    }
    // Get todays date
    public void todayDate(){
        // Get the current date
        java.util.Date now = Calendar.getInstance().getTime();
        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formatedDate = dateFormat.format(now);
        // Update using the string date
        this.stringDate(formatedDate);
    }

}
