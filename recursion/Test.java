public class Test {
  static int sum1To(int n){
    if(n <= 1) return 1;
    return n + sum1To(n - 1);
  }

  static String findBiggest(String[] a, int p, String b){
    if(p >= a.length) return b;
    if(a[p].compareTo(b) > 0) b = a[p];
    return findBiggest(a, p + 1, b);
  }

  static int fib(int n){
    if(n < 2) return 1;
    return fib(n-1) + fib(n-2);
  }

  public static void main(String[] args){
    System.out.println(Test.sum1To(5));
    String[] a = {"aardvark", "camel", "zebra", "apple"};
    System.out.println(Test.findBiggest(a, 0, ""));
    System.out.println(Test.fib(6));
  }
}
