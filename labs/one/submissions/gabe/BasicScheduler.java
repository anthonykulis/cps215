// 69/100

package labs.one;

import java.io.IOException;
import java.io.FileNotFoundException;
import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;

public class BasicScheduler{
   NodeQueue<Job> highPriorityQueue;
   NodeQueue<Job> medPriorityQueue;
   NodeQueue<Job> lowPriorityQueue;
   NodeQueue<Job> nq;
   JobReader jr;
   int tick;

   public BasicScheduler(){
      highPriorityQueue = new NodeQueue<>();
      medPriorityQueue = new NodeQueue<>();
      lowPriorityQueue = new NodeQueue<>();
      nq = new NodeQueue<>();

      /*
       Need two ticks. One for low priority and
       one for medium priority
       -1
      */
      tick = 0;
   }

   public boolean doWorkOnHighPriorityQueue(){
      Job currentJob = highPriorityQueue.peek();
      if(highPriorityQueue.peek() == null){
         return false;
      }

      /*
        Wrong order. This must be called after doing work.
        No need to keep in queue after work is done, even for
        one tick.

        -2
      */
      if(currentJob.isDone()){
         highPriorityQueue.shift();

         /*
            If I imported this, do I want to have it print out
            when the job is done? This should have been called
            in the main method.

            -1
         */
         System.out.println(currentJob.toString());
      }
      else{
         currentJob.doWork();
      }
      return true;

   }

   public void promoteToHighPriorityQueue(){

    /*
    Not checking null here. If this class was imported,
    we could suffer null positions in queue.

    -1
    */
      highPriorityQueue.push(medPriorityQueue.shift());
      tick = 0;
   }

   public void promoteToMediumPriorityQueue(){

     /*
     Not checking null here. If this class was imported,
     we could suffer null positions in queue.

     -1
     */

      medPriorityQueue.push(lowPriorityQueue.shift());
      tick = 0;
   }

   public void addJobsToQueues(){
      while(!nq.isEmpty()){

         Job currentJob = nq.peek();
         String jobName = currentJob.getName();

         if(jobName.equals("a")){
            highPriorityQueue.push(nq.shift());
         }
         else if(jobName.equals("b")){
            medPriorityQueue.push(nq.shift());
         }
         else if(jobName.equals("c")){
            lowPriorityQueue.push(nq.shift());
         }

      }

      nq.clear();


      /*
        You are reading all the jobs at once. This is
        incorrect. you were to only read a group of jobs
        once per tick.

        -5
      */
      try{
         nq = jr.getNextGroupOfJobs();
      }
      catch(IOException e){
         e.printStackTrace();
         System.exit(1);
      }

   }

   public static void main(String[] args){
      BasicScheduler bs = new BasicScheduler();

      try{
         bs.jr = new JobReader("labs/one/data/Jobs.txt");
         bs.nq = bs.jr.getNextGroupOfJobs();
      }
      catch(IOException e){
         e.printStackTrace();
         System.exit(1);
      }

      bs.addJobsToQueues();
      boolean canDoWork = true;

      /*
        You must continue until all queues are empty. The
        high priority queue can become empty before another and
        hence cause your code to stop executing.

        -8
      */
      while(canDoWork == true){

        /*
          Your promotions can lead to incorrect promotions.
          If the tick is 2, and you promote to high priority,
          and another job comes, you promote it after 1.

          You repeat this for again

          -8
        */
         if(bs.highPriorityQueue.isEmpty() || bs.tick == 3){
            bs.promoteToHighPriorityQueue();
         }
         if(bs.medPriorityQueue.isEmpty() || bs.tick == 5){
            bs.promoteToMediumPriorityQueue();
         }

         canDoWork = bs.doWorkOnHighPriorityQueue();
         bs.addJobsToQueues();

         /*
            Your ticks are incorrect. If we tick to 3, we promote,
            but to get to 5, we must promote all of medium first.

            -4
         */
         bs.tick++;

      }


   }

}
