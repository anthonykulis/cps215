// Score 78/100

/*
Not declarative
priorizing jobs and terrible variable names

-1
*/

package labs.one;

import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BasicScheduler {

   public void doQueueWork(NodeQueue queue, NodeQueue queue1){
      Job j = (Job)queue.peek();

      /*
        You do not check to see if this is null
        It is possible for the high priority queue to be empty
        and this called, hence crashing your class.

        While this wont occur in your driver, if I was to import this
        class it would crash
        -1
      */
      j.doWork();
      if(j.isDone()){

         /*
            If I imported this, do I want to have it print out
            when the job is done? This should have been called
            in the main method.

            -1
         */
         System.out.println(j.toString() + "status: completed\n");
         queue.shift();
      }
   }

   public void promoteJob(NodeQueue queue, NodeQueue queue1){

     /*
      This may have been null. You need a check in this method. You
      also collected the reference to the same object 2x.

      -1
     */
      Job j = (Job)queue1.peek();
      queue1.shift();
      queue.push(j);
   }

   public int promoteMedium(NodeQueue queue, NodeQueue queue1, int ticker){

      if(ticker%3==0){
         promoteJob(queue, queue1);
         return ticker = 0;}
      return ticker;
   }

   public int promoteLower(NodeQueue queue, NodeQueue queue1, int ticker){

      if(ticker%5==0){
         promoteJob(queue, queue1);
         return ticker = 0;}
      return ticker;
   }

   public static void main(String[] args){

     /*
     Class associated variables should be defined in the class. Since
     this class is about 3 queues, these need to be scoped higher.

     This follows for the tick counters

     -3
     */
      NodeQueue<Job> queueA = new NodeQueue();
      NodeQueue<Job> queueB = new NodeQueue();
      NodeQueue<Job> queueC = new NodeQueue();
      NodeQueue<Job> jobQueue;


      int ticks = 0;
      int ticks5 = 0;


      try{
         BasicScheduler scheduler = new BasicScheduler();
         JobReader reader = new JobReader("labs/one/data/Jobs.txt");
         jobQueue = reader.getNextGroupOfJobs();


         /*
           This entire while block is confusing and hard to read.
           We discussed the steps to be found in this loop and its conditions
           to run. While it may "work", it is still incorrect due to the lack of
           conciseness.

           -4

         */
         while((!queueA.isEmpty() || !queueB.isEmpty() || !queueC.isEmpty()) || !jobQueue.isEmpty()){

            // why?
            Job j = new Job("",0);

            if(!jobQueue.isEmpty())
               j = jobQueue.shift();
            if(j.getName().equals("a")){
              queueA.push(j); }
            else if(j.getName().equals("b")){
               queueB.push(j);}
            else if(j.getName().equals("c")){
               queueC.push(j);}


            if(jobQueue.isEmpty() && (reader.getNextGroupOfJobs() != null)){

               /*
                You continue calling this even if the system told
                you it has nothing left. This repeats in multiple places.

               -5

               */
               jobQueue = reader.getNextGroupOfJobs();

               ticks++;
               ticks5++;


               if(!queueA.isEmpty()){
                  scheduler.doQueueWork(queueA, queueB);

                  /*
                    Incorrect. Your promote both levels if medium priority
                    queue is empty under the initial conditions

                    -4
                  */
                  if(!queueB.isEmpty()){
                     ticks = scheduler.promoteMedium(queueA, queueB, ticks);
                     ticks5 = scheduler.promoteLower(queueB, queueC, ticks5);}

               }
            }


            if(queueA.isEmpty() && ticks>1 && !queueB.isEmpty()){
                  scheduler.promoteJob(queueA, queueB);}
            else if(queueB.isEmpty() && ticks>1 && !queueC.isEmpty()){
                  scheduler.promoteJob(queueB, queueC);}
         }
      }


      catch(FileNotFoundException e){
         System.out.println("file not found.");
      }
      catch(IOException e){
         System.out.println("can't read");
      }

      /*
      Out of spec

      -5
      */
      catch(NullPointerException e){
         System.out.println("points to nothing");
      }



   }
}
