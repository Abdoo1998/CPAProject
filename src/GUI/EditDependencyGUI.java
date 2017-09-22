package GUI;

import application.Duration;
import application.SubTask;

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

    /** The subtask to edit*/
    private SubTask subTask;

    /** String representing the title of the frame*/
    private static final String FRAME_TITLE = "Edit Dependency";
    /** String on the update button*/
    private static final String UPDATE_BUTTON_STRING = "Update";
    /** String on cancel button*/
    private static final String CANCEL_BUTTON_STRING = "Cancel";


    public EditDependencyGUI(SubTask subTask) {
        this.subTask = subTask;

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

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(updateButton);
        panel.add(cancelButton);

        //button constraints
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 2;
        panelConstraints.weightx = 0.5;
        panelConstraints.weighty = 0.5;
        panelConstraints.fill = GridBagConstraints.NONE;
        panelConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        panelConstraints.insets = DEFAULT_INSETS;
        add(panel, panelConstraints);

    }

    /**
     * Closes the frame.
     */
    private void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case UPDATE_BUTTON_STRING: {
                //do something
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
