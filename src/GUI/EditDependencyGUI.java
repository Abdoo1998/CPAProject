package GUI;

import application.SubTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public EditDependencyGUI(SubTask subTask) {
        this.subTask = subTask;

        JButton updateButton = LayoutUtils.setButton(UPDATE_BUTTON_STRING, this);
        setCustomLayout(updateButton);

        setTitle(FRAME_TITLE + " \"" + subTask.getTaskName() + "\"");
        setResizable(false);
    }

    /**
     * Sets the custom layout for the frame
     * @param updateButton the update button
     */
    private void setCustomLayout(JButton updateButton) {

        //button constraints
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 2;
        buttonConstraints.weightx = 0.5;
        buttonConstraints.weighty = 0.5;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        buttonConstraints.insets = DEFAULT_INSETS;
        add(updateButton, buttonConstraints);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
