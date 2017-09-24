package GUI;

import application.Duration;
import application.SubTask;
import com.mxgraph.model.mxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * GUI that handles the editing of a dependency.
 *
 * @author gorosgobe
 */
public class EditDependencyGUI extends TaskGUI implements ActionListener {

    /** A reference to the task data panel*/
    private final TaskDataPanel taskDataPanel;
    /** A reference to the select GUI (used for updating the tree view)*/
    private final AbstractSelectDependencyGraphView graphView;
    /** The subtask to edit*/
    private SubTask subTask;

    /** String representing the title of the frame*/
    private static final String FRAME_TITLE = "Edit Subtask";
    /** String on the update button*/
    private static final String UPDATE_BUTTON_STRING = "Update";
    /** String on cancel button*/
    private static final String CANCEL_BUTTON_STRING = "Cancel";


    public EditDependencyGUI(AbstractSelectDependencyGraphView graphView, SubTask subTask, TaskDataPanel taskDataPanel) {
        this.subTask = subTask;
        this.taskDataPanel = taskDataPanel;
        this.graphView = graphView;

        JButton updateButton = LayoutUtils.setButton(UPDATE_BUTTON_STRING, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON_STRING, this);
        setCustomLayout(updateButton, cancelButton);

        //initialises default fields
        getTaskNameField().setText(subTask.getTaskName());
        Duration duration = subTask.getDuration();
        getDurationField().getHoursField().setText(duration.getHours() + "");
        getDurationField().getMinutesField().setText(duration.getRemainingMinutes() + "");

        setTitle(FRAME_TITLE + " \"" + subTask.getTaskName() + "\"");
        setResizable(false);
    }

    /**
     * Sets the custom layout for the frame
     * @param updateButton the update button
     */
    private void setCustomLayout(JButton updateButton, JButton cancelButton) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(updateButton);
        panel.add(cancelButton);

        //button constraints
        GridBagConstraints panelConstraints = LayoutUtils.createConstraints(1, 3,
                new Insets(8, 8, 8, 8), GridBagConstraints.BASELINE_TRAILING);
        add(panel, panelConstraints);

    }

    /**
     * Closes the frame.
     */
    private void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private boolean isInvalidDuration() {

        int hours = Integer.parseInt(getDurationField().getHoursField().getText());
        int minutes = Integer.parseInt(getDurationField().getMinutesField().getText());


        return (hours == 0 && minutes == 0)
                || (hours >= 24)
                || (minutes >= 60);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case UPDATE_BUTTON_STRING: {
                //update subtask
                //check if name is valid
                if (getTaskNameText().equals("")) {
                    //invalid subtask name, notify the user
                    MessageGUI messageGUI = new MessageGUI("Invalid Name", "A subtask cannot have an " +
                            "empty name. Please input a valid name for the subtask.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    break;
                }
                //check if duration is valid
                if (isInvalidDuration()) {

                    String minutes = getDurationField().getMinutesField().getText();
                    int minutesInt = Integer.parseInt(minutes);

                    //invalid duration, notify the user
                    MessageGUI messageGUI = new MessageGUI("Invalid Duration", "A subtask cannot have a " +
                            "duration of " + getDurationField().getHoursField().getText()  + " hrs and " +
                             minutes + ((minutesInt == 1) ? " min." : " mins.") + " Please input a valid duration " +
                            "for the subtask.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    break;
                }
                //name and duration are valid
                subTask.setName(getTaskNameField().getText());
                subTask.setDuration(getDurationField().getDuration());
                taskDataPanel.updateGanttChart();
                mxCell cell = (mxCell) graphView.getGraph().getSelectionCell();
                graphView.getGraph().getModel().setValue(cell, getTaskNameText());
                graphView.getSelectedNode().setText(getTaskNameText());
                this.close();
                break;
            }
            case CANCEL_BUTTON_STRING: {
                this.close();
                break;
            }
        }

    }
}
