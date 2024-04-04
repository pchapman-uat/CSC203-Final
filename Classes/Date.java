package Classes;

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
        // NOTE: Year is not formated, meaning the year is not always 4 digits long
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
        this.dateString = this.day + "/" + this.month + "/" + this.year;
    }

}
