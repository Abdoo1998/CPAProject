public class Time {

  private int hours;
  private int minutes;

  public Time(int hours, int minutes) {
    assert (hours >= 0 && hours < 24 || minutes >= 0 || minutes > 59):
        "Time Error: Incorrect time format";
    this.hours = hours;
    this.minutes = minutes;
  }

  public int getHours() {
    return hours;
  }

  public int getMinutes() {
    return minutes;
  }
}
