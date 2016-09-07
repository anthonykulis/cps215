package labs.one.util;

import labs.one.data_structures.queues.NodeQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class JobReader {

  private BufferedReader _br;
  private NodeQueue<Job> _nextGroup = new NodeQueue<>();

  /*
    On initialize, this will preload a job. If the file name is not found,
    it will throw a FileNotFoundException. If there is an issue reading, it
    will throw a IOException.
  */
  public JobReader(String fileName) throws FileNotFoundException {
    try {
      this._br = new BufferedReader(new FileReader(fileName));
    } catch(FileNotFoundException e){
      throw new FileNotFoundException();
    }

  }

  /*
    Calling this method allows for the queue of the jobs to
    be returned. Each queue is immutable! Each queue is consumed!
    If you call this again with items in your queue, it will just
    return that current queue. Once all the jobs are read from file,
    this will return null.
  */
  public NodeQueue getNextGroupOfJobs() throws IOException {
    if(!this._nextGroup.isEmpty()){
      return this._nextGroup;
    }

    try {
      if(this.readLine() && this._nextGroup.isEmpty()){
        return null;
      }

      return this._nextGroup;

    } catch (IOException e){
      throw new IOException();
    }
  }


  private boolean readLine() throws IOException {

    if(!this._nextGroup.isEmpty()){
      return false;
    }

    NodeQueue<Job> temp = new NodeQueue<>();
    String line;

    try {
      line = this._br.readLine();
    } catch(IOException e){
      throw new IOException();
    }

    if(line == null){ return false; }

    String[] jobs = line.split(",");

    for(int i = 0; i < jobs.length; i++){
      String[] job = jobs[i].split(":");

      // Im not checking to see if file syntax is bad.
      // If the length is 0, it means I purposely left it blank.
      // if the length is not 2, it means there is a typo and I dont care.
      if(job.length != 2){ continue; }
      Job j = new Job(job[0], Integer.valueOf(job[1]));
      temp.push(j);
    }

    this._nextGroup = temp;

    return true;
  }

  public static void main(String[] args){
    JobReader jr;
    try {
      jr = new JobReader("labs/one/data/Jobs.txt");
      NodeQueue<Job> nq = jr.getNextGroupOfJobs();
      while(nq != null && !nq.isEmpty()){
        Job j = nq.shift();
        System.out.println(j.toString());
        nq = jr.getNextGroupOfJobs();
      }
    } catch (IOException e){
      e.printStackTrace();
      System.exit(1);
    }
  }
}
