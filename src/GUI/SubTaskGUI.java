package GUI;

import application.Duration;
import application.SubTask;
import application.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GUI representing an application.SubTask
 *
 * @author gorosgobe
 */
public class SubTaskGUI extends TaskGUI implements ActionListener {

    private JLabel dropdownLabel;
    private JComboBox<Object> taskDropdown;
    private JButton button;
    private Map<String, Task> stringTaskMap;

    private static final String FRAME_TITLE = "Create New Subtask";
    private static final String BUTTON_STRING = "Create";
    private static final String LABEL_MESSAGE = "Select main task";
    private static final String TASK_DROPDOWN_STRING = "Task dropdown";


    //List given must be of ALL tasks, including overall tasks and Subtasks
    public SubTaskGUI(List<Task> tasksToShow) {
        super();
        setTitle(FRAME_TITLE);
        setStringTaskMap(tasksToShow);
        setDropdownLabel();
        setJComboBox(tasksToShow);
        setButton();
        setSubTaskLayout();
    }

    public void setStringTaskMap(List<Task> tasksToShow) {
        this.stringTaskMap = new HashMap<>();
        for (Task t : tasksToShow) {
            stringTaskMap.put(t.getTaskName(), t);
        }
    }

    private void setDropdownLabel() {
        this.dropdownLabel = new JLabel(LABEL_MESSAGE + ":");
        dropdownLabel.setLabelFor(taskDropdown);
        dropdownLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    private void setJComboBox(List<Task> tasksToShow) {
        this.taskDropdown = new JComboBox<>(stringTaskMap.keySet().toArray());
        taskDropdown.setSelectedIndex(0);
        taskDropdown.setEditable(true);
        //taskDropdown.addActionListener(this);
        //taskDropdown.setActionCommand(TASK_DROPDOWN_STRING);
        taskDropdown.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    private void setButton() {

        this.button = new JButton(BUTTON_STRING);
        button.setActionCommand(BUTTON_STRING);
        button.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds the frame as an action listener to the button
        button.addActionListener(this);
        //sets button to be default when pressed enter
        getRootPane().setDefaultButton(button);

    }

    private void setSubTaskLayout() {

        //dropdown label constraints
        GridBagConstraints dropdownLabelConstraints = new GridBagConstraints();
        dropdownLabelConstraints.gridx = 0;
        dropdownLabelConstraints.gridy = 2;
        dropdownLabelConstraints.fill = GridBagConstraints.NONE;
        dropdownLabelConstraints.insets = DEFAULT_INSETS;
        add(dropdownLabel, dropdownLabelConstraints);

        //dropdown JComboBox constraints with tasks
        GridBagConstraints taskDropdownConstraints = new GridBagConstraints();
        taskDropdownConstraints.gridx = 1;
        taskDropdownConstraints.gridy = 2;
        taskDropdownConstraints.fill = GridBagConstraints.NONE;
        taskDropdownConstraints.insets = DEFAULT_INSETS;
        taskDropdownConstraints.gridwidth = 2;
        add(taskDropdown, taskDropdownConstraints);

        //button constraints
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 3;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.insets = DEFAULT_INSETS;
        add(button, buttonConstraints);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //only action is from the button
        String taskName = getTaskNameText();
        Duration duration = getDuration();
        String associatedTaskString = (String) taskDropdown.getSelectedItem();
        if (taskName.equals("") || duration == null) {
            //dont do anything
            return;
        }
        setTask(new SubTask(taskName, duration));
        //TODO: Set Main task of the Subtask, whether this associated Task is an Overall Task or a SubTask
        //TODO: -----------------------------------------------------------
        Task associatedTask = stringTaskMap.get(associatedTaskString);
        //TODO: -----------------------------------------------------------
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }


}
