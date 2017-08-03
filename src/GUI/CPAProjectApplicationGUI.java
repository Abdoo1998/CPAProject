package GUI;

import application.Task;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Represents the general CPA Project application and provides methods to show it
 * @author gorosgobe
 */
public class CPAProjectApplicationGUI extends JFrame {

    private JMenuBar menuBar;
    private JTabbedPane tabbedPane;
    private JPanel taskView;
    private JPanel optimalPlanView;

    /* Name of the application, shown at the top of the frame */
    private static final String APPLICATION_NAME = "CPAProject";
    /* Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";
    /*Titles for tabs*/
    private static final String TASK_VIEW_TAB_STRING = "Task View";
    private static final String OPTIMAL_VIEW_TAB_STRING = "Optimal Plan View";
    /*Sizes for components*/
    private static final int APPLICATION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private static final int APPLICATION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
    private static final int MENU_WIDTH = APPLICATION_WIDTH;
    private static final int MENU_HEIGHT = 25;


    /*Holds all Overall Tasks to render*/
    private List<Task> tasks;

    public CPAProjectApplicationGUI() {
        this.tasks = new LinkedList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Initialises and shows the main application GUI JFrame
     */
    public void createAndShowGUI() {

        setTitle(APPLICATION_NAME);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //sets size of frame and color
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_WIDTH));
        setBackground(new Color(255, 255, 255));

        //sets menu bar
        initialiseAndSetJMenuBar();

        //sets double tabbed pane with Task View and Optimal Plan View
        initialiseAndSetTabbedPane();

        //shows the frame
        pack();
        setLocationRelativeTo(null); //centers frame
        setVisible(true);
    }

    private void initialiseAndSetTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        setTaskView();
        setOptimalPlanView();
        tabbedPane.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        tabbedPane.addTab(TASK_VIEW_TAB_STRING, taskView);
        tabbedPane.addTab(OPTIMAL_VIEW_TAB_STRING, optimalPlanView);
        add(tabbedPane);
    }

    private void initialiseAndSetJMenuBar() {
        this.menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuBar.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        setJMenuBar(menuBar);
    }


    private void setTaskView() {
        this.taskView = new JPanel();
        taskView.setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT - MENU_HEIGHT));
        taskView.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    private void setOptimalPlanView() {
        this.optimalPlanView = new JPanel();
        optimalPlanView.setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT - MENU_HEIGHT));
        optimalPlanView.setFont(FontCollection.DEFAULT_FONT_PLAIN);
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
