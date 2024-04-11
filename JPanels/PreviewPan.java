package JPanels;
import static mainScreen.Screen.currentReminder;
import static mainScreen.Screen.frameWidth;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.*;

public class PreviewPan extends JPanel {
    private String spacing = " ".repeat(20);
    public JTextArea title = new JTextArea();
    public JTextArea content = new JTextArea();
    public JTextArea date = new JTextArea();
    public JLabel space = new JLabel(spacing.repeat(4));
    
    public PreviewPan(){
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        
        // Add title
        this.add(title, gbc);
        gbc.gridy++;
        
        // Add date
        this.add(date, gbc);
        gbc.gridy++;
        
        gbc.fill = GridBagConstraints.VERTICAL;
        content.setSize((frameWidth - 50), 300);
        content.setLineWrap(true); // Enable line wrapping
        content.setWrapStyleWord(true); // Enable word wrapping
        this.add(content, gbc);
        
        // Add space
        gbc.gridy++;
        this.add(space, gbc);

        this.updatePreview();
    }
    
    public void updatePreview(){
        if(currentReminder != null){
            this.title.setText(currentReminder.title);
            this.date.setText(currentReminder.date.dateString);
            this.content.setText(currentReminder.content);
        }
    }
    
    public void updateReminder() {
        currentReminder.title = this.title.getText();
        currentReminder.content = this.content.getText();
        currentReminder.date.stringDate(this.date.getText());
    }
}
