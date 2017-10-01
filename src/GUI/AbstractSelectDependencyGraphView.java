package GUI;


import application.OverallTask;
import com.mxgraph.model.mxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static GUI.TaskGUI.TOP_DEFAULT_INSETS;

public abstract class AbstractSelectDependencyGraphView extends GraphView implements ActionListener {

    /** A reference to the task data panel*/
    private TaskDataPanel taskDataPanel;
    /** A reference to a Label with a string indicating which node was selected*/
    private JLabel selectedLabel;
    /** A reference to the selected node in the tree, representing a subtask to remove */
    private JLabel selectedNode;

    //CONSTANTS
    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";

    public AbstractSelectDependencyGraphView(String title, OverallTask task, TaskDataPanel taskDataPanel) {
        super(title, task);
        this.taskDataPanel = taskDataPanel;
        this.selectedLabel = new JLabel();
        this.selectedNode = new JLabel();

        //adds selection indicator
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                Object cell = getGraphComponent().getCellAt(mouseEvent.getX(), mouseEvent.getY());
                if (cell != null && cell instanceof mxCell && !getGraph().getModel().isEdge(cell)) {
                    String id = ((mxCell) cell).getId();
                    getSelectedNode().setText(id);
                } else {
                    getSelectedNode().setText("None selected");
                }
            }
        };

        getGraphComponent().getGraphControl().addMouseListener(adapter);

        setCustomLayout();
    }

    public TaskDataPanel getTaskDataPanel() {
        return taskDataPanel;
    }

    public OverallTask getTask() {
        return super.getOverallTask();
    }

    public JLabel getSelectedLabel() {
        return selectedLabel;
    }

    public JLabel getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the custom layout for the GUI.
     */
    private void setCustomLayout() {

        JPanel labelAndNode = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelAndNode.add(getSelectedLabel());
        labelAndNode.add(getSelectedNode());

        GridBagConstraints selectedLabelAndNodeConstraints = LayoutUtils.createConstraints(0, 0,
                TOP_DEFAULT_INSETS, GridBagConstraints.FIRST_LINE_START);
        add(labelAndNode, selectedLabelAndNodeConstraints);

    }


    /**
     * Initialises and shows the Remove Dependency GUI JFrame
     */
    public void createAndShowGUI() {

        setResizable(false);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //shows the frame
        pack();
        setLocationRelativeTo(null); //centers frame
        setVisible(true);
    }
}
