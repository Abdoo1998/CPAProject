package GUI;

import java.awt.*;

/**
 * Collection of fonts (Font Objects) and associated constants used throughout the GUI
 * @author gorosgobe
 */
public final class FontCollection {

    private FontCollection() {}

    /** Default font size*/
    public static final int DEFAULT_FONT_SIZE = 16;
    /** Big font size*/
    public static final int BIG_FONT_SIZE = 22;
    /** Default plain font with default font size*/
    public static final Font DEFAULT_FONT_PLAIN = new Font("SansSerif", Font.PLAIN, DEFAULT_FONT_SIZE);
    /** Default bold font with default font size*/
    public static final Font DEFAULT_FONT_BOLD = new Font("SansSerif", Font.BOLD, DEFAULT_FONT_SIZE);
    /** Big bold font */
    public static final Font BIG_BOLD_FONT = new Font("SansSerif", Font.BOLD, BIG_FONT_SIZE);


}
