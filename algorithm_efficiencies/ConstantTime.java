public class ConstantTime {
  public static void main(String[] args){

    final int NUM_TRIALS = 10000;
    long[] trials = new long[NUM_TRIALS];

    int sum = 0;
    int sumUp = (int)(Math.random() * 10000000) + 1;

    for(int trial = 0; trial < NUM_TRIALS; trial++){
      sum = 0;

      // our start and end times
      long startTime, endTime;

      // start the clock
      startTime = System.nanoTime();

      sum = sumUp * (sumUp + 1) / 2;

      endTime = System.nanoTime();

      trials[trial] = endTime - startTime;


    }

    long total = 0;
    for(int i = 0; i < NUM_TRIALS; i++){ total += trials[i]; }
    long avg = total/NUM_TRIALS;
    System.out.println("Computing the sum of " + NUM_TRIALS + " random numbers up to 10,000,000 took an average of " + avg + " nanoseconds.");

  }
}
