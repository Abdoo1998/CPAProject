import GUI.CPAProjectApplicationGUI;
import application.Duration;
import application.OverallTask;
import application.SubTask;
import application.Time;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        CPAProjectApplicationGUI application = new CPAProjectApplicationGUI();
        javax.swing.SwingUtilities.invokeLater(application::createAndShowGUI);

        //OverallTaskGUI overallTask = new OverallTaskGUI();
        //javax.swing.SwingUtilities.invokeLater(overallTask::showGUI);

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        OverallTask t1 = new OverallTask("Morning routine", new Duration(0, 15), new Time(10, 40), "This is my morning routine");
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

        Duration dummyDuration = new Duration(0, 10);
        Time dummyTime = new Time(0, 10);

        OverallTask overallTask = new OverallTask("A", dummyDuration, dummyTime);

        SubTask B = new SubTask("B", dummyDuration);
        SubTask C = new SubTask("C", dummyDuration);
        SubTask D = new SubTask("D", dummyDuration);
        SubTask E = new SubTask("E", dummyDuration);
        SubTask F = new SubTask("F", dummyDuration);
        SubTask G = new SubTask("G", dummyDuration);
        SubTask H = new SubTask("H", dummyDuration);
        SubTask I = new SubTask("I", dummyDuration);
        SubTask J = new SubTask("J", dummyDuration);
        SubTask K = new SubTask("K", dummyDuration);
        SubTask L = new SubTask("L", dummyDuration);

        overallTask.addSubTask(B);
        overallTask.addSubTask(C);
        overallTask.addSubTask(D);
        overallTask.addSubTask(E);

        B.addDependency(F);
        C.addDependency(F);
        D.addDependency(F);
        D.addDependency(I);
        D.addDependency(K);
        E.addDependency(G);
        F.addDependency(H);
        F.addDependency(I);
        H.addDependency(J);
        I.addDependency(J);
        I.addDependency(H);
        J.addDependency(K);
        G.addDependency(K);
        G.addDependency(L);

//        SubTaskGUI subTaskGUI = new SubTaskGUI(tasks);
//        javax.swing.SwingUtilities.invokeLater(subTaskGUI::showGUI);


        application.addOverallTask(t1);
        application.addOverallTask(t3);
        application.addOverallTask(t2);
        application.addOverallTask(overallTask);

        application.revalidate();

    }

}
