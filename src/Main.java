import GUI.CPAProjectApplicationGUI;
import GUI.SubTaskGUI;
import application.Duration;
import application.OverallTask;
import application.SubTask;
import application.Time;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CPAProjectApplicationGUI application = new CPAProjectApplicationGUI();
        //javax.swing.SwingUtilities.invokeLater(application::createAndShowGUI);

        //OverallTaskGUI overallTask = new OverallTaskGUI();
        //javax.swing.SwingUtilities.invokeLater(overallTask::showGUI);

        OverallTask t1 = new OverallTask("Morning routine", new Duration(0, 15), new Time(10, 40));
        OverallTask t2 = new OverallTask("Afternoon routine", new Duration(0, 10), new Time(15, 30));
        OverallTask t3 = new OverallTask("Dinner", new Duration(0, 45), new Time(21, 45));
        List<OverallTask> tasks = new LinkedList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        SubTask s1 = new SubTask("Breakfast", new Duration(0,15));
        SubTask s2 = new SubTask("Tea", new Duration(0,20));
        SubTask s3 = new SubTask("Toast", new Duration(0,5));
        SubTask s4 = new SubTask("Brush teeth", new Duration(0,5));

        SubTask s11 = new SubTask("Get Milk", new Duration(0,1));
        SubTask s12 = new SubTask("Get cup", new Duration(0,1));

        SubTask s21 = new SubTask("Get water", new Duration(0,1));
        SubTask s22 = new SubTask("Get cup2", new Duration(0,1));

        t1.addSubTask(s1);
        t1.addSubTask(s2);
        t2.addSubTask(s3);
        t3.addSubTask(s4);

        s1.addDependency(s11);
        s1.addDependency(s12);
        s2.addDependency(s21);
        s2.addDependency(s22);

        SubTaskGUI subTaskGUI = new SubTaskGUI(tasks, application);
        javax.swing.SwingUtilities.invokeLater(subTaskGUI::showGUI);


        application.addOverallTask((OverallTask) t2);
        application.addOverallTask((OverallTask) t3);
        application.revalidate();
        Thread.sleep(500);
        application.addOverallTask((OverallTask) t2);
        application.addOverallTask((OverallTask) t3);
        application.revalidate();
        Thread.sleep(500);
        application.addOverallTask((OverallTask) t2);
        application.addOverallTask((OverallTask) t3);
        application.revalidate();




    }

}
