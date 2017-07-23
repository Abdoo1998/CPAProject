
/*
* Created by gorosgobe on 23/07/2017
 */


import javax.swing.*;

public class CPAProjectApplication {

    /*
    Main purpose of the class is to run the application and initiate the GUI
     */

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(CPAProjectApplication::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CPAProject");
        // Sets what to do when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds label with content
        JLabel label = new JLabel("Starting with the CPA Project");

        //adds label to frame
        frame.getContentPane().add(label);

        //shows the frame
        frame.pack();
        frame.setVisible(true);
    }


}
