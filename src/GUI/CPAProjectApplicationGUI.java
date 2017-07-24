package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Represents the general CPA Project application and provides methods to show it
 * @author gorosgobe
 */
public class CPAProjectApplicationGUI extends JFrame {

    private static final String APPLICATION_NAME = "CPAProject";
    private static final String ICON_PATH = "GUI/images/mainIcon.png";

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

    private JMenu createMenu(String menuName, Map<Action, String> map) {

        JMenu menu = new JMenu(menuName);

        for (Action act : map.keySet()) {
            act.putValue(Action.NAME, map.get(act));
            menu.add(act);
        }

        return menu;
    }


}
