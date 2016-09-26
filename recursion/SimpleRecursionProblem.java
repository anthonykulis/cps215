public class SimpleRecursionProblem {

  public long sum1to(long n){
    long sum = 1;
    if(n > 1) sum = this.sum1to(n - 1) + n;
    return sum;
  }

  public static void main(String[] args){
    SimpleRecursionProblem srp = new SimpleRecursionProblem();
    long end = 1;
    if(args.length > 0) end = Long.valueOf(args[0]);
    System.out.println("Sum of 1 to " + end + " is " + srp.sum1to(end));
  }
}
