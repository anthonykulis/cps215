import bottle.SodaBottle;

public class Driver {

  public static void main(String args[]){
    SodaBottle sb = new SodaBottle("Fango", 40.0);
    boolean itWorked = sb.fill();
    System.out.println("I have the cap on and filled it? " + itWorked);
    sb.cap.remove();
    sb.fill();
    System.out.println("I removed the cap and filled. Now i have " + sb.getCurrentVolume() + " ounces");
    sb.pour(18);
    System.out.println("I just drank 18 ounces, so I have " + sb.getCurrentVolume() + " ounces left");
    sb.empty();
    System.out.println(sb.getType()  + " is terrible. So I poured it out and have " + sb.getCurrentVolume() + " left");
  }
}
