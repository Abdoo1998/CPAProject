package GUI;

import application.OverallTask;

import javax.swing.event.TreeSelectionEvent;
import java.awt.event.ActionEvent;

/**
 * Class representing the GUI used to
 */
public class SelectEditDependency extends AbstractSelectDependency {

    public SelectEditDependency(TaskDataPanel taskDataPanel, OverallTask task) {
        super(taskDataPanel, task);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {

    }
}
