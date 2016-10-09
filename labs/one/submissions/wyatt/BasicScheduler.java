package labs.one;

import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;
import java.io.FileNotFoundException;
import java.io.IOException;

//Author: Wyatt Melton

public class BasicScheduler {

   private NodeQueue<Job> _highPriQ = new NodeQueue<>();
   private NodeQueue<Job> _medPriQ = new NodeQueue<>();
   private NodeQueue<Job> _lowPriQ = new NodeQueue<>();
   private int _ticker1 = 0;
   private int _ticker2 = 0;

   public void doWork()  {

     /*
       No need to peek then shift.
     */

      Job j = (Job)this._highPriQ.peek();
      j.doWork();
      if(j.isDone()) {
        /*
          This print should be in main,
          otherwise if I used your class, I
          would get your print statement
          -1
        */
         System.out.println(j);
         this._highPriQ.shift();
      }
   }
   public void prioritizeJobs(NodeQueue nodeQueue) {


      Job j = (Job)nodeQueue.shift();
      while(j != null) {
         switch(j.getName()) {
            case "a":
               this._highPriQ.push(j);
               break;
            case "b":
               this._medPriQ.push(j);
               break;
            case "c":
               this._lowPriQ.push(j);
               break;
         }
         j = (Job)nodeQueue.shift();
      }
   }


   public void promoteJobs() {
      Job j;
      if(this._highPriQ.isEmpty() && this._lowPriQ.peek() != null) {
        /*
          Superfluous peeks
        */

         j = this._medPriQ.peek();
         this._highPriQ.push(j);
         this._medPriQ.shift();
      }
      if(this._medPriQ.isEmpty() && this._lowPriQ.peek() != null) {
         j = this._lowPriQ.peek();
         this._medPriQ.push(j);
         this._lowPriQ.shift();
      }
   }



   public void promoteWaitingJobs()  {
      Job j;

      /*
        You promote on 4, not 3

        -2
      */
      if(this._ticker1 % 3 == 0 && this._medPriQ.peek() != null) {
         j = this._medPriQ.peek();
         this._highPriQ.push(j);
         this._medPriQ.shift();
         this._ticker1 = 0;
      }

      /*
        You promote on 6, not 5

        -2
      */
      if(this._ticker2 % 5 == 0 && this._lowPriQ.peek() != null) {
         j = this._lowPriQ.peek();
         this._medPriQ.push(j);
         this._lowPriQ.shift();
         this._ticker2 = 0;
      }
   }

   public static void main(String[] args) {

      try {
         JobReader _jobReader = new JobReader("labs/one/data/Jobs.txt");
         BasicScheduler scheduler = new BasicScheduler();

         NodeQueue nq = _jobReader.getNextGroupOfJobs();
         scheduler.prioritizeJobs(nq);


         while(!scheduler._highPriQ.isEmpty() || !scheduler._medPriQ.isEmpty() || !scheduler._lowPriQ.isEmpty()) {

            scheduler.doWork();

            /*
              You are possibly double promoting.
              If I promote from med to high,
              then look at waiting, I could be
              promoting one who has waited 3 and
              on that exact tick, have high empty.
              Hence promoted. Then the following
              job will be promoted from med to high
              when waiting 0.

              -8
            */
            scheduler.promoteJobs();
            scheduler.promoteWaitingJobs();

            /*
              You always get jobs even when there
              are no more to be had. You should have only
              checked this when available.
            */
            nq = _jobReader.getNextGroupOfJobs();
            if(nq.peek() != null) {
               scheduler.prioritizeJobs(nq);
            }

            scheduler._ticker1++;
            scheduler._ticker2++;

         }
      }
      catch(FileNotFoundException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
