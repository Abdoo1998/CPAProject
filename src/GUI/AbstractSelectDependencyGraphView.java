package GUI;


import application.OverallTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static GUI.TaskGUI.TOP_DEFAULT_INSETS;

public abstract class AbstractSelectDependencyGraphView extends GraphView implements ActionListener {

    /** A reference to the task data panel*/
    private TaskDataPanel taskDataPanel;
    /** A reference to a Label with a string indicating which node was selected*/
    private JLabel selectedLabel;
    /** A reference to the selected node in the tree, representing a subtask to remove */
    private JLabel selectedNode;


    //CONSTANTS
    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";

    public AbstractSelectDependencyGraphView(String title, OverallTask task, TaskDataPanel taskDataPanel) {
        super(title, task);
        this.taskDataPanel = taskDataPanel;
        this.selectedLabel = new JLabel();
        this.selectedNode = new JLabel();

        setCustomLayout();
    }

    public TaskDataPanel getTaskDataPanel() {
        return taskDataPanel;
    }

    public OverallTask getTask() {
        return super.getOverallTask();
    }

    public JLabel getSelectedLabel() {
        return selectedLabel;
    }

    public JLabel getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the custom layout for the remove dependency GUI.
     */
    private void setCustomLayout() {

        GridBagConstraints selectedLabelConstraints = LayoutUtils.createConstraints(0, 0, TOP_DEFAULT_INSETS);
        add(selectedLabel, selectedLabelConstraints);

        GridBagConstraints selectedNodeStringConstraints = LayoutUtils.createConstraints(1, 0, TOP_DEFAULT_INSETS);
        add(selectedNode, selectedNodeStringConstraints);

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
}
