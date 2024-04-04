package JPannels;
import javax.swing.*;
import java.awt.FlowLayout;

public class Date extends JPanel {
    private int frameWidth = 0;
    private int frameHeight = 0;

    JTextArea dayInput = new JTextArea();
    JTextArea monthInput = new JTextArea();
    JTextArea yearInput = new JTextArea();

    public Date(int frameHeight, int frameWidth){
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;

        dayInput.setSize(this.frameWidth, 30);
        dayInput.setText("Day");
        
        monthInput.setText("Month");
        monthInput.setSize(this.frameWidth, 30);

        yearInput.setText("Year");
        yearInput.setSize(this.frameWidth, 30);
    
        this.setLayout(new FlowLayout());
        this.add(dayInput);
        this.add(monthInput);
        this.add(yearInput);
    }
}
