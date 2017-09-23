package GUI;

import application.OverallTask;
import application.SubTask;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionEvent;

import static GUI.TaskGUI.DEFAULT_INSETS;


/**
 * Represents the GUI to remove a depencency from an Overall Task in the task's data panel.
 *
 * @author gorosgobe
 */
public class RemoveDependencyGUI extends AbstractSelectDependency {

    /** Title of the frame*/
    private static final String FRAME_TITLE = "Remove Dependency";
    /** String representing the string on the remove button*/
    private static final String REMOVE_BUTTON_STRING = "Remove";
    /** String representing the string on the cancel button*/
    private static final String CANCEL_BUTTON_STRING = "Cancel";
    /** String representing the string on the label*/
    private static final String REMOVE_DEPENDENCY_MESSAGE = "Remove dependency";


    public RemoveDependencyGUI(TaskDataPanel taskDataPanel, OverallTask task) {
        super(taskDataPanel, task, REMOVE_DEPENDENCY_MESSAGE);
        setTitle(FRAME_TITLE);

        JButton removeButton = LayoutUtils.setButton(REMOVE_BUTTON_STRING, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON_STRING, this);
        setCustomLayout(removeButton, cancelButton);
    }

    private void setCustomLayout(JButton removeButton, JButton cancelButton) {
        //creates a panel for both buttons
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(removeButton);
        panel.add(cancelButton);
        GridBagConstraints buttonsConstraints = LayoutUtils.createConstraints(1,3, DEFAULT_INSETS,
                GridBagConstraints.LAST_LINE_END);
        add(panel, buttonsConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case CANCEL_BUTTON_STRING: {
                this.close();
                break;
            }
            case REMOVE_BUTTON_STRING: {
                //if the selected task is the overall task, give warning to user and dont do anything
                if (getSelectedNode().getText().equals(getTask().getTaskName())) {
                    MessageGUI messageGUI = new MessageGUI("Warning", "The task to remove that you " +
                            "selected is the main task: cannot be removed as it is not a dependency. Please choose " +
                            "a dependency of the main task.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    break;
                }
                //remove dependency from task
                SubTask subTask = SubTask.findSubTaskInDependencies(getTask(), getSelectedNode().getText());
                //if parent task is an overall task, then delete directly
                if (getTask().getAllSubTasks().contains(subTask)) {
                    //POSSIBLY ISSUE A WARNING TO USER, AS THIS WILL DELETE ALL DEPENDENCIES OF THE GIVEN SUBTASK
                    getTask().removeSubTask(subTask);
                } else {
                    //otherwise, find parent task
                    SubTask parent = SubTask.findParentsSubTaskOf(getTask(), getSelectedNode().getText());
                    parent.removeDependency(subTask);
                }
                //update gantt chart
                getTaskDataPanel().updateGanttChart();
                this.close();
                break;
            }
        }
    }

    //as with SubTaskGUI
    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getTree().getLastSelectedPathComponent();

        if (node == null) {
            //Nothing is selected.
            return;
        }

        getSelectedNode().setText((String) node.getUserObject());
    }
}
