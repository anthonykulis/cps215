package cap;

public class Cap {
  private boolean _attached;

  public Cap(){
    this._attached = false;
  }

  public Cap attach(){
    this._attached = true;
    return this;
  }

  public Cap remove(){
    this._attached = false;
    return this;
  }

  public boolean isAttached(){
    return this._attached;
  }

  public static void main(String args[]){
    System.out.println("Running Cap tests");

    Cap c = new Cap();
    System.out.println("Remove cap test");
    c.remove();
    if(c.isAttached()){
      throw new RuntimeException("Cap is attached when I removed it!");
    }

    System.out.println("Attach cap test");
    c.attach();
    if(!c.isAttached()){
      throw new RuntimeException("Cap is not attached when I attached it!");
    }

    System.out.println("Looks good. Exiting");
  }
}
