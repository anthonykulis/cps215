public class RecursiveMain {
  public static void main(String[] args){

    if(args.length != 0){

      // print our first arg
      System.out.print(args[0] + " ");

      // reduce our args - our stoping condition
      String[] temp = new String[args.length - 1];
      System.arraycopy(args, 1, temp, 0, args.length - 1);

      // start over
      RecursiveMain.main(temp);

    } else {
      System.out.println();
    }
  }
}
