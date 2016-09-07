package labs.one.util;

public class Job {
  private int _timeToComplete;
  private String _name;
  private int _timeRemaining;

  public Job(String name, int timeToComplete){
    this._name = name;
    this._timeToComplete = this._timeRemaining = timeToComplete;
  }

  public String getName(){ return this._name; }

  public int getTimeLeft(){ return this._timeRemaining; }

  public void doWork(){
    if(this._timeToComplete == 0){ return; }
    this._timeRemaining--;
  }

  public String toString(){
    return "Job: " + this._name + "\nTime to complete: " + this._timeToComplete + "\n";
  }
}
