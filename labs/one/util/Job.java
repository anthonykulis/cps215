package labs.one.util;

public class Job {
  private int _timeToComplete;
  private String _name;

  public Job(String name, int timeToComplete){
    this._name = name;
    this._timeToComplete = timeToComplete;
  }

  public String getName(){ return this._name; }

  public int getTimeLeft(){ return this._timeToComplete; }

  public void doWork(){
    if(this._timeToComplete == 0){ return; }
    this._timeToComplete--;
  }

  public String toString(){
    return "Job: " + this._name + "\nTime to complete: " + this._timeToComplete + "\n";
  }
}
