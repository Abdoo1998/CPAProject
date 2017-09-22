package GUI;

import application.OverallTask;

import javax.swing.*;

/**
 * Represents the GUI to remove a depencency from an Overall Task in the task's data panel.
 *
 * @author gorosgobe
 */
public class RemoveDependencyGUI extends JFrame {

    /** A reference to the Overall Task to remove the dependency from*/
    private OverallTask task;
    /** A reference to the dropdown label*/
    private JLabel dropdownLabel;
    /** A reference to the main task dropdown*/
    private JComboBox taskDropdown;
    /** A reference to a Label with a string indicating which node was selected*/
    private JLabel selectedLabel;
    /** A reference to the selected node in the tree, representing a subtask to remove */
    private JLabel selectedNode;
    /** A reference to the scroll pane with the tree view*/
    private JScrollPane treePanel;
    /** A reference to the tree view*/
    private JTree tree;
    /** A reference to the remove button*/
    private JButton button;

    //CONSTANTS
    /** Title of the frame*/
    private static final String FRAME_TITLE = "Remove Dependency";
    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";
    /** String representing the string on the button*/
    private static final String BUTTON_STRING = "Remove";
    /** String representing the string on the label*/
    private static final String LABEL_MESSAGE = "Select main task";
    /** String representing the task dropdown message*/
    private static final String TASK_DROPDOWN_STRING = "Task dropdown";
    /** String representing the selected node in the tree view (subTask)*/
    private static final String SELECTED_NODE_STRING = "Remove dependency";

    public RemoveDependencyGUI(OverallTask task) {
        this.task = task;

    }

    /**
     * Initialises and shows the Remove Dependency GUI JFrame
     */
    public void createAndShowGUI() {

        setTitle(FRAME_TITLE);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //shows the frame
        pack();
        setLocationRelativeTo(null); //centers frame
        setVisible(true);
    }

}
