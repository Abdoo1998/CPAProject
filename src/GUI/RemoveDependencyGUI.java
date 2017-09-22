package GUI;

import application.OverallTask;
import application.SubTask;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static GUI.TaskGUI.DEFAULT_INSETS;
import static GUI.TaskGUI.TOP_DEFAULT_INSETS;

/**
 * Represents the GUI to remove a depencency from an Overall Task in the task's data panel.
 *
 * @author gorosgobe
 */
public class RemoveDependencyGUI extends JFrame implements ActionListener, TreeSelectionListener {

    /** A reference to the task data panel*/
    private final TaskDataPanel taskDataPanel;
    /** A reference to the Overall Task to remove the dependency from*/
    private OverallTask task;
    /** A reference to a Label with a string indicating which node was selected*/
    private JLabel selectedLabel;
    /** A reference to the selected node in the tree, representing a subtask to remove */
    private JLabel selectedNode;
    /** A reference to the scroll pane with the tree view*/
    private JScrollPane treePanel;
    /** A reference to the tree view*/
    private JTree tree;

    //CONSTANTS
    /** Title of the frame*/
    private static final String FRAME_TITLE = "Remove Dependency";
    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";
    /** String representing the string on the remove button*/
    private static final String REMOVE_BUTTON_STRING = "Remove";
    /** String representing the string on the cancel button*/
    private static final String CANCEL_BUTTON_STRING = "Cancel";
    /** String representing the string on the label*/
    private static final String REMOVE_DEPENDENCY_MESSAGE = "Remove dependency";

    public RemoveDependencyGUI(TaskDataPanel taskDataPanel, OverallTask task) {
        this.task = task;
        this.taskDataPanel = taskDataPanel;
        setSelectedLabelAndSelectedNode();
        setTreeView(task);
        setScrollPane();
        JButton removeButton = LayoutUtils.setButton(REMOVE_BUTTON_STRING, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON_STRING, this);
        setCustomLayout(removeButton, cancelButton);
    }

    /**
     * Sets the labels representing the text informing the user about selection of a node to be removed and the
     * text representation of the node selected.
     */
    private void setSelectedLabelAndSelectedNode() {

        this.selectedLabel = new JLabel(REMOVE_DEPENDENCY_MESSAGE + ": ");
        selectedLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //the selectedNode text will be determined by the selection of a particular node in the tree view
        //initial is default overall task
        this.selectedNode = new JLabel(task.getTaskName());
        selectedNode.setFont(FontCollection.DEFAULT_FONT_PLAIN);

    }

    /**
     * Sets the JScrollPane that contains the tree view.
     */
    private void setScrollPane() {
        //this is done outside of setTreeView because otherwise upon selection of Tasks on the
        //task dropdown JComboBox, the treePanel wouldn't update
        this.treePanel = new JScrollPane(tree);
    }

    /**
     * Sets the JTree to be a representation of all dependencies of the given OverallTask.
     * @param overallTask the task to represent in a tree view (root task)
     */
    private void setTreeView(OverallTask overallTask) {
        //sets root with selected item
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(overallTask.getTaskName());

        for (SubTask task : overallTask.getAllSubTasks()) {
            root.add(setTreeRecursivelyFrom(task));
        }

        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        this.tree = new JTree(treeModel);
        //allows for single selection
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //adds frame as listener
        tree.addTreeSelectionListener(this);
        //selects first node (OverallTask) by default
        DefaultMutableTreeNode overallNode = (DefaultMutableTreeNode) treeModel.getRoot();
        tree.setSelectionPath(new TreePath(overallNode.getPath()));
    }

    /**
     * Recursive helper for setTreeView. Sets the tree view (DefaultMutableTreeNode) for the given subtask
     * and its dependencies.
     * @param task the subtask to construct a node from
     * @return the constructed tree node from the task given
     */
    private DefaultMutableTreeNode setTreeRecursivelyFrom(SubTask task) {

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(task.getTaskName());

        //base case
        if (task.getDependencies() == null) {
            return new DefaultMutableTreeNode(task.getTaskName());
        }

        //set the tree recursively for each subtask
        for (SubTask t : task.getDependencies()) {
            node.add(setTreeRecursivelyFrom(t));
        }

        return node;

    }

    /**
     * Sets the custom layout for the remove dependency GUI.
     */
    private void setCustomLayout(JButton removeButton, JButton cancelButton) {

        this.setLayout(new GridBagLayout());

        //creates a panel for both buttons
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(removeButton);
        panel.add(cancelButton);

        GridBagConstraints selectedLabelConstraints = LayoutUtils.createConstraints(0, 0, TOP_DEFAULT_INSETS);
        add(selectedLabel, selectedLabelConstraints);

        GridBagConstraints selectedNodeStringConstraints = LayoutUtils.createConstraints(1, 0, TOP_DEFAULT_INSETS);
        add(selectedNode, selectedNodeStringConstraints);

        GridBagConstraints scrollPaneConstraints = LayoutUtils.createConstraints(0, 1);
        scrollPaneConstraints.gridwidth = 2;
        scrollPaneConstraints.gridheight = 2;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        add(treePanel, scrollPaneConstraints);

        GridBagConstraints buttonsConstraints = LayoutUtils.createConstraints(1,3, DEFAULT_INSETS,
                GridBagConstraints.LAST_LINE_END);
        add(panel, buttonsConstraints);

    }

    /**
     * Initialises and shows the Remove Dependency GUI JFrame
     */
    public void createAndShowGUI() {

        setTitle(FRAME_TITLE);
        setResizable(false);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //shows the frame
        pack();
        setLocationRelativeTo(null); //centers frame
        setVisible(true);
    }

    /**
     * Closes the frame.
     */
    private void close() {
      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case CANCEL_BUTTON_STRING: {
                this.close();
                break;
            }
            case REMOVE_BUTTON_STRING: {
                //if the selected task is the overall task, give warning to user and dont do anything
                if (selectedNode.getText().equals(task.getTaskName())) {
                    MessageGUI messageGUI = new MessageGUI("Warning", "The task to remove that you " +
                            "selected is the main task: cannot be removed as it is not a dependency. Please choose " +
                            "a dependency of the main task.");
                    javax.swing.SwingUtilities.invokeLater(messageGUI::createAndShowGUI);
                    break;
                }
                //remove dependency from task
                SubTask subTask = SubTask.findSubTaskInDependencies(task, selectedNode.getText());
                //if parent task is an overall task, then delete directly
                if (task.getAllSubTasks().contains(subTask)) {
                    //POSSIBLY ISSUE A WARNING TO USER, AS THIS WILL DELETE ALL DEPENDENCIES OF THE GIVEN SUBTASK
                    task.removeSubTask(subTask);
                } else {
                    //otherwise, find parent task
                    SubTask parent = SubTask.findParentSubTaskOf(task, selectedNode.getText());
                    parent.removeDependency(subTask);
                }
                //update gantt chart
                taskDataPanel.updateGanttChart();
                this.close();
                break;
            }
        }
    }

    //as with SubTaskGUI
    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) {
            //Nothing is selected.
            return;
        }

        selectedNode.setText((String) node.getUserObject());
    }
}
