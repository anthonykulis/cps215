package bottle;

public class Bottle {
  private double _capacity;
  private double _current_volume;

  public Bottle(double capacity){
    this._capacity = capacity;
    this._current_volume = 0.0;
  }

  public boolean addLiquid(double amount){
    if(!this._canIAddThisMuch(amount)){
      return false;
    }

    this._current_volume += amount;
    return true;
  }

  public boolean fill(){
    double amountToFill = this._capacity - this._current_volume;
    return this.addLiquid(amountToFill);
  }

  public boolean pour(double amount){
    if(!this._canIRemoveThisMuch(amount)){
      return false;
    }

    this._current_volume -= amount;
    return true;
  }

  public double getCurrentVolume(){
    return this._current_volume;
  }

  public double getCapacity(){
    return this._capacity;
  }

  public boolean empty(){
    this._current_volume = 0.0;
    return true;
  }

  private boolean _doesAmountFitBounds(double desired){
    return (desired >= 0.0 && desired <= this._capacity);
  }

  private boolean _canIAddThisMuch(double desired){
    return (this._doesAmountFitBounds(desired) && desired + this._current_volume <= this._capacity);
  }

  private boolean _canIRemoveThisMuch(double desired){
    return (this._doesAmountFitBounds(desired) && this._current_volume - desired >= 0.0);
  }


  // tests
  public static void main(String args[]){
    System.out.println("Running tests on Bottle.java");
    double capacity = 20.00;
    Bottle b = new Bottle(capacity);

    // bottle over and under fill test
    System.out.println("Testing filling over capacity - expext output to be false");
    boolean ret = b.addLiquid(21.0);
    if(ret){
      throw new RuntimeException("Tried to over fill bottle, expected false, got true");
    }
    ret = b.addLiquid(19.0);
    if(!ret){
      throw new RuntimeException("Tried to put a proper amount of liquid in bottle, expexted true, got false");
    }

    // bottle empty() and getCurrentVolume() test
    System.out.println("Testing to empty");
    b.empty();
    if(b.getCurrentVolume() != 0.0){
      throw new RuntimeException("Tried to empty bottle but failed!");
    }

    // Fill test
    System.out.println("Testing filling to max capacity");
    b.fill();
    if(b.getCurrentVolume() != capacity){
      throw new RuntimeException("Tried to fill to capacity but failed");
    }

    //pour test
    System.out.println("Testing pouring amounts");
    System.out.println("-- pour 1oz");
    ret = b.pour(1.0);
    if(!ret){
      throw new RuntimeException("Tried to pour 1oz and failed");
    }

    System.out.println("-- pour 20z when there is only 19oz");
    ret = b.pour(20.0);
    if(ret){
      throw new RuntimeException("Tried to pour 20oz when there was only 19, it passed when it should have failed");
    }

    System.out.println("Looks good. Exiting");
  }
}
