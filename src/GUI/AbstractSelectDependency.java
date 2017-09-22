package GUI;

import application.OverallTask;
import application.SubTask;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static GUI.TaskGUI.TOP_DEFAULT_INSETS;

/**
 * Abstract class representing the GUI to select a dependency. The class executes a given action and can supply a
 * message or a warning to the user after executing the action.
 *
 * @author gorosgobe
 */
public abstract class AbstractSelectDependency extends JFrame implements ActionListener, TreeSelectionListener {


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

    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";

    public AbstractSelectDependency(TaskDataPanel taskDataPanel, OverallTask task, String labelText) {
        this.task = task;
        this.taskDataPanel = taskDataPanel;
        setSelectedLabelAndSelectedNode(labelText);
        setTreeView(task);
        setScrollPane();
        setCustomLayout();
    }

    public TaskDataPanel getTaskDataPanel() {
        return taskDataPanel;
    }

    public OverallTask getTask() {
        return task;
    }

    public JLabel getSelectedLabel() {
        return selectedLabel;
    }

    public JLabel getSelectedNode() {
        return selectedNode;
    }

    public JScrollPane getTreePanel() {
        return treePanel;
    }

    public JTree getTree() {
        return tree;
    }

    /**
     * Sets the labels representing the text informing the user about selection of a node to be removed and the
     * text representation of the node selected.
     */
    private void setSelectedLabelAndSelectedNode(String labelText) {

        this.selectedLabel = new JLabel(labelText + ": ");
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
    private void setCustomLayout() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints selectedLabelConstraints = LayoutUtils.createConstraints(0, 0, TOP_DEFAULT_INSETS);
        add(selectedLabel, selectedLabelConstraints);

        GridBagConstraints selectedNodeStringConstraints = LayoutUtils.createConstraints(1, 0, TOP_DEFAULT_INSETS);
        add(selectedNode, selectedNodeStringConstraints);

        GridBagConstraints scrollPaneConstraints = LayoutUtils.createConstraints(0, 1);
        scrollPaneConstraints.gridwidth = 2;
        scrollPaneConstraints.gridheight = 2;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        add(treePanel, scrollPaneConstraints);

    }

    public void updateTreeView() {
        setTreeView(task);
        //sets the scroll panel's viewport to be the tree
        treePanel.setViewportView(tree);
        treePanel.revalidate();
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

    /**
     * Closes the frame.
     */
    protected void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
