package GUI;

import application.OverallTask;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * GUI to add a dependency to a task from its task data panel. Calls an add dependency graph view, which handles the
 * selection and addition.
 *
 * @author gorosgobe
 */
public class AddSubTaskGUI extends SubTaskGUI {

    private TaskDataPanel taskDataPanel;

    public AddSubTaskGUI(List<OverallTask> tasksToShow, TaskDataPanel taskDataPanel) {
        super(tasksToShow);
        this.taskDataPanel = taskDataPanel;
        getTaskDropdown().setEnabled(false);
    }

    public AddSubTaskGUI(List<OverallTask> tasksToShow, OverallTask toBeSelectedTask, TaskDataPanel taskDataPanel) {
        super(tasksToShow, toBeSelectedTask);
        this.taskDataPanel = taskDataPanel;
        getTaskDropdown().setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(getButtonString())) {
            AddSubTaskGraphView addSubTaskGraphView = new AddSubTaskGraphView(super.getTitle(),
                    taskDataPanel.getTask(), this, taskDataPanel);
            javax.swing.SwingUtilities.invokeLater(addSubTaskGraphView::showGUI);
        }
    }
}
