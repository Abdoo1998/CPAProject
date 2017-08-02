import java.util.HashSet;
import java.util.Set;
import java.time.Duration;

public class Task {

  private final String name;
  private final Time startTime;
  private final Duration duration;
  private final Set<Task> dependencies;

  public Task(String name, Time startTime, Duration duration) {
    this.name = name;
    this.startTime = startTime;
    this.duration = duration;
    this.dependencies = new HashSet<>();
  }

  public Time getStartTime() {
    return startTime;
  }

  public Duration getDuration() {
    return duration;
  }

  public Set<Task> getDependencies() {
    return dependencies;
  }

  public void addDependency(Task newDep) {
    dependencies.add(newDep);
  }

  public TaskGraph generateGraph() {
    // generates a 'tree/map' type structure as
    return null;
  }
}
