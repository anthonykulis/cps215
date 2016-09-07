package labs.one;

import java.io.FileNotFoundException;
import java.io.IOException;

import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;

public class BasicScheduler {

  int lowTick, mediumTick;
  NodeQueue<Job> high, medium, low, nextGroup;
  JobReader jr;

  public BasicScheduler(){
    this.lowTick = this.mediumTick = 1;
    this.high = new NodeQueue<>();
    this.medium = new NodeQueue<>();
    this.low = new NodeQueue<>();
    try {
      this.jr = new JobReader("labs/one/data/Jobs.txt");
    } catch (FileNotFoundException e){
      e.printStackTrace();
      System.exit(1);
    }
  }

  boolean fillTheQueues(){
    try {

      // get the next group
      this.nextGroup = this.jr.getNextGroupOfJobs();
      Job j = this.nextGroup.shift();

      // no jobs left to get
      if(j == null){
        return false;
      }

      // go through our provided queue and prioritize
      while(j != null){
        switch(j.getName().charAt(0)){
          case 'a':
            this.high.push(j);
            break;
          case 'b':
            this.medium.push(j);
            break;
          case 'c':
            this.low.push(j);
            break;
          default: //no op
        }

        // get the next job in the group
        j = this.nextGroup.shift();
      }
    } catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }

    return true;
  }

  void performWork(){

    // only highest priorty does some work
    Job worker = this.high.peek();

    // empty?
    if(worker ==  null){ return; }

    worker.doWork();

    // if the worker finishes, remove from the queue and print its completion
    if(worker.isDone()){
      this.high.shift();
      System.out.println("Completed: " + worker.toString());
    }
  }


  boolean promoteLowToEmptyMedium(){

    if(this.medium.isEmpty()){
      Job j = this.low.shift();
      if(j != null){
        this.medium.push(j);
        return true;
      }
    }

    return false;
  }

  boolean promoteMediumToEmptyHigh(){
    if(this.high.isEmpty()){
      Job j = this.medium.shift();
      if(j != null){
        this.high.push(j);
        return true;
      }
    }
    return false;
  }

  void promoteMediumToHighOnWait(){
    if(this.mediumTick % 3 == 0){
      Job j = this.medium.shift();
      if(j != null){ this.high.push(j); }
    }
    this.mediumTick++;
  }

  void promoteLowToMediumOnWait(){
    if(this.lowTick % 5 == 0){
      Job j = this.low.shift();
      if(j != null){ this.medium.push(j); }
    }
    this.lowTick++;
  }

  public static void main(String[] args){

    // first, give ourselves an instance
    BasicScheduler bs = new BasicScheduler();

    // first fill the queues
    boolean jobsLeftToSchedule = bs.fillTheQueues();

    // keep going until all queues are empty
    while(!(bs.high.isEmpty() && bs.medium.isEmpty() && bs.low.isEmpty())){

      bs.performWork();

      // order matters - by rules, I can only promote once per tick.
      if(!bs.promoteMediumToEmptyHigh()){
        // I can only promote for waiting if the above node isn't empty
        bs.promoteMediumToHighOnWait();
      }

      // by doing low to medium second, we prevent low going to medium, then to high
      if(bs.promoteLowToEmptyMedium()){
        // i can onlhy promte for waiting if the above node isn't empty
        bs.promoteLowToMediumOnWait();
      }

      // only call to schedule jobs is jobs still exist to schedule
      if(jobsLeftToSchedule){
        jobsLeftToSchedule = bs.fillTheQueues();
      }
    }
  }

}
