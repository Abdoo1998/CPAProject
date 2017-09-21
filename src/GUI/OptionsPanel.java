package GUI;

import application.OverallTask;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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
        JPanel editTaskPanel = new JPanel();

        //creates panels for every field that the edit task panel will handle
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //sets name panel
        setNamePanel(namePanel);

        //sets duration panel
        this.durationField = new TimeTextField("h", "m");
        this.updateDurationButton = new JButton("Update duration");
        setTimeTextFieldPanel(durationPanel, durationField, updateDurationButton,
                OverallTaskGUI.DURATION_STRING + ": ");

        //sets start time panel
        this.startTimeField = new TimeTextField("h", "m");
        this.updateStartTimeButton = new JButton("Update start time");
        setTimeTextFieldPanel(startTimePanel, startTimeField, updateStartTimeButton,
                OverallTaskGUI.START_TIME + ": ");

        //set description panel
        this.description = new JTextArea(5, 20);
        this.descriptionScrollPane = new JScrollPane(description);
        this.updateDescriptionButton = new JButton("Update description");
        setDescriptionPanel(descriptionPanel, description, descriptionScrollPane, updateDescriptionButton);

        setEditTaskPanel(editTaskPanel, namePanel, durationPanel, startTimePanel, descriptionPanel);

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

    private void setNamePanel(JPanel namePanel) {
        assert namePanel != null : "Arguments must not be null";
        //creates name label
        JLabel nameLabel = new JLabel(OverallTaskGUI.TASK_STRING + ": ");
        nameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //creates name text field
        this.nameField = new CPATextField(20);
        //accessibility
        nameLabel.setLabelFor(nameField);
        //creates update name button
        this.updateNameButton = new JButton("Update name");
        updateNameButton.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds to name panel
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(updateNameButton);
    }

    public void setTimeTextFieldPanel(JPanel timeTextFieldPanel, TimeTextField timeTextField, JButton button, String labelName) {
        assert timeTextFieldPanel != null && timeTextField != null && button != null : "Arguments must not be null";

        //creates name label
        JLabel label = new JLabel(labelName);
        label.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //accessibility
        label.setLabelFor(timeTextField);
        //sets font for button
        button.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds to panel
        timeTextFieldPanel.add(label);
        timeTextFieldPanel.add(timeTextField);
        timeTextFieldPanel.add(button);
    }

    private void setDescriptionPanel(JPanel descriptionPanel, JTextArea description,
                                     JScrollPane descriptionScrollPane, JButton updateDescriptionButton) {
        assert descriptionPanel != null && description != null && descriptionScrollPane != null
                && updateDescriptionButton != null : "Arguments must not be null";
        //sets description label
        JLabel descriptionLabel = new JLabel(OverallTaskGUI.DESCRIPTION_LABEL + ": ");
        descriptionLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //accessibility
        descriptionLabel.setLabelFor(description);
        //sets options for text area
        description.setLineWrap(true);
        description.setLineWrap(true);
        description.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //sets scroll pane that has been assigned to JTextArea
        descriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        //sets font for button
        updateDescriptionButton.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //adds to panel
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionScrollPane);
        descriptionPanel.add(updateDescriptionButton);

    }

    private void setEditTaskPanel(JPanel editTaskPanel, JPanel namePanel, JPanel durationPanel,
                                  JPanel startTimePanel, JPanel descriptionPanel) {

        editTaskPanel.setLayout(new BoxLayout(editTaskPanel, BoxLayout.PAGE_AXIS));

        Dimension separationDimension1 = new Dimension(0, 8);
        Dimension separationDimension2 = new Dimension(0, 4);

        //separation
        editTaskPanel.add(Box.createRigidArea(separationDimension1));
        editTaskPanel.add(namePanel);
        //separation
        editTaskPanel.add(Box.createRigidArea(separationDimension2));
        editTaskPanel.add(durationPanel);
        //separation
        editTaskPanel.add(Box.createRigidArea(separationDimension2));
        editTaskPanel.add(startTimePanel);
        //separation
        editTaskPanel.add(Box.createRigidArea(separationDimension2));
        editTaskPanel.add(descriptionPanel);
        //separation
        editTaskPanel.add(Box.createRigidArea(separationDimension1));
        //sets titled border
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Edit task");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        editTaskPanel.setBorder(border);
    }
}
