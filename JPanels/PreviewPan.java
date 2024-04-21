// Package with the JPanels folder
package JPanels;
// Import the current reminder
import static mainScreen.Screen.currentReminder;
// Import the frame width
import static mainScreen.Screen.frameWidth;
// Import the Grid format class and Swing
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

// Import the color values
import Values.colors;
// Import Java AWT classes
import java.awt.*;

public class PreviewPan extends JPanel {
    // Declare the title, content, date, and priority labels
    private String spacing = " ".repeat(20);
    public JTextArea title = new JTextArea();
    public JTextArea content = new JTextArea();
    public JTextArea date = new JTextArea();
    public JLabel space = new JLabel(spacing.repeat(4));
    // Declare the priority combo box
    // A combo box is a drop down menu
    public JComboBox<String> priority = new JComboBox<String>(colors.allColors.keySet().toArray(new String[0]));


    // Constructor for the preview panel
    public PreviewPan(){
        // Set the background color
        this.setBackground(new Color(255, 255, 255));
        // Use GridBagLayout for flexible placement
        this.setLayout(new GridBagLayout()); 
        // Create a grid bag constraints object
        GridBagConstraints gbc = new GridBagConstraints();
        // Set the grid bag constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Center alignment
        gbc.anchor = GridBagConstraints.CENTER; 
        // Add some padding
        gbc.insets = new Insets(5, 5, 5, 5); 
        
        // Add title
        this.add(title, gbc);
        gbc.gridy++;
        
        // Add date
        this.add(date, gbc);
        gbc.gridy++;
        
        // Add content
        gbc.fill = GridBagConstraints.VERTICAL;
        content.setSize((frameWidth - 50), 300);
        content.setLineWrap(true); // Enable line wrapping
        content.setWrapStyleWord(true); // Enable word wrapping
        this.add(content, gbc);

        // Add priority
        this.priority.setSelectedIndex(6);
        this.priority.addActionListener(e -> this.colorHandle());
        gbc.gridy++;
        this.add(this.priority, gbc);

        // Add space
        gbc.gridy++;
        this.add(space, gbc);
        // Update the preview with the current reminder
        this.updatePreview();
    }
    // Handle color changes
    public void colorHandle() {
        // Get the current color from the combo box
        currentReminder.priority = colors.allColors.get(this.priority.getSelectedItem());
        // Convert the color to a string
        currentReminder.priorityString = this.priority.getSelectedItem().toString();
        // Update the preview
        this.updateReminder();
    }
    // Update the preview
    public void updatePreview(){
        // Update the preview with the current reminder, if there is one
        if(currentReminder != null){
            // Set the Title, Date Content and Background color of the preview 
            this.title.setText(currentReminder.title);
            this.date.setText(currentReminder.date.dateString);
            this.content.setText(currentReminder.content);
            this.setBackground(currentReminder.priority);
        }
    }
    // Update the loaded reminder
    public void updateReminder() {
        // Set the title, content, and date of the loaded reminder
        currentReminder.title = this.title.getText();
        currentReminder.content = this.content.getText();
        currentReminder.date.stringDate(this.date.getText());
    }

}
