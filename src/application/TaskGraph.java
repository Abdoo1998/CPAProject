package application;

public class TaskGraph {

  private TaskGraphNode start;
  private TaskGraphNode end;

  public TaskGraph() {
    this.start = new TaskGraphNode();
    this.end   = new TaskGraphNode();
  }

  public TaskGraphNode getStartNode() {
    return start;
  }

  public TaskGraphNode getEndNode() {
    return end;
  }
}
