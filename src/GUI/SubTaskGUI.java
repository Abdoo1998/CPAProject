package GUI;

import application.OverallTask;

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

    /** A reference to the dropdown label*/
    private JLabel dropdownLabel;
    /** A reference to the main task dropdown*/
    private JComboBox taskDropdown;
    /** A reference to the create button*/
    private JButton button;
    /** A reference to a mapping from Strings to all OverallTasks*/
    private Map<String, OverallTask> stringTaskMap;

    /** String representing the frame title*/
    private static final String FRAME_TITLE = "Create New Subtask";
    /** String representing the string on the button*/
    private static final String BUTTON_STRING = "Create";
    /** String representing the string on the label*/
    private static final String LABEL_MESSAGE = "Select main task";
    /** String representing the task dropdown message*/
    private static final String TASK_DROPDOWN_STRING = "Task dropdown";


    /**
     * Constructs a subTaskGUI.
     * @param tasksToShow the list of all OverallTasks held in the GUI
     */
    public SubTaskGUI(List<OverallTask> tasksToShow) {
        //List given must be of ALL Overall tasks
        super();
        setTitle(FRAME_TITLE);
        setStringTaskMap(tasksToShow);
        setDropdownLabel();
        setJComboBox();
        //setSelectedLabelAndSelectedNode();
        OverallTask overallTask = stringTaskMap.get(taskDropdown.getSelectedItem());
        setButton();
        setSubTaskLayout();
    }

    /**
     * Constructs a new subtaskGUI, with the OverallTask parameter being the one selected by default in the task
     * dropdown
     * @param tasksToShow the list of all OverallTasks held in the GUI
     * @param toBeSelectedTask the Overall Task selected by default
     */
    public SubTaskGUI(List<OverallTask> tasksToShow, OverallTask toBeSelectedTask) {
        //List given must be of ALL Overall tasks
        super();
        setTitle(FRAME_TITLE);
        setStringTaskMap(tasksToShow);
        setDropdownLabel();
        setJComboBox(toBeSelectedTask.getTaskName());
        //setSelectedLabelAndSelectedNode();
        setButton();
        setSubTaskLayout();
    }

    /**
     * Initialises the string to overall task map used by the JComboBox
     * @param tasksToShow the list of OverallTasks
     */
    public void setStringTaskMap(List<OverallTask> tasksToShow) {
        this.stringTaskMap = new HashMap<>();
        for (OverallTask t : tasksToShow) {
            stringTaskMap.put(t.getTaskName(), t);
        }
    }

    /**
     * Initialises and sets the dropdown label
     */
    private void setDropdownLabel() {
        this.dropdownLabel = new JLabel(LABEL_MESSAGE + ":");
        dropdownLabel.setLabelFor(taskDropdown);
        dropdownLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    /**
     * Initialises and sets the task dropdown JComboBox
     */
    private void setJComboBox() {
        this.taskDropdown = new JComboBox<>(stringTaskMap.keySet().toArray());
        taskDropdown.setSelectedIndex(0);
        taskDropdown.setEditable(true);
        taskDropdown.addActionListener(this);
        taskDropdown.setActionCommand(TASK_DROPDOWN_STRING);
        taskDropdown.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    /**
     * Initialises and sets the task dropdown JComboBox
     * @param selectedTaskName the name of the task selected by default
     */
    private void setJComboBox(String selectedTaskName) {
        this.taskDropdown = new JComboBox<>(stringTaskMap.keySet().toArray());
        taskDropdown.setSelectedItem(selectedTaskName);
        taskDropdown.setEditable(true);
        taskDropdown.addActionListener(this);
        taskDropdown.setActionCommand(TASK_DROPDOWN_STRING);
        taskDropdown.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    /**
     * Sets the create button.
     */
    private void setButton() {

        this.button = new JButton(BUTTON_STRING);
        button.setActionCommand(BUTTON_STRING);
        button.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds the frame as an action listener to the button
        button.addActionListener(this);
        //sets button to be default when pressed enter
        getRootPane().setDefaultButton(button);

    }

    /**
     * Sets the GridBagConstraints for the specific components of the SubTask GUI. For more information on
     * GridBagLayout and GridBagConstraint see the Java tutorials.
     */
    private void setSubTaskLayout() {

        //dropdown label constraints
        GridBagConstraints dropdownLabelConstraints = new GridBagConstraints();
        dropdownLabelConstraints.gridx = 0;
        dropdownLabelConstraints.gridy = 2;
        dropdownLabelConstraints.weightx = 0.5;
        dropdownLabelConstraints.weighty = 0.5;
        dropdownLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        dropdownLabelConstraints.insets = DEFAULT_INSETS;
        add(dropdownLabel, dropdownLabelConstraints);

        //dropdown JComboBox constraints with tasks
        GridBagConstraints taskDropdownConstraints = new GridBagConstraints();
        taskDropdownConstraints.gridx = 1;
        taskDropdownConstraints.gridy = 2;
        taskDropdownConstraints.weightx = 0.5;
        taskDropdownConstraints.weighty = 0.5;
        taskDropdownConstraints.fill = GridBagConstraints.HORIZONTAL;
        taskDropdownConstraints.insets = DEFAULT_INSETS;
        taskDropdownConstraints.gridwidth = 2;
        add(taskDropdown, taskDropdownConstraints);

        //button constraints
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 4;
        buttonConstraints.weightx = 0.5;
        buttonConstraints.weighty = 0.5;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        buttonConstraints.insets = DEFAULT_INSETS;
        add(button, buttonConstraints);

    }

    public boolean isInvalidDuration() {

        int hours = Integer.parseInt(getDurationField().getHoursField().getText());
        int minutes = Integer.parseInt(getDurationField().getMinutesField().getText());


        return (hours == 0 && minutes == 0)
                || (hours >= 24)
                || (minutes >= 60);
    }

    public JComboBox getTaskDropdown() {
        return taskDropdown;
    }

    public Map<String, OverallTask> getStringTaskMap() {
        return stringTaskMap;
    }

    public String getButtonString() {
        return BUTTON_STRING;
    }

    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case BUTTON_STRING: {
                String taskName = getTaskNameText();
                //check if name valid
                if (taskName.equals("")) {
                    //show warning and dont do anything
                    MessageGUI messageGUI = new MessageGUI("Empty subtask name", "You have " +
                            "inputted an invalid subtask name. Subtasks cannot have an empty name. Please " +
                            "input a valid name for the subtask.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    return;
                }
                //check if duration valid
                if (isInvalidDuration()) {
                    //show warning and dont do anything
                    //invalid duration, notify the user
                    String minutes = getDurationField().getMinutesField().getText();
                    int minutesInt = Integer.parseInt(minutes);
                    MessageGUI messageGUI = new MessageGUI("Invalid Duration", "A subtask cannot " +
                            "have a duration of " + getDurationField().getHoursField().getText()
                            + " hrs and " + minutes + ((minutesInt == 1) ? " min." : " mins.") + " Please " +
                            "input a valid duration for the subtask.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    return;
                }
                //show GUI for add dependency graph view
                AddSubTaskGraphView graphView = new AddSubTaskGraphView("Select task to add to",
                        stringTaskMap.get(taskDropdown.getSelectedItem()), this);
                javax.swing.SwingUtilities.invokeLater(graphView::showGUI);
            }
        }

    }

}
