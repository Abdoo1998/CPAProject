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
public class AddDependencyGraphView extends GraphView implements ActionListener {

    private static final String SELECT_BUTTON_STRING = "Select";
    private SubTaskGUI subTaskGUI;

    public AddDependencyGraphView(String title, OverallTask task, SubTaskGUI subTaskGUI) {
        super(title, task);
        this.subTaskGUI = subTaskGUI;
        setMinimumSize(new Dimension(300, 200));
        setMaximumSize(new Dimension(1200, 800));

        //can select cells
        getGraph().setCellsSelectable(true);
        JButton selectButton = LayoutUtils.setButton(SELECT_BUTTON_STRING, this);

        //set button
        GridBagConstraints constraints = LayoutUtils.createConstraints(1, 2,
                new Insets(8, 8, 8, 8), GridBagConstraints.LAST_LINE_END);
        constraints.fill = GridBagConstraints.NONE;
        add(selectButton, constraints);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case SELECT_BUTTON_STRING: {

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

                close();
                subTaskGUI.close();
            }
        }
    }
}
