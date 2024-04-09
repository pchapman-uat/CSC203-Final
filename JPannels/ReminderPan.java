package JPannels;
import Classes.Date;
import Classes.Reminder;
import static mainScreen.Screen.currentReminder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ReminderPan extends JPanel{
    private int frameWidth = 0;
    private int frameHeight = 0;

    JTextArea title = new JTextArea();
    JTextArea content = new JTextArea();
    JLabel date = new JLabel();
    JLabel id = new JLabel();
    public DatePan datePannel = new DatePan(this.frameHeight, this.frameWidth);

    public ReminderPan(int frameHeight, int frameWidth) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.title.setText("Title");
        this.add(this.datePannel);
        this.add(this.title);
        this.add(this.content);
        this.add(date);
        this.add(id);
    }
    public Reminder createReminderObj(){
        String title = this.title.getText();
        String content = this.content.getText();
        String date = this.date.getText();
        int id = Integer.parseInt(this.id.getText());
        return new Reminder(id, title, content, date);
    }

    public void setValues(){
        
        this.setTitle(currentReminder.title);
        this.setContent(currentReminder.content);
        this.setID(currentReminder.id);
        this.setDate(currentReminder);
    }

    private void setTitle(String title){
        this.title.setText(title);
    }
    private void setContent(String content){
        this.content.setText(content);
    }
    private void setID(Integer id){
        this.id.setText(String.valueOf(id));
    }
    private void setDate(Reminder reminder){
        this.date.setText(reminder.date.dateString);
        this.datePannel.setDateVals(reminder);
    }

}
