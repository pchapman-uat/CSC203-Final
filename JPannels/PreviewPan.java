package JPannels;
import static mainScreen.Screen.currentReminder;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;


public class PreviewPan extends JPanel {
    private String spacing = " ".repeat(20);
    public JLabel title = new JLabel("", SwingConstants.CENTER);
    public JLabel content = new JLabel("", SwingConstants.CENTER);
    public JLabel date = new JLabel("", SwingConstants.CENTER);
    public PreviewPan(){
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new GridLayout(5,1));
        this.add(this.title);
        this.add(this.date);
        this.add(this.content);
        this.updatePreview();
    }
    public void updatePreview(){
        if(currentReminder != null){
            this.title.setText(this.spacing+currentReminder.title+this.spacing);
            this.date.setText(currentReminder.date.dateString);
            this.content.setText(currentReminder.content);
        }
    }
}