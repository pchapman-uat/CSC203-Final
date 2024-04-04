package JPannels;

import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ReminderPan extends JPanel{
    private int frameWidth = 0;
    private int frameHeight = 0;

    JTextArea title = new JTextArea();
    JTextArea content = new JTextArea();
    public ReminderPan(int frameHeight, int frameWidth) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.title.setText("Title");
        this.add(this.title);
        this.add(this.content);
    }

    public void setTitle(String title){
        this.title.setText(title);
    }
    public void setContent(String content){
        this.content.setText(content);
    }

}
