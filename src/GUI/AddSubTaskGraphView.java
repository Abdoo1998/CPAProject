package GUI;

import application.OverallTask;
import application.SubTask;
import com.mxgraph.model.mxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The version of the Graph View that adds a dependency to the selected node.
 *
 * @author gorosgobe
 */
public class AddSubTaskGraphView extends AbstractSelectDependencyGraphView implements ActionListener {

    private static final String ADD_BUTTON_STRING = "Add";
    private static final String CANCEL_BUTTON_STRING = "Cancel";
    private static final String ADD_DEPENDENCY_MESSAGE = "Add subtask as dependency to";
    private TaskDataPanel taskDataPanel;
    private SubTaskGUI subTaskGUI;

    public AddSubTaskGraphView(String title, OverallTask task, SubTaskGUI subTaskGUI) {
        super(title, task, null);
        this.subTaskGUI = subTaskGUI;

        setFrameLayout();
        setButtonLayout();
    }

    public AddSubTaskGraphView(String title, OverallTask task, SubTaskGUI subTaskGUI, TaskDataPanel taskDataPanel) {
        super(title, task, taskDataPanel);
        this.subTaskGUI = subTaskGUI;
        this.taskDataPanel = taskDataPanel;

        setFrameLayout();
        setButtonLayout();
    }

    private void setFrameLayout() {
        setMinimumSize(new Dimension(300, 200));
        setMaximumSize(new Dimension(1200, 800));

        getSelectedLabel().setText(ADD_DEPENDENCY_MESSAGE + ": ");
        getSelectedLabel().setFont(FontCollection.DEFAULT_FONT_PLAIN);
        getSelectedNode().setFont(FontCollection.DEFAULT_FONT_PLAIN);
        getSelectedNode().setText(getTask().getTaskName());
    }


    private void setButtonLayout() {
        //can select cells
        getGraph().setCellsSelectable(true);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //set up button
        JButton selectButton = LayoutUtils.setButton(ADD_BUTTON_STRING, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON_STRING, this);

        panel.add(selectButton);
        panel.add(cancelButton);

        //set panel
        GridBagConstraints constraints = LayoutUtils.createConstraints(1, 2,
                new Insets(8, 8, 8, 8), GridBagConstraints.LAST_LINE_END);
        constraints.fill = GridBagConstraints.NONE;
        add(panel, constraints);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case ADD_BUTTON_STRING: {

                if (getGraph().getSelectionCell() == null) {
                    MessageGUI messageGUI = new MessageGUI("Invalid Selection", "You have not selected " +
                            "a task to add a dependency to. Please select a task.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    return;
                }
                //something has been selected
                String taskId = ((mxCell) getGraph().getSelectionCell()).getId();

                if (taskId.equals(subTaskGUI.getStringTaskMap()
                        .get(subTaskGUI.getTaskDropdown().getSelectedItem()).getTaskName())) {
                    //task is the overall task
                    OverallTask overallTask = getOverallTask();
                    overallTask.addSubTask(new SubTask(subTaskGUI.getTaskNameText(), subTaskGUI.getDuration()));
                } else {
                    //task is subtask
                    SubTask subTask = getSubTask(taskId);
                    subTask.addDependency(new SubTask(subTaskGUI.getTaskNameText(), subTaskGUI.getDuration()));
                }

                if (taskDataPanel != null) {
                    taskDataPanel.updateGanttChart();
                }

                close();
                subTaskGUI.close();
                break;
            }
            case CANCEL_BUTTON_STRING: {
                close();
                break;
            }
        }
    }
}
