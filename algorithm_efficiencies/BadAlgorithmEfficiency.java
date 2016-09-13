public class BadAlgorithmEfficiency {
  public static void main(String[] args){

    // our low number to high number
    final int START = 1;
    final int END = 10;

    long sum = 0;

    // our start and end times
    long startTime, endTime;

    // start the clock
    startTime = System.nanoTime();

    for(int i = START; i <= END; i++){
      for(int j = 1; j <= i; j++){
        sum++;
      }
    }

    endTime = System.nanoTime();

    System.out.println("The sum of " + START + " to " + END + " is " + sum + " and took " + (endTime-startTime) + " nanoseconds");
  }
}
