package application;

import java.util.HashSet;
import java.util.Set;

public class TaskGraphNode {

  private Time earliestCompletionTime;
  private Time latestCompletionTime;
  private Set<TaskGraphArc> incoming;
  private Set<TaskGraphArc> outgoing;
  private int nodeID;
  private static int count = 0;
  private int degree;

  public TaskGraphNode() {
    nodeID = count++;
    this.incoming = new HashSet<>();
    this.outgoing = new HashSet<>();
  }

  public int getNodeID() {
    return nodeID;
  }

  public int getDegree() {
    return degree;
  }

  public void setDegree(int degree) {
    this.degree = degree;
  }

  public Time getEarliestCompletionTime() {
    return earliestCompletionTime;
  }

  public Time getLatestCompletionTime() {
    return latestCompletionTime;
  }

  public void setEarliestCompletionTime(Time earliestCompletionTime) {
    this.earliestCompletionTime = earliestCompletionTime;
  }

  public void setLatestCompletionTime(Time latestCompletionTime) {
    this.latestCompletionTime = latestCompletionTime;
  }

  public Duration getFloat() {
    return earliestCompletionTime.getTimeDifference(latestCompletionTime);
  }

  public Set<TaskGraphArc> getIncomingArcs() {
    return incoming;
  }

  public Set<TaskGraphArc> getOutgoingArcs() {
    return outgoing;
  }

  public void addOutgoingArc(Task task) {
    outgoing.add(new TaskGraphArc(task, this, new TaskGraphNode()));
  }

  public void setIncoming(Set<TaskGraphArc> incoming) {
    this.incoming = incoming;
  }

  public void setOutgoing(Set<TaskGraphArc> outgoing) {
    this.outgoing = outgoing;
  }
}
