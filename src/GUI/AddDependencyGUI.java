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
public class AddDependencyGUI extends SubTaskGUI {

    private TaskDataPanel taskDataPanel;

    public AddDependencyGUI(List<OverallTask> tasksToShow, TaskDataPanel taskDataPanel) {
        super(tasksToShow);
        this.taskDataPanel = taskDataPanel;
    }

    public AddDependencyGUI(List<OverallTask> tasksToShow, OverallTask toBeSelectedTask, TaskDataPanel taskDataPanel) {
        super(tasksToShow, toBeSelectedTask);
        this.taskDataPanel = taskDataPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AddDependencyGraphView addDependencyGraphView = new AddDependencyGraphView(super.getTitle(),
                taskDataPanel.getTask(), this, taskDataPanel);
        javax.swing.SwingUtilities.invokeLater(addDependencyGraphView::showGUI);
    }
}
