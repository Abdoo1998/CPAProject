import java.util.List;

public class TaskGraphNode {

  private Task task;
  private List<TaskGraphNode> incoming;
  private List<TaskGraphNode> outgoing;

  public TaskGraphNode(Task task) {
    this.incoming = null;
    this.outgoing = null;
  }


}
