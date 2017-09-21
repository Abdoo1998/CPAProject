package application;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OverallTask extends Task {

  private Time startTime;
  private String description = "";
  private final Set<SubTask> subTasks;

  public OverallTask(String name, Duration duration, Time startTime) {
    super(name, duration);
    this.startTime = startTime;
    this.subTasks = new HashSet<>();
  }

  public OverallTask(String name, Duration duration, Time startTime, String description) {
    super(name, duration);
    this.startTime = startTime;
    this.description = description;
    this.subTasks = new HashSet<>();
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  @Override
  public String toString() {
    return "[" + getTaskName() + ", " + getDuration() + ", " + getStartTime() + "]\n";
  }

  //  public TaskGraph generateGraph() {
//    TaskGraph graph = new TaskGraph();
//
//    TaskGraphNode startNode = graph.getStartNode();
//    TaskGraphNode endNode   = graph.getEndNode();
//
//    startNode.setEarliestCompletionTime(new Time(0,0));
//    startNode.setLatestCompletionTime(new Time(0,0));
//
//    subTasks.stream()
//            .filter(i -> i.getDependsOnMe().isEmpty())
//            .forEach(j -> endNode.addIncomingArc(j, new TaskGraphNode()));
//
//    endNode.getIncomingArcs().forEach(i -> recurseBackwards(graph, i.getParent()));
//
//    return graph;
//  }
//
//  private void recurseBackwards(TaskGraph graph, TaskGraphNode currentNode) {
//    Set<TaskGraphArc> outgoingArcs = currentNode.getOutgoingArcs();
//
//    Set<Task> outgoingTasks = outgoingArcs.stream()
//        .map(TaskGraphArc::getTask).collect(Collectors.toSet());
//
//    subTasks.stream()
//            .filter(i -> anyOutgoingTaskDependsOnThis(i, outgoingTasks))
//            .forEach(j -> {
//              subTasks.remove(j);
//              TaskGraphNode newParent;
//
//              newParent = subTasks.isEmpty() ? graph.getStartNode() : new
//                  TaskGraphNode();
//
//              currentNode.addIncomingArc(j, newParent);
//              subTasks.remove(j);
//
//              if (newParent != graph.getStartNode()) {
//                recurseBackwards(graph, newParent);
//              }
//            });
//
//    ////////////////////////////////////////////////////////
//    //////////////// STILL WORKING ON IT!!! ////////////////
//    ////////////////////////////////////////////////////////
//  }
//
//
//  private boolean thisTaskDependsOnThat(Task thisTask, Task thatTask) {
//    if (!(thisTask instanceof SubTask && thatTask instanceof SubTask)) {
//      return false;
//    }
//
//    return ((SubTask) thisTask).getDependencies().contains(thatTask);
//  }
//
//  private boolean anyOutgoingTaskDependsOnThis(Task thisTask, Set<Task>
//      outgoingTasks) {
//
//    for (Task t : outgoingTasks) {
//      if (thisTaskDependsOnThat(t, thisTask)) {
//        return true;
//      }
//    }
//
//    return false;
//  }

  public TaskGraph generateGraph() {
    TaskGraph graph = new TaskGraph();

    TaskGraphNode startNode = graph.getStartNode();

    subTasks.stream()
        .filter(i -> i.getDependencies().isEmpty())
        .forEach(startNode::addOutgoingArc);

    startNode.getOutgoingArcs().forEach(i -> recurseForward(graph, i));

    return graph;
  }

  private void recurseForward(TaskGraph graph, TaskGraphArc currentArc) {
    Set<SubTask> nextTasks = subTasks.stream()
        .filter(i -> i.getDependencies().contains((SubTask) currentArc.getTask
            ()))
        .collect(Collectors.toSet());

    if (nextTasks.isEmpty()) {
      currentArc.setChild(graph.getEndNode());
      return;
    }

    TaskGraphNode newNode = new TaskGraphNode();
    currentArc.setChild(newNode);

    nextTasks.forEach(i -> {
      TaskGraphArc existingArc = graph.findArc(i);
      if (existingArc != null) {
        if (existingArc.getParent().getOutgoingArcs().size() > 1) {
          TaskGraphNode intermediateNode = new TaskGraphNode();
          intermediateNode.addOutgoingArc(existingArc.getTask());
          existingArc.getParent().getOutgoingArcs().remove(existingArc);
          TaskGraphArc.newDummy(existingArc.getParent(), intermediateNode);
          TaskGraphArc.newDummy(newNode, intermediateNode);
          existingArc.setParent(intermediateNode);

        }


        TaskGraphArc.newDummy(newNode, existingArc.getParent());
      } else {
        newNode.addOutgoingArc(i);
        recurseForward(graph, new TaskGraphArc(i, newNode, null));
      }
    });
  }


}
