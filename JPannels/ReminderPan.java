package JPannels;
import Classes.Date;
import Classes.Reminder;

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
    public ReminderPan(int frameHeight, int frameWidth) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.title.setText("Title");
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


    public void setTitle(String title){
        this.title.setText(title);
    }
    public void setContent(String content){
        this.content.setText(content);
    }
    public void setID(Integer id){
        this.id.setText(String.valueOf(id));
    }
    public void setDate(String date){
        this.date.setText(date);
    }

}
