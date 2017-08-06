package application;

public class Time {

  private int hours;
  private int minutes;

  public Time(int hours, int minutes) {
    assert (hours >= 0 && hours < 24 || minutes >= 0 || minutes > 59):
        "application.Time Error: Incorrect time format";
    this.hours = hours;
    this.minutes = minutes;
  }

  public int getHours() {
    return hours;
  }

  public int getMinutes() {
    return minutes;
  }

  @Override
  public String toString() {
    return hours + ":" + minutes;
  }

  //pre: otherTime is later than current time
  public Duration getTimeDifference(Time otherTime) {
    int minuteDifference = Math.abs((otherTime.getMinutes() - minutes) % 60);
    int hourDifference = Math.abs((otherTime.getHours() - hours) % 23);

    if (minuteDifference < 0) {
      hourDifference--;
      minuteDifference += 60;
    }

    return new Duration(hourDifference, minuteDifference);
  }
}
