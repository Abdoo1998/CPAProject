package application;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OverallTask extends Task {

  private final Time startTime;
  private final Set<SubTask> subTasks;

  public OverallTask(String name, Duration duration, Time startTime) {
    super(name, duration);
    this.startTime = startTime;
    this.subTasks = new HashSet<>();
  }

  public Set<SubTask> getAllSubTasks() {
    return subTasks;
  }

  public void addSubTask(SubTask task) {
    subTasks.add(task);
  }

  public void removeSubTask(SubTask task) {
      subTasks.remove(task);
    }

  public Time getStartTime() {
    return startTime;
  }

  public TaskGraph generateGraph() {
    TaskGraph graph = new TaskGraph();

    TaskGraphNode startNode = graph.getStartNode();
    TaskGraphNode endNode   = graph.getEndNode();

    startNode.setEarliestCompletionTime(new Time(0,0));
    startNode.setLatestCompletionTime((new Time(0,0)));

    subTasks.stream()
            .filter(i -> i.getDependsOnMe().isEmpty())
            .forEach(j -> {
              endNode.addIncomingArc(j, new TaskGraphNode());

              // Not sure whether to remove from the set yet.
              // subTasks.remove(j);
            });

    endNode.getIncomingArcs().forEach(i ->
      {
        TaskGraphNode currentNode = i.getParent();

        subTasks.stream()
            .filter(j -> j.getDependsOnMe().contains(i.getTask()))
            .forEach(k -> currentNode.addIncomingArc(k, new TaskGraphNode()));
      });

    return graph;
  }

  private void recurseBackwards(TaskGraph graph, TaskGraphNode currentNode) {
//    SubTask outgoingTask = (SubTask) currentNode.getOutgoingArcs().getTask();

//    Set dependencies = subTasks.stream()
//        .filter(i -> outgoingTask.getDependsOnMe().contains(i))
//        .collect(Collectors.toSet());

//    if (dependencies.isEmpty()) {
//      TaskGraphArc startingArc = currentNode.getOutgoingArc();
//      startingArc.setParent(graph.getStartNode());
////      graph.getStartNode().addOutgoingArc();
//      currentNode.getOutgoingArc().setParent(graph.getStartNode());
//    }

//    .forEach(j -> {
//          TaskGraphNode newNode = new TaskGraphNode();
//          currentNode.addIncomingArc(j, newNode);
//          recurseBackwards(graph, newNode);
//        });


    ////////////////////////////////////////////////////////
    //////////////// STILL WORKING ON IT!!! ////////////////
    ////////////////////////////////////////////////////////
  }
}
