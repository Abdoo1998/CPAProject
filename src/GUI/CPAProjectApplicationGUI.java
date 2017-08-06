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

    /** Field representing the menu bar*/
    private JMenuBar menuBar;
    /** Field representing the tabbed pane*/
    private JTabbedPane tabbedPane;
    /** Field representing the scrollable pane under the Task View tab*/
    private JScrollPane taskView;
    /** Field representing the task panel to be located in the scrollable pane*/
    private JPanel taskPanel;
    /** Field representing the panel under the Optimal Plan View tab*/
    private JPanel optimalPlanView;
    /** List holding all Overall Tasks to render(ed)*/
    private List<OverallTask> tasks;

    /** Name of the application, shown at the top of the frame */
    private static final String APPLICATION_NAME = "CPAProject";
    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";
    /** Title for Task View tab*/
    private static final String TASK_VIEW_TAB_STRING = "Task View";
    /** Title for the Optimal View tab*/
    private static final String OPTIMAL_VIEW_TAB_STRING = "Optimal Plan View";
    /** Application width*/
    private static final int APPLICATION_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    /** Application height*/
    private static final int APPLICATION_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
    /** Menu width*/
    private static final int MENU_WIDTH = APPLICATION_WIDTH;
    /** Menu height*/
    private static final int MENU_HEIGHT = 25;
    /** Maximum number of tasks allowed per row*/
    private static final int MAX_TASK_VIEW_NUM_WIDTH = 4;
    /** Preferred width for overall task view components*/
    private static final int OVERALL_TASK_VIEW_COMPONENT_WIDTH = 250;
    /** Preferred height for overall task view components*/
    private static final int OVERALL_TASK_VIEW_COMPONENT_HEIGHT = OVERALL_TASK_VIEW_COMPONENT_WIDTH;
    /** Maximum number of rows of overall task view components to appear without scrolling*/
    private static final int BOUNDARY_SCROLLABLE_ROWS = 4;
    /** Speed of the scroll bar*/
    private static final int SCROLL_BAR_SPEED = 18;


    /**
     * Constructs the application's main GUI. Initialises and sets the menu bar and the tabbed pane.
     */
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

    /**
     * Gets the list containing all tasks held by the GUI.
     * @return the list containing all tasks in the GUI.
     */
    public List<OverallTask> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task view and to the task list. Recalculates size of scrollable pane with addition
     * of elements.
     * @param task the task to add
     */
    public void addOverallTask(OverallTask task) {
        tasks.add(task);
        Dimension componentDimension = new Dimension(OVERALL_TASK_VIEW_COMPONENT_WIDTH, OVERALL_TASK_VIEW_COMPONENT_HEIGHT);
        OverallTaskViewComponent overallTaskViewComponent = new OverallTaskViewComponent(task, componentDimension);

        //constraints for the nth component, with row and column limits specified by constants
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = (tasks.size() - 1) % MAX_TASK_VIEW_NUM_WIDTH;
        constraints.gridy = (tasks.size() - 1) / MAX_TASK_VIEW_NUM_WIDTH;
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.BASELINE;
        taskPanel.add(overallTaskViewComponent, constraints);

        //recalculates size of task panel in scrollable pane
        if (tasks.size() % MAX_TASK_VIEW_NUM_WIDTH == 0 && tasks.size() > BOUNDARY_SCROLLABLE_ROWS * MAX_TASK_VIEW_NUM_WIDTH) {
            taskPanel.setSize(new Dimension((int) taskPanel.getPreferredSize().getWidth(),
                    (int) taskPanel.getPreferredSize().getHeight() + OVERALL_TASK_VIEW_COMPONENT_HEIGHT));
            taskPanel.revalidate();
        }
    }

    /**
     * Adds all tasks to the Task View. Useful for saved data on file, we parse the file data and construct
     * a list, and then show it on the GUI by calling this method.
     * @param taskList the list of tasks to be shown/added on the GUI
     */
    public void addAllOverallTasks(List<OverallTask> taskList) {
        for (OverallTask t : taskList) {
            addOverallTask(t);
        }
    }

    /**
     * Removes the given task from the task view and redraws the tasks.
     * @param task the task to be removed
     */
    public void removeOverallTask(OverallTask task) {
        //Note: recalculating the position of every task might be more expensive than drawing all tasks again, TBD.
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

    /**
     * Initialises and sets the tabbed pane in the main frame.
     */
    private void initialiseAndSetTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        //set each tab's inside contents
        setTaskView();
        setOptimalPlanView();
        tabbedPane.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //add tabs
        tabbedPane.addTab(TASK_VIEW_TAB_STRING, taskView);
        tabbedPane.addTab(OPTIMAL_VIEW_TAB_STRING, optimalPlanView);
        //add tabbed pane itself to main frame
        add(tabbedPane);
    }

    /**
     * Initialises and sets the menu bar.
     */
    private void initialiseAndSetJMenuBar() {
        this.menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuBar.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        setJMenuBar(menuBar);
    }

    /**
     * Sets the task view, by creating a scrolling pane with its client being a JPanel.
     */
    private void setTaskView() {
        //initially no tasks are provided to task panel, so layout calculations will be made in addTask
        this.taskPanel = new JPanel();
        //sets layout to gridbag layout
        taskPanel.setLayout(new GridBagLayout());
        //initialises scrolling task view with client being task JPanel
        this.taskView = new JScrollPane(taskPanel);
        //sets vertical scrolling
        taskView.createVerticalScrollBar();
        taskView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //blocks horizontal scrolling
        taskView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        taskView.setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT - MENU_HEIGHT));
        taskView.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //makes scrolling down and up the task view faster
        taskView.getVerticalScrollBar().setUnitIncrement(SCROLL_BAR_SPEED);

    }

    /**
     * Sets the optimal plan view.
     */
    private void setOptimalPlanView() {
        //TODO: Pablo, complete.
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
