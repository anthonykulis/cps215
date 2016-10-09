// 86/100

package labs.one;

import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BasicScheduler {

   private NodeQueue<Job> _highPriQue = new NodeQueue<>();
   private NodeQueue<Job> _medPriQue = new NodeQueue<>();
   private NodeQueue<Job> _lowPriQue = new NodeQueue<>();

   /*
    Need two tickers
    -1
   */
   private int _ticker = 1;

   public void doWork()  {

      /*
        No need to peek then shift.
      */
      Job j = this._highPriQue.peek();
      j.doWork();
      if(j.isDone()) {

        /*
          This print should be in main,
          otherwise if I used your class, I
          would get your print statement
          -1
        */
         System.out.println(j);
         this._highPriQue.shift();
      }
   }
   public void prioritizeJobs(NodeQueue nodeQueue) {

      Job j = (Job)nodeQueue.shift();
      while(j != null) {
         switch(j.getName()) {
            case "a":
               this._highPriQue.push(j);
               break;
            case "b":
               this._medPriQue.push(j);
               break;
            case "c":
               this._lowPriQue.push(j);
               break;
         }
         j = (Job)nodeQueue.shift();
      }
   }


   public void promoteJobs() {
      Job j;
      if(this._highPriQue.isEmpty() && this._medPriQue.peek() != null) {

        /*
          Superfluous peeks
        */
         j = this._medPriQue.peek();
         this._highPriQue.push(j);
         this._medPriQue.shift();
      }

      if(this._medPriQue.isEmpty() && this._lowPriQue.peek() != null) {
         j = this._lowPriQue.peek();
         this._medPriQue.push(j);
         this._lowPriQue.shift();
      }
   }



   public void promoteWaitingJobs()  {
      Job j;
      if(this._ticker % 3 == 0 && this._medPriQue.peek() != null) {
         j = this._medPriQue.peek();
         this._highPriQue.push(j);
         this._medPriQue.shift();
      }
      if(this._ticker % 5 == 0 && this._lowPriQue.peek() != null) {
         j = this._lowPriQue.peek();
         this._medPriQue.push(j);
         this._lowPriQue.shift();
      }
   }

   public static void main(String[] args) {

      try {
         JobReader _jobReader = new JobReader("labs/one/data/Jobs.txt");
         BasicScheduler scheduler = new BasicScheduler();

         NodeQueue nq = _jobReader.getNextGroupOfJobs();
         scheduler.prioritizeJobs(nq);


         while(!scheduler._highPriQue.isEmpty() || !scheduler._medPriQue.isEmpty() || !scheduler._lowPriQue.isEmpty()) {

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
            scheduler.prioritizeJobs(nq);

            /*
              Because you only have one ticker,
              low priority jobs will only be promoted
              when there are no medium priority jobs to
              be promoted.

              -4
            */
            scheduler._ticker++;

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
