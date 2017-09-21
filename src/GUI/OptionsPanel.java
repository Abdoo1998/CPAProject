package GUI;

import application.OverallTask;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * Represents a JPanel holding all options within a Task Data Panel
 *
 * @author gorosgobe
 */
public class OptionsPanel extends JPanel {

    private OverallTask task;

    private CPATextField nameField;
    private JButton updateNameButton;

    private TimeTextField durationField;
    private JButton updateDurationButton;

    private TimeTextField startTimeField;
    private JButton updateStartTimeButton;

    private JTextArea description;
    private JScrollPane descriptionScrollPane;
    private JButton updateDescriptionButton;


    public OptionsPanel(OverallTask task) {
        this.task = task;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        //panel on the left, handles editing an Overall task's direct fields (not dependencies)
        JPanel editTaskPanel = new JPanel(new GridBagLayout());

        this.nameField = new CPATextField(20);
        this.updateNameButton = new JButton("Update name");
        updateNameButton.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.durationField = new TimeTextField("h", "m");
        this.updateDurationButton = new JButton("Update duration");
        updateDurationButton.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //sets start time panel
        this.startTimeField = new TimeTextField("h", "m");
        this.updateStartTimeButton = new JButton("Update start time");
        updateStartTimeButton.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //set description panel
        this.description = new JTextArea(5, 20);
        this.descriptionScrollPane = new JScrollPane(description);
        descriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.updateDescriptionButton = new JButton("Update description");
        updateDescriptionButton.setFont(FontCollection.DEFAULT_FONT_PLAIN);

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


        setCustomLayout(editTaskPanel, name, duration, startTime, description);

        //sets titled border for edit task panel
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Edit Task");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        editTaskPanel.setBorder(border);

        //add the edit task panel to the options panel
        this.add(editTaskPanel);

        //TODO: Update start time
        //TODO: Update duration
        //TODO: Update description
        //TODO: Update button

        //Panel called Manage Dependencies
        //TODO: Add dependency
        //TODO: Delete dependency
        //TODO: Edit dependency
        //TODO: Optimise dependencies (once Naman and Erik finish)

    }

    private void setCustomLayout(JPanel editTaskPanel, JLabel nameLabel, JLabel durationLabel,
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



}