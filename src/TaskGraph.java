public class TaskGraph {

  private TaskGraphNode start;
  private TaskGraphNode end;

  public TaskGraph() {
    this.start = new TaskGraphNode(null);
    this.end   = new TaskGraphNode(null);
  }
}
