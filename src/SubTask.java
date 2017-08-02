import java.util.ArrayList;
import java.util.List;

public class SubTask extends Task {

  private final List<SubTask> dependencies;

  public SubTask(String name, Duration duration) {
    super(name, duration);
    this.dependencies = new ArrayList<>();
  }

  public List<SubTask> getDependencies() {
    return dependencies;
  }

  public void addDependency(SubTask dep) {
    dependencies.add(dep);
  }

  public void removeDependancy(SubTask dep) {
    dependencies.remove(dep);
  }
}
