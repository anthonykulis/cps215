// 95/100

/*
  Hey, I appreciate the declaritive code, but its like coffee,
  drinking it in moderation keeps you going, drinking a days worth
  in 5 minutes makes you shake. Don't be too declartive. 
*/

// Lab 1 - Revision 1
// Mark Kiel

package labs.one;

import java.io.IOException;
import labs.one.data_structures.queues.NodeQueue;
import labs.one.util.Job;
import labs.one.util.JobReader;

public class BasicScheduler{
    private final int _lowPriorityTickPromotion = 5;
    private final int _mediumPriorityTickPromotion = 3;
    private int _lowPriorityTickCount, _mediumPriorityTickCount;    // Keeps track of tick count for promotion purposes
    private boolean _incomingJobsAvailable;
    private JobReader _reader;
    private NodeQueue<Job> _incomingQueue;
    private NodeQueue<Job> _highPriorityQueue, _mediumPriorityQueue, _lowPriorityQueue;

    public BasicScheduler(String jobList){
        this._incomingJobsAvailable = true;
        this._lowPriorityTickCount = _mediumPriorityTickCount = 0;
        this._highPriorityQueue = new NodeQueue<>();
        this._mediumPriorityQueue = new NodeQueue<>();
        this._lowPriorityQueue = new NodeQueue<>();

        try{
            _reader = new JobReader(jobList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void start(){
        do{
            this.onTick();
        }while(this.jobsRemain());
    }

    /* Dont need this method. Its just a wrapper */
    private void printReport(Job job){
        System.out.println(job.toString());
    }

    private void onTick(){
        if(this._incomingJobsAvailable){
            this.queueJobs();
        }
        this.promote();
        this.work();
    }

    private boolean jobsRemain(){
        return (!_highPriorityQueue.isEmpty() || !_mediumPriorityQueue.isEmpty() ||
                 !_lowPriorityQueue.isEmpty());
    }

    private void queueJobs(){
        try{
            this._incomingQueue = this._reader.getNextGroupOfJobs();
            if(this._incomingQueue == null){
                this._incomingJobsAvailable = false;
                return;
            } }catch (IOException e){
            e.printStackTrace();
        }

        while(!this._incomingQueue.isEmpty()){
            Job job = this._incomingQueue.shift();
            String name = job.getName();

            switch (name) {
                case "a":
                    this._highPriorityQueue.push(job);
                    break;
                case "b":
                    this._mediumPriorityQueue.push(job);
                    break;
                case "c":
                    this._lowPriorityQueue.push(job);
                    break;
                default:
                    break;
            }
        }
        this._incomingJobsAvailable = true;
    }

    private void promote(){
        // promote front node in medium queue if high queue is empty or tick count is 3
        if((this._mediumPriorityTickCount == _mediumPriorityTickPromotion) || (this._highPriorityQueue.isEmpty() && this._mediumPriorityTickCount > 0)){

            /*
              You dont check for a null return on the shift. Med priority
              may be empty

              -2
            */
            this._highPriorityQueue.push(this._mediumPriorityQueue.shift());
            this._mediumPriorityTickCount = 0;
        }

        // promote front node in low queue if medium queue is empty or tick count is 5
        if((this._lowPriorityTickCount == _lowPriorityTickPromotion) || (this._mediumPriorityQueue.isEmpty() && this._lowPriorityTickCount > 0)){
            /*
              You dont check for a null return on the shift. Low priority
              may be empty

              -2
            */
            this._mediumPriorityQueue.push(this._lowPriorityQueue.shift());
            this._lowPriorityTickCount = 0;
        }
    }

    private void work(){
        if(!this._highPriorityQueue.isEmpty()){
            Job job = this._highPriorityQueue.peek();
            job.doWork();
            if(job.isDone()){
              /*
                You print irregardless of who may be using this class.
                This means I am stuck with your print.

                -1
              */
               this.printReport(this._highPriorityQueue.shift());
            }
        }

        if(!this._lowPriorityQueue.isEmpty()) this._lowPriorityTickCount++;
        if(!this._mediumPriorityQueue.isEmpty()) this._mediumPriorityTickCount++;
    }

    public static void main(String[] args){
        BasicScheduler scheduler = new BasicScheduler("labs/one/data/Jobs.txt");
        scheduler.start();
    }


}
