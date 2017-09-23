package GUI;

import application.OverallTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static GUI.TaskGUI.TOP_DEFAULT_INSETS;

/**
 * Abstract class representing the GUI to select a dependency. The class executes a given action and can supply a
 * message or a warning to the user after executing the action.
 *
 * @author gorosgobe
 */
public abstract class AbstractSelectDependency extends JFrame implements ActionListener {


    /** A reference to the task data panel*/
    private final TaskDataPanel taskDataPanel;
    /** A reference to the Overall Task to remove the dependency from*/
    private OverallTask task;
    /** A reference to a Label with a string indicating which node was selected*/
    private JLabel selectedLabel;
    /** A reference to the selected node in the tree, representing a subtask to remove */
    private JLabel selectedNode;


    //CONSTANTS
    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";

    public AbstractSelectDependency(TaskDataPanel taskDataPanel, OverallTask task, String labelText) {
        this.task = task;
        this.taskDataPanel = taskDataPanel;
        setSelectedLabelAndSelectedNode(labelText);
        setCustomLayout();
    }

    public TaskDataPanel getTaskDataPanel() {
        return taskDataPanel;
    }

    public OverallTask getTask() {
        return task;
    }

    public JLabel getSelectedLabel() {
        return selectedLabel;
    }

    public JLabel getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the labels representing the text informing the user about selection of a node to be removed and the
     * text representation of the node selected.
     */
    private void setSelectedLabelAndSelectedNode(String labelText) {

        this.selectedLabel = new JLabel(labelText + ": ");
        selectedLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //the selectedNode text will be determined by the selection of a particular node in the tree view
        //initial is default overall task
        this.selectedNode = new JLabel(task.getTaskName());
        selectedNode.setFont(FontCollection.DEFAULT_FONT_PLAIN);

    }


    /**
     * Sets the custom layout for the remove dependency GUI.
     */
    private void setCustomLayout() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints selectedLabelConstraints = LayoutUtils.createConstraints(0, 0, TOP_DEFAULT_INSETS);
        add(selectedLabel, selectedLabelConstraints);

        GridBagConstraints selectedNodeStringConstraints = LayoutUtils.createConstraints(1, 0, TOP_DEFAULT_INSETS);
        add(selectedNode, selectedNodeStringConstraints);

        GridBagConstraints scrollPaneConstraints = LayoutUtils.createConstraints(0, 1);
        scrollPaneConstraints.gridwidth = 2;
        scrollPaneConstraints.gridheight = 2;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        //add(treePanel, scrollPaneConstraints);

    }


    /**
     * Initialises and shows the Remove Dependency GUI JFrame
     */
    public void createAndShowGUI() {

        setResizable(false);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //shows the frame
        pack();
        setLocationRelativeTo(null); //centers frame
        setVisible(true);
    }

    /**
     * Closes the frame.
     */
    protected void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
