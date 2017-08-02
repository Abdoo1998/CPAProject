import GUI.OverallTaskGUI;

public class Main {

    public static void main(String[] args) {
        //CPAProjectApplicationGUI application = new CPAProjectApplicationGUI();
        //javax.swing.SwingUtilities.invokeLater(application::createAndShowGUI);

        OverallTaskGUI overallTask = new OverallTaskGUI();
        javax.swing.SwingUtilities.invokeLater(overallTask::showGUI);

    }

}
