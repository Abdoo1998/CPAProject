import GUI.OverallTaskGUI;
import GUI.SubTaskGUI;
import application.*;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //CPAProjectApplicationGUI application = new CPAProjectApplicationGUI();
        //javax.swing.SwingUtilities.invokeLater(application::createAndShowGUI);

        OverallTaskGUI overallTask = new OverallTaskGUI();
        javax.swing.SwingUtilities.invokeLater(overallTask::showGUI);

        Task t1 = new OverallTask("Morning routine", new Duration(0, 15), new Time(10, 40));
        Task t2 = new OverallTask("Afternoon routine", new Duration(0, 10), new Time(15, 30));
        Task t3 = new OverallTask("Dinner", new Duration(0, 45), new Time(21, 45));
        List<Task> tasks = new LinkedList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        SubTaskGUI subTaskGUI = new SubTaskGUI(tasks);
        javax.swing.SwingUtilities.invokeLater(subTaskGUI::showGUI);

    }

}
