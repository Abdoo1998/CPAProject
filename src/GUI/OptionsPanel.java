package GUI;

import application.Duration;
import application.OverallTask;
import application.Time;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.LayoutUtils.setButton;
import static GUI.TaskDataPanel.DEFAULT_NO_DESCRIPTION;
import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * Represents a JPanel holding all options within a Task Data Panel
 *
 * @author gorosgobe
 */
public class OptionsPanel extends JPanel implements ActionListener {

    /**A reference to the application*/
    private CPAProjectApplicationGUI applicationReference;
    /** A reference to the task to modify*/
    private OverallTask task;
    /** A reference to the task data panel of the task*/
    private TaskDataPanel taskDataPanel;

    // EDIT TASK PANEL

    /** A reference to the name field where the user will input the new name*/
    private CPATextField nameField;
    /** A reference to the button that will update the name of the task*/
    private JButton updateNameButton;

    /** A reference to the duration field where the user will input the new duration*/
    private TimeTextField durationField;
    /** A reference to the button that will update the duration of the task*/
    private JButton updateDurationButton;

    /** A reference to the start time fiels where the user will input the new start time*/
    private TimeTextField startTimeField;
    /** A reference to the button that will update the start time of the task*/
    private JButton updateStartTimeButton;

    /** A reference to the text area containing the new description of the task*/
    private JTextArea description;
    /** A reference to the scroll pane containing the text area with the description of the task*/
    private JScrollPane descriptionScrollPane;
    /** A reference to the button that will update the description of the task*/
    private JButton updateDescriptionButton;

    //MANAGE DEPENDENCIES PANEL
    private JButton addDependencyButton;
    private JButton removeDependencyButton;
    private JButton editDependencyButton;
    private JButton deleteOverallTaskButton;
    private JButton optimiseScheduleButton;

    //constants
    /** The string on the name button*/
    private static final String UPDATE_NAME_BUTTON = "Update name";
    /** The string on the duration button*/
    private static final String UPDATE_DURATION_BUTTON = "Update duration";
    /** The string on the start time button*/
    private static final String UPDATE_START_TIME_BUTTON = "Update start time";
    /** The string on the description button*/
    private static final String UPDATE_DESCRIPTION_BUTTON = "Update description";

    private static final String ADD_DEPENDENCY_BUTTON = "Add dependency";
    private static final String REMOVE_DEPENDENCY_BUTTON = "Remove dependency";
    private static final String EDIT_DEPENDENCY_BUTTON = "Edit dependency";
    private static final String DELETE_OVERALL_TASK_BUTTON = "Delete task";
    private static final String OPTIMISE_SCHEDULE_BUTTON = "Optimise schedule";



    public OptionsPanel(OverallTask task, TaskDataPanel taskDataPanel, CPAProjectApplicationGUI applicationReference) {
        this.applicationReference = applicationReference;
        this.task = task;
        this.taskDataPanel = taskDataPanel;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        //panel on the left, handles editing an Overall task's direct fields (not dependencies)
        setEditTaskPanel();

        //Panel on the right, manages the dependencies of the Overall task
        setManageDependenciesPanel();

    }

    private void setEditTaskPanel() {
        //panel on the left, handles editing an Overall task's direct fields (not dependencies)
        JPanel editTaskPanel = new JPanel(new GridBagLayout());

        this.nameField = new CPATextField(20);
        this.updateNameButton = setButton(UPDATE_NAME_BUTTON, this);

        this.durationField = new TimeTextField("h", "m");
        this.updateDurationButton = setButton(UPDATE_DURATION_BUTTON, this);

        this.startTimeField = new TimeTextField("h", "m");
        this.updateStartTimeButton = setButton(UPDATE_START_TIME_BUTTON, this);

        this.description = new JTextArea(5, 20);
        description.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        this.descriptionScrollPane = new JScrollPane(description);
        descriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.updateDescriptionButton = setButton(UPDATE_DESCRIPTION_BUTTON, this);

        JLabel name = new JLabel(OverallTaskGUI.TASK_STRING + ": ");
        name.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        name.setLabelFor(nameField);

        JLabel duration = new JLabel(OverallTaskGUI.DURATION_STRING + ": ");
        duration.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        duration.setLabelFor(durationField);

        JLabel startTime = new JLabel(OverallTaskGUI.START_TIME + ": ");
        startTime.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        startTime.setLabelFor(startTimeField);

        JLabel description = new JLabel(OverallTaskGUI.DESCRIPTION_LABEL + ": ");
        description.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        description.setLabelFor(descriptionScrollPane);

        setCustomLayoutEditTaskPanel(editTaskPanel, name, duration, startTime, description);

        //sets titled border for edit task panel
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Edit Task");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        editTaskPanel.setBorder(border);

        //add the edit task panel to the options panel
        this.add(editTaskPanel);
    }

    private void setManageDependenciesPanel() {
        //Panel on the right, manages the dependencies of the Overall task, called Manage Dependencies
        JPanel manageDependenciesPanel = new JPanel(new GridBagLayout());

        this.addDependencyButton = setButton(ADD_DEPENDENCY_BUTTON, this);
        this.removeDependencyButton = setButton(REMOVE_DEPENDENCY_BUTTON, this);
        this.editDependencyButton = setButton(EDIT_DEPENDENCY_BUTTON, this);
        this.deleteOverallTaskButton = setButton(DELETE_OVERALL_TASK_BUTTON, this);
        this.optimiseScheduleButton = setButton(OPTIMISE_SCHEDULE_BUTTON, this);

        //TODO: Delete dependency - subtask
        //TODO: Edit dependency - subtask
        //TODO: Delete itself - overall task
        //TODO: Optimise dependencies (once Naman and Erik finish) - compute CPA and show in Gantt chart

        setCustomLayoutManageDependenciesPanel(manageDependenciesPanel);

        //sets titled border for edit task panel
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Manage Dependencies");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        manageDependenciesPanel.setBorder(border);

        //add the edit task panel to the options panel
        this.add(manageDependenciesPanel);

    }


    private void setCustomLayoutEditTaskPanel(JPanel editTaskPanel, JLabel nameLabel, JLabel durationLabel,
                                 JLabel startTimeLabel, JLabel descriptionLabel) {

        //constraints for name label
        GridBagConstraints nameConstraints = createConstraints(0,0);
        editTaskPanel.add(nameLabel, nameConstraints);

        //constraints for name field
        GridBagConstraints nameFieldConstraints = createConstraints(1,0);
        editTaskPanel.add(nameField, nameFieldConstraints);

        //constraints for name button
        GridBagConstraints nameButtonConstraints = createConstraints(2, 0);
        editTaskPanel.add(updateNameButton, nameButtonConstraints);

        //constraints for duration label
        GridBagConstraints durationConstraints = createConstraints(0, 1);
        editTaskPanel.add(durationLabel, durationConstraints);

        //constraints for duration field
        GridBagConstraints durationFieldConstraints = createConstraints(1, 1);
        editTaskPanel.add(durationField, durationFieldConstraints);

        //constraints for duration button
        GridBagConstraints durationButtonConstraints = createConstraints(2, 1);
        editTaskPanel.add(updateDurationButton, durationButtonConstraints);

        //constraints for start time label
        GridBagConstraints startTimeConstraints = createConstraints(0, 2);
        editTaskPanel.add(startTimeLabel, startTimeConstraints);

        //constraints for start time field
        GridBagConstraints startTimeFieldConstraints = createConstraints(1, 2);
        editTaskPanel.add(startTimeField, startTimeFieldConstraints);

        //constraints for start time button
        GridBagConstraints startTimeButtonConstraints = createConstraints(2, 2);
        editTaskPanel.add(updateStartTimeButton, startTimeButtonConstraints);

        //constraints for description label
        GridBagConstraints descriptionConstraints = createConstraints(0, 3);
        editTaskPanel.add(descriptionLabel, descriptionConstraints);

        //constraints for description scroll pane
        GridBagConstraints descriptionScrollPaneConstraints = createConstraints(1, 3);
        editTaskPanel.add(descriptionScrollPane, descriptionScrollPaneConstraints);

        //constraints for description button
        GridBagConstraints descriptionButtonConstraints = createConstraints(2, 3);
        editTaskPanel.add(updateDescriptionButton, descriptionButtonConstraints);

    }

    private void setCustomLayoutManageDependenciesPanel(JPanel manageDependenciesPanel) {

        GridBagConstraints addDependencyConstraints = createConstraints(0,0);
        manageDependenciesPanel.add(addDependencyButton, addDependencyConstraints);

        GridBagConstraints removeDependencyConstraints = createConstraints(0, 1);
        manageDependenciesPanel.add(removeDependencyButton, removeDependencyConstraints);

        GridBagConstraints editDependencyConstraints = createConstraints(0, 2);
        manageDependenciesPanel.add(editDependencyButton, editDependencyConstraints);

        GridBagConstraints deleteOverallTaskConstraints = createConstraints(0, 3);
        manageDependenciesPanel.add(deleteOverallTaskButton,deleteOverallTaskConstraints);

        GridBagConstraints optimiseAndScheduleConstraints = createConstraints(0,4);
        manageDependenciesPanel.add(optimiseScheduleButton, optimiseAndScheduleConstraints);

    }

    private GridBagConstraints createConstraints(int gridx, int gridy) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = DEFAULT_INSETS;

        return constraints;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {
            case UPDATE_NAME_BUTTON: {
                JTabbedPane tabbedPane = applicationReference.getTabbedPane();
                String newName = nameField.getText();
                if (newName.equals("")) {
                    return;
                }
                //update name of task
                task.setName(newName);
                //update task data panel
                taskDataPanel.getTaskNameLabel().setText(newName);
                //update name of tab
                int index = tabbedPane.getSelectedIndex();
                tabbedPane.setTabComponentAt(index, new RemovableTabComponent(newName, tabbedPane, taskDataPanel));
                //update name of legend in gantt chart, not necessarily efficient but can be changed if necessary
                taskDataPanel.updateGanttChart();
                //update name in task view
                applicationReference.updateTaskPanel();
                break;
            }
            case UPDATE_DURATION_BUTTON: {
                Duration newDuration = durationField.getDuration();
                if (newDuration.getHours() == 0 && newDuration.getTotalMinutes() == 0) {
                    return;
                }
                //update duration of task
                task.setDuration(newDuration);
                //update task data panel
                taskDataPanel.getTaskDurationLabel().setText(newDuration.toString() + " hrs");
                //update duration in task view
                applicationReference.updateTaskPanel();
                break;
            }
            case UPDATE_START_TIME_BUTTON: {
                Time newStartTime = startTimeField.getTime();
                if (newStartTime.getHours() == 0 && newStartTime.getMinutes() == 0) {
                    return;
                }
                //update start time of task
                task.setStartTime(newStartTime);
                //update task panel
                taskDataPanel.getTaskStartTimeLabel().setText(newStartTime.toString());
                //update start time in task view
                applicationReference.updateTaskPanel();
                break;
            }
            case UPDATE_DESCRIPTION_BUTTON: {
                String newDescription = description.getText();
                if (newDescription.equals("")) {
                    newDescription = DEFAULT_NO_DESCRIPTION;
                }
                //update description of task
                task.setDescription(newDescription);
                //update task data panel
                taskDataPanel.getTaskDescriptionTextArea().setEditable(true);
                taskDataPanel.getTaskDescriptionTextArea().setText(newDescription);
                taskDataPanel.getTaskDescriptionTextArea().setEditable(false);
                break;
            }
            case ADD_DEPENDENCY_BUTTON: {
                //add dependency GUI handles the updating of the Gantt chart
                AddDependencyGUI addDependencyGUI = new AddDependencyGUI(applicationReference.getTasks(), task, taskDataPanel);

                javax.swing.SwingUtilities.invokeLater(addDependencyGUI::showGUI);
                break;
            }
            case REMOVE_DEPENDENCY_BUTTON: {
                //GUI handles the removal of the dependency
                RemoveDependencyGUI removeDependencyGUI = new RemoveDependencyGUI(taskDataPanel, task);
                javax.swing.SwingUtilities.invokeLater(removeDependencyGUI::createAndShowGUI);
                break;
            }
            case EDIT_DEPENDENCY_BUTTON: {
                SelectEditDependencyGUI selectEditDependencyGUI = new SelectEditDependencyGUI(taskDataPanel, task);
                javax.swing.SwingUtilities.invokeLater(selectEditDependencyGUI::createAndShowGUI);
                break;
            }
            case DELETE_OVERALL_TASK_BUTTON: {

                WarningGUI warningGUI = new WarningGUI("Warning", "Warning: you are about to delete the " +
                        "task you are currently viewing. This will also delete all the dependencies. Do you wish to " +
                        "proceed?", null, null);

                Action proceedWithDeletion = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //remove task
                        applicationReference.getTasks().remove(task);
                        System.out.println(applicationReference.getTasks());
                        //go back to application task view
                        JTabbedPane pane = applicationReference.getTabbedPane();
                        //close tab
                        int index = pane.indexOfComponent(taskDataPanel);
                        pane.remove(index);
                        //set selected index to be the task view, the first tab
                        pane.setSelectedIndex(0);
                        //close warning
                        warningGUI.close();
                        applicationReference.updateTaskPanel();
                    }
                };

                warningGUI.setContinueButtonAction(proceedWithDeletion);

                javax.swing.SwingUtilities.invokeLater(warningGUI::createAndShowGUI);
                break;
            }
        }
    }
}