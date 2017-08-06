package application;

import java.util.HashSet;
import java.util.Set;

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

    startNode.setEarliestCompletionTime(new Time(0,0));
    startNode.setLatestCompletionTime((new Time(0,0)));

    subTasks.stream()
            .filter(i -> i.getDependencies().isEmpty())
            .forEach(startNode::addOutgoingArc);


    // TODO:          ///////////////////
    // TODO:          /// FINISH THIS ///
    // TODO:          ///////////////////

    return graph;
  }
}
