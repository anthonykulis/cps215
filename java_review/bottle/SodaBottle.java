package bottle;

import cap.Cap;


public class SodaBottle extends Bottle {

  private String _type;
  public Cap cap;

  public SodaBottle(String type, double capacity){
    super(capacity);
    this._type = type;
    this.cap = new Cap();
    this.cap.attach();
  }

  public boolean pour(double amount){
    if(this.cap.isAttached()){
      return false;
    }
    return super.pour(amount);
  }

  public boolean addLiquid(double amount){
    if(this.cap.isAttached()){
      return false;
    }
    return super.addLiquid(amount);
  }

  public boolean fill(){
    if(this.cap.isAttached()){
      return false;
    }
    return super.fill();
  }

  public boolean empty(){
    if(this.cap.isAttached()){
      return false;
    }
    return super.empty();
  }

  public String getType(){
    return this._type;
  }


  public static void main(String args[]){
    System.out.println("Running tests on SodaBottle.java");
    SodaBottle sb = new SodaBottle("Coke", 20.0);

    // first things first, get type
    System.out.println("Testing constructor setting type of soda bottle");
    String type = sb.getType();
    if(!type.equals("Coke")){
      throw new RuntimeException("Tried to set type to Coke, didn't work");
    }

    System.out.println("Testing functionality while cap is on");

    // Test adding
    if(sb.fill()){
      throw new RuntimeException("I tried to fill with the cap on and it worked!");
    }
    if(sb.addLiquid(1.0)){
      throw new RuntimeException("I tried to add 1 oz with the cap on and it worked!");
    }

    // test pouring
    if(sb.empty()){
      throw new RuntimeException("I tried to empty it with the cap on and it worked!");
    }

    if(sb.pour(1)){
      throw new RuntimeException("I tried to pour with the cap on and it worked!");
    }

    System.out.println("testing functionality while cap is off");
    sb.cap.remove();

    if(!sb.addLiquid(1.0)){
      throw new RuntimeException("I tried to add 1oz with cap off and it failed!");
    }

    if(!sb.fill()){
      throw new RuntimeException("I tried to fill remaining 19oz with cap off and it failed");
    }

    if(!sb.pour(1.0)){
      throw new RuntimeException("I tried to pour 1oz with cap off and it failed");
    }

    if(!sb.empty()){
      throw new RuntimeException("I tried to empty with the cap off and it failed");
    }

    System.out.println("Looks good. Exiting");
  }
}
