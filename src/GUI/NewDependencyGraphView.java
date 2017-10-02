package GUI;

import application.OverallTask;
import application.SubTask;
import com.mxgraph.model.mxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * Class representing the GUI for the selection of two tasks and the addition of the second one as a dependency
 * of the first one.
 *
 * @author gorosgobe
 */
public class NewDependencyGraphView extends AbstractSelectDependencyGraphView {

    private static final String NONE_SELECTED = "No task selected";
    private static final String NONE = "None";
    private static final String NONE2 = "none";
    private static final String DEPENDENCY_INDICATOR = " --> ";
    /** String representing the string on the select button*/
    private static final String SELECT_BUTTON_STRING = "Add new dependency";
    /** String representing the string on the cancel button*/
    private static final String CANCEL_BUTTON_STRING = "Close";
    private JLabel secondTask;
    private JLabel separator;


    public NewDependencyGraphView(String title, OverallTask task, TaskDataPanel taskDataPanel) {
        super(title, task, taskDataPanel, false);
        getGraph().setCellsSelectable(true);
        getSelectedLabel().setText("Select two tasks: ");
        getSelectedNode().setText(NONE_SELECTED);
        setNewDependencyCustomLayout();
        addCustomMouseListener();

    }

    private void setNewDependencyCustomLayout() {
        this.separator = new JLabel();
        separator.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.secondTask = new JLabel();
        secondTask.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        getLabelAndNode().add(separator);
        getLabelAndNode().add(secondTask);

        JButton selectButton = LayoutUtils.setButton(SELECT_BUTTON_STRING, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON_STRING, this);

        //creates a panel for both buttons
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(selectButton);
        panel.add(cancelButton);
        GridBagConstraints buttonsConstraints = LayoutUtils.createConstraints(1,3, DEFAULT_INSETS,
                GridBagConstraints.LAST_LINE_END);
        add(panel, buttonsConstraints);
    }

    @Override
    public void addCustomMouseListener() {

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {


                Object cell = getGraphComponent().getCellAt(mouseEvent.getX(), mouseEvent.getY());
                if (cell != null && cell instanceof mxCell && !getGraph().getModel().isEdge(cell)) {
                    String id = ((mxCell) cell).getId();

                    if (getSelectedNode().getText().equals(NONE_SELECTED)) {
                        //initially empty
                        getSelectedNode().setText(id);
                        separator.setText(DEPENDENCY_INDICATOR);
                        secondTask.setText(NONE2);
                        return;
                    }

                    if (secondTask.getText().equals(NONE2)) {
                        secondTask.setText(id);
                        return;
                    }

                    if (!getSelectedNode().getText().equals(NONE) && !secondTask.getText().equals(NONE2)) {
                        //delete and start over
                        getSelectedNode().setText(id);
                        secondTask.setText(NONE2);
                    }

                } else {
                    getSelectedNode().setText(NONE_SELECTED);
                    separator.setText("");
                    secondTask.setText("");
                }

            }
        };

        getGraphComponent().getGraphControl().addMouseListener(adapter);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {
            case SELECT_BUTTON_STRING: {

                if (getSelectedNode().getText().equals(NONE_SELECTED)) {
                    MessageGUI m = new MessageGUI("Invalid selection", "Cannot add a dependency as no " +
                            "tasks have been selected. Please select two tasks.");
                    SwingUtilities.invokeLater(m::createAndShowGUI);
                    return;
                }

                if (secondTask.getText().equals(NONE2)) {
                    MessageGUI m = new MessageGUI("Invalid selection", "Cannot add a dependency as only " +
                            "one task has been selected. Please select two tasks.");
                    SwingUtilities.invokeLater(m::createAndShowGUI);
                    return;
                }

                //two tasks have been selected

                if (getTask().getTaskName().equals(getSelectedNode().getText())) {
                    OverallTask overallTask = getTask();
                    SubTask subTask = SubTask.findSubTaskInDependencies(getTask(), secondTask.getText());
                    overallTask.addSubTask(subTask);
                    this.close();
                } else if (getTask().getTaskName().equals(secondTask.getText())) {
                    MessageGUI m = new MessageGUI("Invalid selection", "Cannot add a task as a dependency " +
                            "of a subtask. Please select two subtasks or a subtask and a task.");
                    SwingUtilities.invokeLater(m::createAndShowGUI);
                } else {
                    //both subtasks
                    SubTask subTask1 = SubTask.findSubTaskInDependencies(getTask(), getSelectedNode().getText());
                    SubTask subTask2 = SubTask.findSubTaskInDependencies(getTask(), secondTask.getText());

                    boolean isSubTask1InSubTask2 = SubTask.findRecursivelySubTask(subTask2,
                            subTask1.getTaskName(), new HashSet<>()) != null;
                    if (isSubTask1InSubTask2) {
                        MessageGUI m = new MessageGUI("Invalid selection", "First task \""
                                + getSelectedNode().getText() + "\" is already a dependency of the second task \""
                                + secondTask.getText() + "\" (" + secondTask.getText()
                                + DEPENDENCY_INDICATOR +  getSelectedNode().getText() +  "). Two tasks cannot depend " +
                                "on each other (" + getSelectedNode().getText() + DEPENDENCY_INDICATOR +
                                secondTask.getText() + " is invalid). Please select two valid tasks.");
                        SwingUtilities.invokeLater(m::createAndShowGUI);
                        return;
                    }
                    subTask1.addDependency(subTask2);
                    this.close();
                }
                break;
            }
            case CANCEL_BUTTON_STRING: {
                this.close();
                break;
            }
        }
    }
}
