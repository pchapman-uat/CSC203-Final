public class Reminder {
    private int id;
    public String title;
    public String content;
    
    public Date date;
    Reminder(int id, String title, String content, String date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = new Date();
        this.date.stringDate(date);
    }
}
