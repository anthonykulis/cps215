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

  public boolean isDone(){ return this._timeRemaining == 0; }

  public void doWork(){
    if(this.isDone()){ return; }
    this._timeRemaining--;
  }

  public String toString(){
    return "Job: " + this._name + "\nTime to complete: " + this._timeToComplete + "\n";
  }
}
