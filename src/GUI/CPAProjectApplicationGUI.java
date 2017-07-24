package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Represents the general CPA Project application and provides methods to show it
 * @author gorosgobe
 */
public class CPAProjectApplicationGUI extends JFrame {

    /* Name of the application, shown at the top of the frame */
    private static final String APPLICATION_NAME = "CPAProject";
    /* Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";

    /**
     * Initialises and shows the main application GUI JFrame
     */
    public void createAndShowGUI() {

        setTitle(APPLICATION_NAME);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //sets size of frame and color
        setPreferredSize(new Dimension(800, 800));
        setBackground(new Color(255, 255, 255));

        //shows the frame
        pack();
        setVisible(true);
    }

    /**
     * Creates a JMenu with the name given and the actions specified by the map given
     *
     * @param menuName the name of the menu
     * @param map the mapping from actions to their names
     * @return the JMenu specified by the names and the mapping from actions to their names
     */
    public JMenu createMenu(String menuName, Map<Action, String> map) {

        JMenu menu = new JMenu(menuName);

        for (Action act : map.keySet()) {
            act.putValue(Action.NAME, map.get(act));
            menu.add(act);
        }

        return menu;
    }


}
