package GUI;


import application.*;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class GraphView extends JFrame {

    private OverallTask task;
    private CPAGraph graph;
    private mxGraphComponent graphComponent;
    private JScrollPane scrollPane;
    private Map<String, Task> idToTask;

    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 40;
    private static final int VERTICAL_SCROLL_SPEED = 18;
    private static final int HORIZONTAL_SCROLL_SPEED = 18;

    public GraphView(String title, OverallTask task) {
        super(title);
        setResizable(false);

        this.task = task;
        this.idToTask = new HashMap<>();
        this.graph = new CPAGraph();
        Object parent = graph.getDefaultParent();

        graph.setCellsEditable(false);
        graph.setCellsMovable(false);
        graph.setCellsResizable(false);
        graph.setCellsSelectable(false);
        graph.setConnectableEdges(false);
        graph.setEdgeLabelsMovable(false);
        graph.setAllowDanglingEdges(false);
        //sets only selection of one cell
        graph.getSelectionModel().setSingleSelection(true);

        //sets up the graph component and draws the graph
        this.graphComponent = insertAndDrawAllTasks(parent, task);
        graphComponent.setPreferredSize(graphComponent.getPreferredSize());
        this.scrollPane = new JScrollPane(graphComponent);
        scrollPane.setPreferredSize(scrollPane.getPreferredSize());
        scrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_SPEED);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(HORIZONTAL_SCROLL_SPEED);
        scrollPane.setBorder(null);
        this.setLayout(new GridBagLayout());
        setCustomLayout(scrollPane);

    }

    private void setCustomLayout(JScrollPane scrollPane) {
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.weightx = 1;
        panelConstraints.weighty = 1;
        panelConstraints.gridwidth = 2;
        panelConstraints.gridwidth = 2;
        panelConstraints.insets = new Insets(8, 8, 8, 8);
        panelConstraints.fill = GridBagConstraints.BOTH;
        add(scrollPane, panelConstraints);
    }

    public CPAGraph getGraph() {
        return graph;
    }

    public mxGraphComponent getGraphComponent() {
        return graphComponent;
    }

    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private mxGraphComponent insertAndDrawAllTasks(Object parent, OverallTask task) {

        Map<SubTask, Object> subTaskToNode = new HashMap<>();

        graph.getModel().beginUpdate();

        try {

            Object overallTaskNode = createAndInsertVertex(parent, task);

            for (SubTask child : task.getAllSubTasks()) {
                //insert first child
                Object childNode = createAndInsertVertex(parent, child);
                //connect the overall task with its children
                insertEdge(parent, overallTaskNode, childNode);
                //child node has been added
                subTaskToNode.put(child, childNode);
                //insert all children in each of the branches
                insertRecursivelyIntoGraph(parent, child, childNode, subTaskToNode);
            }

        } finally {
            graph.getModel().endUpdate();
        }

        //adds the graph to the frame
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);

        //sets up the hierarchical layout
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setOrientation(SwingConstants.WEST);
        layout.execute(parent);

        return graphComponent;
    }

    private void insertRecursivelyIntoGraph(Object parent, SubTask t, Object parentNode, Map<SubTask, Object> subTaskToNode) {
        //base case
        if (t.getDependencies().isEmpty()) {
            return;
        }

        for (SubTask child : t.getDependencies()) {

            if (subTaskToNode.keySet().contains(child)) {
                //child has alread been added, no need to draw another object, just the edge from the parent task to
                // the child

                //in the case when there is alread an edge between parentNode and the childNode, then dont draw the edge
                //this solves the case when a child node is hit multiple times by an edge from a parent node due to the
                //parent node being hit by different edges from differen upper nodes
                if (graph.getEdgesBetween(parentNode, subTaskToNode.get(child)).length == 0) {
                    //no edges then insert edge
                    insertEdge(parent, parentNode, subTaskToNode.get(child));
                    insertRecursivelyIntoGraph(parent, child, subTaskToNode.get(child), subTaskToNode);
                    continue;
                } else {
                    //avoids duplication of edges
                    insertRecursivelyIntoGraph(parent, child, subTaskToNode.get(child), subTaskToNode);
                    continue;
                }
            }

            //child has not been added
            Object childNode = createAndInsertVertex(parent, child);
            insertEdge(parent, parentNode, childNode);
            //add child
            subTaskToNode.put(child, childNode);
            //recurse
            insertRecursivelyIntoGraph(parent, child, childNode, subTaskToNode);
        }

    }

    public Object createAndInsertVertex(Object parent, OverallTask task) {
        //precondition is that id == 0
        idToTask.put(task.getTaskName(), task);
        //increments id after putting the overall task into the map
        return graph.insertVertex(parent, task.getTaskName(), task.getTaskName(), 0, 0,
                DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Object createAndInsertVertex(Object parent, SubTask subTask) {
        idToTask.put(subTask.getTaskName(), subTask);
        Object vertex = graph.insertVertex(parent, subTask.getTaskName(), subTask.getTaskName(), 0, 0,
                DEFAULT_WIDTH, DEFAULT_HEIGHT);
        return vertex;
    }

    public Map<String, Task> getIdToTask() {
        return idToTask;
    }

    public void updateGraph() {
        this.graph = new CPAGraph();
        Object parent = graph.getDefaultParent();

        graph.setCellsEditable(false);
        graph.setCellsMovable(false);
        graph.setCellsResizable(false);
        graph.setCellsSelectable(false);
        graph.setConnectableEdges(false);

        //sets up the graph component and draws the graph
        this.graphComponent = insertAndDrawAllTasks(parent, task);
        graphComponent.setPreferredSize(graphComponent.getPreferredSize());
        //update scroll pane
        scrollPane.setViewportView(graphComponent);
        scrollPane.revalidate();

    }

    public SubTask getSubTask(String id) {
        return (SubTask) idToTask.get(id);
    }

    public OverallTask getOverallTask() {
        return task;
    }

    public void insertEdge(Object parent, Object node1, Object node2) {
       graph.insertEdge(parent, null, null, node1, node2);
    }

    public void showGUI() {
        pack();
        // shows GUI in center of application frame. Requires to be called after pack() and before setVisible(true)
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static OverallTask createTest() {
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


        return overallTask;
    }

    public static void main(String[] args) {


        GraphView frame = new GraphView("Graph View", createTest());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
