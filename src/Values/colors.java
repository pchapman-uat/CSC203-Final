// Package with the values folder
package src.Values;
// Import Java colors, Map, and LinkedHashMap classes
import java.awt.Color;
import java.util.Map;
import java.util.LinkedHashMap;

public class colors {
    // Create a static color object
    public static final Color RED = new Color(255, 0, 0);
    public static final Color ORANGE = new Color(255, 128, 0);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color PURPLE = new Color(128, 0, 128);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    // Create a static map of colors
    public static final Map<String, Color> allColors = new LinkedHashMap<String, Color>() {
        {
            put("RED", RED);
            put("ORANGE", ORANGE);
            put("YELLOW", YELLOW);
            put("GREEN", GREEN);
            put("BLUE", BLUE);
            put("PURPLE", PURPLE);
            put("WHITE", WHITE);
            put("BLACK", BLACK);
        }
    };
}
