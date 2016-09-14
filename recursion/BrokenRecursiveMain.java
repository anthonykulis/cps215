public class BrokenRecursiveMain {
  public static void main(String[] args){

    int num = 0;
    if(args.length > 0){
      num = Integer.valueOf(args[0]);
    }
    System.out.println(num);
    num++;
    String[] temp = {num+""};

    // start over
    BrokenRecursiveMain.main(temp);

  }
}
