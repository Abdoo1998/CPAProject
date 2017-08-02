import java.util.HashSet;
import java.util.Set;

public class TaskGraphNode {

  private Time earliestCompletionTime;
  private Set<TaskGraphArc> incoming;
  private Set<TaskGraphArc> outgoing;

  public TaskGraphNode() {
    this.incoming = new HashSet<>();
    this.outgoing = new HashSet<>();
  }

  public Time getEarliestCompletionTime() {
    return earliestCompletionTime;
  }

  public Set<TaskGraphArc> getIncomingArcs() {
    return incoming;
  }

  public Set<TaskGraphArc> getOutgoingArcs() {
    return outgoing;
  }
}
