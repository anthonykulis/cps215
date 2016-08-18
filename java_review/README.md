# Assignment
This is an in-class review. Of course if you do not complete it today in class, please try to knock it out at home over the weekend. The source is available now. Use it as a guide if needed, but try to do this on your own. Use google if you need to remember how to do something.
## Goal
Build a Soda Bottle class.
This class *is-a* Bottle.
This class *has-a* Cap.
This means you need a super class named **Bottle** and a composite class named **Cap**.
Of course they should be packaged in *bottle* and *cap*, respectively.
## Class specs
In cap/Cap.java
```
  public class Cap {
    private boolean _attached;
    public Cap();
    public Cap attach();
    public Cap remove();
    public boolean isAttached();
  }
```

In bottle/Bottle.java
```
  public class Bottle {
    private double _capacity;
    private double _current_volume;
    public Bottle(double capacity);
    public boolean addLiquid(double amount);
    public boolean fill();
    public boolean pour();
    public double getCurrentVolume();
    public double getCapacity();
    public double empty();
  }
```
In bottle/SodaBottle.java
```
  public class SodaBottle extends Bottle {
    private String _type;
    public Cap cap;
    public SodaBottle(String type, double capacity);
    public boolean pour(double amount);
    public boolean addLiquid(double amount);
    public boolean fill();
    public boolean empty();
    public String getType();
  }
```

## Testing
Testing is always a good thing. If you have time in class to derive the tests, for each class create a **main** method to test each of your classes other methods. Use `throw new RuntimeException(String string)` if you get an unexpected return.
## Grading
This is an ungraded exercise.
## Due
There is no due date. This is for review.
