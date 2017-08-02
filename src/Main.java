import GUI.CPAProjectApplicationGUI;

public class Main {

    public static void main(String[] args) {
        CPAProjectApplicationGUI application = new CPAProjectApplicationGUI();
        javax.swing.SwingUtilities.invokeLater(application::createAndShowGUI);

    }

}
