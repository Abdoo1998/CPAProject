package GUI;

import application.OverallTask;
import application.SubTask;
import com.mxgraph.model.mxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static GUI.TaskGUI.DEFAULT_INSETS;


public class SelectEditDependencyGraphView extends AbstractSelectDependencyGraphView {

    /** String representing the string on the select button*/
    private static final String SELECT_BUTTON_STRING = "Edit";
    /** String representing the string on the cancel button*/
    private static final String CANCEL_BUTTON_STRING = "Close";
    /** String representing the string on the label*/
    private static final String SELECT_DEPENDENCY_MESSAGE = "Select dependency to edit";

    public SelectEditDependencyGraphView(String title, OverallTask task, TaskDataPanel taskDataPanel) {
        super(title, task, taskDataPanel);
        getGraph().setCellsSelectable(true);

        getSelectedLabel().setText(SELECT_DEPENDENCY_MESSAGE + ": ");
        getSelectedLabel().setFont(FontCollection.DEFAULT_FONT_PLAIN);
        getSelectedNode().setFont(FontCollection.DEFAULT_FONT_PLAIN);
        getSelectedNode().setText(getTask().getTaskName());

        JButton selectButton = LayoutUtils.setButton(SELECT_BUTTON_STRING, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON_STRING, this);
        setCustomLayout(selectButton, cancelButton);
    }


    /**
     * Sets the custom layout
     * @param selectButton the select button
     * @param cancelButton the cancel button
     */
    private void setCustomLayout(JButton selectButton, JButton cancelButton) {

        //creates a panel for both buttons
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(selectButton);
        panel.add(cancelButton);
        GridBagConstraints buttonsConstraints = LayoutUtils.createConstraints(1,3, DEFAULT_INSETS,
                GridBagConstraints.LAST_LINE_END);
        add(panel, buttonsConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
        switch (actionEvent.getActionCommand()) {
            case SELECT_BUTTON_STRING: {

                if (getGraph().getSelectionCell() == null) {
                    MessageGUI messageGUI = new MessageGUI("Invalid Selection", "You have not selected " +
                            "a task to edit. Please select a task.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    return;
                }
                //something has been selected
                String taskId = ((mxCell) getGraph().getSelectionCell()).getId();
                //if the selected task is the overall task, give warning message to user and dont do anything
                if (taskId.equals(getTask().getTaskName())) {
                    //task selected is overall task, give warning
                    MessageGUI messageGUI = new MessageGUI("Invalid Selection", "The task that you have " +
                            "selected (" + getTask().getTaskName() + ") is not a dependency. Please choose a " +
                            "dependency instead.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    break;
                }
                //create the GUI that will handle the editing
                SubTask subTask = SubTask.findSubTaskInDependencies(getTask(), taskId);
                EditDependencyGUI editDependencyGUI = new EditDependencyGUI(this, subTask, getTaskDataPanel());
                javax.swing.SwingUtilities.invokeLater(editDependencyGUI::showGUI);

                break;
            }
            case CANCEL_BUTTON_STRING: {
                this.close();
            }
        }

    }
}
