/* being declarative - 1pt */

/* package not changed - 1pt*/
package labs.one;

/* importing the two IO exceptions - 2pts */
import java.io.FileNotFoundException;
import java.io.IOException;

import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;

public class BasicScheduler {

  final int LOW_WAIT = 5;
  final int MED_WAIT = 3;

  /* variables 7 points */
  int lowTick, mediumTick;
  NodeQueue<Job> high, medium, low, nextGroup;
  JobReader jr;

  /* Total 9 pts*/
  public BasicScheduler(){

    /* starting your tickers at 0, 2pt */
    this.lowTick = this.mediumTick = 0;

    /* creating your three queues, 6pts */
    this.high = new NodeQueue<>();
    this.medium = new NodeQueue<>();
    this.low = new NodeQueue<>();

    try {
      /* reading the file 2pt */
      this.jr = new JobReader("labs/one/data/Jobs.txt");
    } catch (FileNotFoundException e){
      e.printStackTrace();
      System.exit(1);
    }
  }

  /* Total 15pts */
  boolean fillTheQueues(){
    try {

      /* get the next group - 2pt */
      this.nextGroup = this.jr.getNextGroupOfJobs();

      /* shifting through jobs - 5pts */
      Job j = this.nextGroup.shift();

      /* Stopping on no jobs left to fill - 5pts */
      if(j == null){
        return false;
      }

      // go through our provided queue and prioritize
      while(j != null){

        /* priortizing your jobs into seperate queues - 3pts */
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

  /* Total 12 points */
  Job performWork(){

    /* peeking at the only worker - 3pt */
    // only highest priorty does some work
    Job worker = this.high.peek();

    /* checking the worker is not null - 1pt*/
    // empty?
    if(worker ==  null){ return null; }

    /* calling doWork - 2pts */
    worker.doWork();

    /* checking to see if the worker is done after doing work - 2pts */
    // if the worker finishes, remove from the queue and print its completion
    if(worker.isDone()){
      /* removing a completed worker - 2pts */
      return this.high.shift();
    }

    return null;
  }

  /* Total 5 points */
  boolean promoteLowToEmptyMedium(){

    /* checking higher priority queue is empty before shifting - 1pt */
    if(this.medium.isEmpty()){

      /* pushing into the medium priority queue - 4pt */
      this._promote(this.low, this.medium);

      /* reset the low tick */
      this.lowTick = 0;

      return true;

    }

    return false;
  }

  /* Total 6 points */
  boolean promoteMediumToEmptyHigh(){

    /* checking higher priority queue is empty before shifting - 1pt */
    if(this.high.isEmpty()){

      /* pushing into the high priority queue - 4pt */
      this._promote(this.medium, this.high);

      /* reset the tick */
      this.mediumTick = 0;

      return true;

    }
    return false;
  }

  /* Total 8 points */
  void promoteMediumToHighOnWait(){

    /* modulating (or reseting) ticker so it only works every 3 - 2pts */
    if(this.mediumTick == this.MED_WAIT){
      this._promote(this.medium, this.high);
      this.mediumTick = 0;
    } else {
      /* incrementing your ticker - 1pt */
      this.mediumTick++;
    }
  }

  /* Total 8 points */
  void promoteLowToMediumOnWait(){

    /* modulating (or reseting) ticker so it only works every 5 - 2pts */
    if(this.lowTick == LOW_WAIT){
      this._promote(this.low, this.high);
      this.lowTick = 0;
    } else {
      /* incrementing your ticker - 1pt */
      this.lowTick++;
    }
  }

  private void _promote(NodeQueue from, NodeQueue to){

    Job promoted = (Job)from.shift();

    /* checking the shift didn't result in null and pushing - 3pt */
    if(promoted != null) to.push(promoted);
  }

  public static void main(String[] args){

    // first, give ourselves an instance
    BasicScheduler bs = new BasicScheduler();

    // reuse job
    Job j;

    // first fill the queues
    boolean jobsLeftToSchedule = bs.fillTheQueues();

    // keep going until all queues are empty
    while(!(bs.high.isEmpty() && bs.medium.isEmpty() && bs.low.isEmpty())){

      /*
        Following order 4 points each
      */
      j = bs.performWork();

      if(j != null){
        /* printing out the toString method, not something else - 2pts */
        System.out.println("Completed: " + j);
      }

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
