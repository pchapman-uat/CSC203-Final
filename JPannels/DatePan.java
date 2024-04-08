package JPannels;
import javax.swing.*;

import Classes.Reminder;

import java.awt.FlowLayout;

public class DatePan extends JPanel {
    private int frameWidth = 0;
    private int frameHeight = 0;

    JTextArea dayInput = new JTextArea();
    JTextArea monthInput = new JTextArea();
    JTextArea yearInput = new JTextArea();

    public DatePan(int frameHeight, int frameWidth){
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;

        this.dayInput.setSize(this.frameWidth, 30);
        this.dayInput.setText("Day");
        
        this.monthInput.setText("Month");
        this.monthInput.setSize(this.frameWidth, 30);

        this.yearInput.setText("Year");
        this.yearInput.setSize(this.frameWidth, 30);
    
        this.setLayout(new FlowLayout());
        this.add(dayInput);
        this.add(monthInput);
        this.add(yearInput);
    }

    public void setDateVals(Reminder reminder){
        this.yearInput.setText(Integer.toString(reminder.date.year));
        this.monthInput.setText(Integer.toString(reminder.date.month));
        this.dayInput.setText(Integer.toString(reminder.date.day));
    }

}