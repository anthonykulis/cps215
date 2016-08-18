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
In bottle/SodaBottle.java. Do you see the repeated methods in this class compared to bottle? You will have to manage those appropriately. Hint: they are overriden for a reason and it has to do with the cap. Realize the Bottle.java class has no idea about the Cap.java class, so we must handle it here. Realistically, we would handle it in Bottle.java, but this is an exercise!
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
## Optional
If you are more comfortable using a driver to test, that is ok. While its not the best approach in a real-world situation, this is also only a 200 level class. Feel free to run a driver instead. I included one in the source path, but it is very limited since I wrote unit tests for each class.
## Grading
This is an ungraded exercise.
## Due Date
There is no due date. This is for review.
