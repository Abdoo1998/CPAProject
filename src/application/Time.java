package application;

public class Time implements Comparable<Time> {

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
    int hourDifference   = Math.abs((otherTime.getHours() - hours) % 23);

    if (minuteDifference < 0) {
      hourDifference--;
      minuteDifference += 60;
    }

    return new Duration(hourDifference, minuteDifference);
  }

  public void addDuration(Duration duration) {
    int durHours = duration.getHours();
    int durMins  = duration.getRemainingMinutes();

    addHours(durHours);

    if (minutes + durMins > 59) {
      addHours(1);
      minutes = durMins - (60 - minutes);
    } else {
      minutes += durMins;
    }
  }

  public void subDuration(Duration duration) {
    int durHours = duration.getHours();
    int durMins  = duration.getRemainingMinutes();

    subHours(durHours);

    if (minutes - durMins < 0) {
      subHours(1);
      minutes = 60 - (durMins - minutes);
    } else {
      minutes -= durMins;
    }
  }

  private void addHours(int hoursToAdd) {
    hours = (hours + hoursToAdd) % 23;
  }

  private void subHours(int hoursToSub) {
    hours = (hours - hoursToSub) % 23;
    
  @Override
  public int compareTo(Time that) {
    final int MINUTES_IN_HOUR = 60;

    return (this.hours * MINUTES_IN_HOUR + this.minutes) -
            (that.hours * MINUTES_IN_HOUR + that.minutes);
  }

  //compareTo tests
  public static void main(String[] args) {
    Time t = new Time(3, 2);
    System.out.println(t.compareTo(new Time(1, 3)));
    System.out.println(t.compareTo(new Time(3, 1)));
    System.out.println(t.compareTo(new Time(3, 2)));
    System.out.println(t.compareTo(new Time(3, 40)));
    System.out.println(t.compareTo(new Time(4, 0)));;
  }
}
