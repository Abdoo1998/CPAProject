package GUI;

import application.OverallTask;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents the general CPA Project application and provides methods to show it
 * @author gorosgobe
 */
public class CPAProjectApplicationGUI extends JFrame {

    private JMenuBar menuBar;
    private JTabbedPane tabbedPane;
    private JScrollPane taskView;
    private JPanel taskPanel;
    private JPanel optimalPlanView;
    /*Holds all Overall Tasks to render*/
    private List<OverallTask> tasks;
    //private List<OverallTaskViewComponent> overallTaskViewComponentList;

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
    private static final int MAX_TASK_VIEW_NUM_WIDTH = 4;
    private static final int OVERALL_TASK_VIEW_COMPONENT_WIDTH = 250;
    private static final int OVERALL_TASK_VIEW_COMPONENT_HEIGHT = OVERALL_TASK_VIEW_COMPONENT_WIDTH;
    private static final int BOUNDARY_SCROLLABLE_ROWS = 4;
    private static final int SCROLL_BAR_SPEED = 18;


    public CPAProjectApplicationGUI() {
        this.tasks = new LinkedList<>();

        //sets size of frame and color
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_WIDTH));
        setBackground(new Color(255, 255, 255));

        //sets menu bar
        initialiseAndSetJMenuBar();

        //sets double tabbed pane with Task View and Optimal Plan View
        initialiseAndSetTabbedPane();
    }

    public List<OverallTask> getTasks() {
        return tasks;
    }

    public void addOverallTask(OverallTask task) {
        tasks.add(task);
        Dimension componentDimension = new Dimension(OVERALL_TASK_VIEW_COMPONENT_WIDTH, OVERALL_TASK_VIEW_COMPONENT_HEIGHT);
        OverallTaskViewComponent overallTaskViewComponent = new OverallTaskViewComponent(task, componentDimension);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = (tasks.size() - 1) % MAX_TASK_VIEW_NUM_WIDTH;
        constraints.gridy = (tasks.size() - 1) / MAX_TASK_VIEW_NUM_WIDTH;
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.BASELINE;
        taskPanel.add(overallTaskViewComponent, constraints);

        if (tasks.size() % MAX_TASK_VIEW_NUM_WIDTH == 0 && tasks.size() > BOUNDARY_SCROLLABLE_ROWS * MAX_TASK_VIEW_NUM_WIDTH) {
            taskPanel.setSize(new Dimension((int) taskPanel.getPreferredSize().getWidth(),
                    (int) taskPanel.getPreferredSize().getHeight() + OVERALL_TASK_VIEW_COMPONENT_HEIGHT));
            taskPanel.revalidate();
        }


    }

    public void removeOverallTask(OverallTask task) {
        //TODO: PABLO
        tasks.remove(task);
    }


    /**
     * Initialises and shows the main application GUI JFrame
     */
    public void createAndShowGUI() {

        setTitle(APPLICATION_NAME);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

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
        //initially no tasks are provided to task panel, so layout calculations will be made in addTask
        this.taskPanel = new JPanel();
        //sets layout to gridbag layout
        taskPanel.setLayout(new GridBagLayout());

        this.taskView = new JScrollPane(taskPanel);
        taskView.createVerticalScrollBar();
        taskView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        taskView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        taskView.setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT - MENU_HEIGHT));
        taskView.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //makes scrolling down and up the task view faster
        taskView.getVerticalScrollBar().setUnitIncrement(SCROLL_BAR_SPEED);

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
