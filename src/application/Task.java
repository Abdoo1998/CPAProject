package application;

public abstract class Task {

  private String name;
  private Duration duration;

  public Task(String name, Duration duration) {
    this.name = name;
    this.duration = duration;
  }

  public String getTaskName() {
    return name;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Task task = (Task) o;

    if (name != null ? !name.equals(task.name) : task.name != null)
      return false;
    return duration != null ? duration.equals(task.duration) : task.duration == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (duration != null ? duration.hashCode() : 0);
    return result;
  }
}
