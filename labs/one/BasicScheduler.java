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

  /* variables 7 points */
  int lowTick, mediumTick;
  NodeQueue<Job> high, medium, low, nextGroup;
  JobReader jr;

  /* Total 9 pts*/
  public BasicScheduler(){

    /* starting your tickers at 1, 2pt */
    this.lowTick = this.mediumTick = 1;

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
  void performWork(){

    /* peeking at the only worker - 3pt */
    // only highest priorty does some work
    Job worker = this.high.peek();

    /* checking the worker is not null - 1pt*/
    // empty?
    if(worker ==  null){ return; }

    /* calling doWork - 2pts */
    worker.doWork();

    /* checking to see if the worker is done after doing work - 2pts */
    // if the worker finishes, remove from the queue and print its completion
    if(worker.isDone()){

      /* removing a completed worker - 2pts */
      this.high.shift();

      /* printing out the toString method, not something else - 2pts */
      System.out.println("Completed: " + worker.toString());
    }
  }

  /* Total 6 points */
  boolean promoteLowToEmptyMedium(){

    /* checking higher priority queue is empty before shifting - 1pt */
    if(this.medium.isEmpty()){
      /* shifting the lower priority - 2pt */
      Job j = this.low.shift();

      /* checking the shift didn't result in null - 1pt */
      if(j != null){

        /* pushing into the medium priority queue - 2pt */
        this.medium.push(j);

        /* reset the low tick */
        this.lowTick = 0;

        return true;
      }
    }

    return false;
  }

  /* Total 6 points */
  boolean promoteMediumToEmptyHigh(){

    /* checking higher priority queue is empty before shifting - 1pt */
    if(this.high.isEmpty()){

      /* shifting the lower priority - 2pt */
      Job j = this.medium.shift();

      /* checking the shift didn't result in null - 1pt */
      if(j != null){

        /* pushing into the medium priority queue - 2pt */
        this.high.push(j);

        /* reset the tick */
        this.mediumTick = 0;

        return true;
      }
    }
    return false;
  }

  /* Total 8 points */
  void promoteMediumToHighOnWait(){

    /* modulating (or reseting) ticker so it only works every 3 - 2pts */
    if(this.mediumTick == 3){

      /* shifting out - 2pts */
      Job j = this.medium.shift();

      /* checking for null 1pt */
      if(j != null){

        /* pushing into higher - 2pts */
        this.high.push(j);
      }

    }

    /* incrementing your ticker - 1pt */
    this.mediumTick++;
  }

  /* Total 8 points */
  void promoteLowToMediumOnWait(){

    /* modulating (or reseting) ticker so it only works every 5 - 2pts */
    if(this.lowTick == 5){

      /* shifting out - 2pts */
      Job j = this.low.shift();

      /* checking for null 1pt */
      if(j != null){

        /* pushing into higher - 2pts */
        this.medium.push(j);
      }
    }

    /* incrementing your ticker - 1pt */
    this.lowTick++;
  }

  public static void main(String[] args){

    // first, give ourselves an instance
    BasicScheduler bs = new BasicScheduler();

    // first fill the queues
    boolean jobsLeftToSchedule = bs.fillTheQueues();

    // keep going until all queues are empty
    while(!(bs.high.isEmpty() && bs.medium.isEmpty() && bs.low.isEmpty())){

      /*
        Following order 4 points each
      */
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
