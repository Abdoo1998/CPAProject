package GUI;

import application.OverallTask;

import java.awt.event.ActionEvent;

/**
 * Class representing the GUI for the selection of two tasks and the addition of the second one as a dependency
 * of the first one.
 *
 * @author gorosgobe
 */
public class NewDependencyGraphView extends AbstractSelectDependencyGraphView {

    public NewDependencyGraphView(String title, OverallTask task, TaskDataPanel taskDataPanel) {
        super(title, task, taskDataPanel);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
