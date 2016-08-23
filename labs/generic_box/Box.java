public class Box<T> {
  private T _item;
  private String _description;

  public Box(){
    this._item = null;
    this._description = null;
  }

  public Box add(T item){
    this._item = item;
    return this;
  }

  public T remove(){
    T item = this._item;
    this._item = null;
    return item;
  }

  public Box setDescription(String description){
    this._description = description;
    return this;
  }

  public String getDescription(){
    return this._description;
  }

  public static void main(String args[]){
    Box b = new Box();
    b.setDescription("My awesome test box");

    System.out.println("Box description: " + b.getDescription());

    if(!b.getDescription().equals("My awesome test box")){
      throw new RuntimeException("Description doesnt match");
    }

    String item = "Pretty Item";
    b.add(item);
    String removedItem = (String)b.remove();

    System.out.println("removed item: " + item);

    if(!removedItem.equals("Pretty Item")){
      throw new RuntimeException("What I removed is not what I put in");
    }

    String removedAgain = (String)b.remove();
    if(removedAgain != null){
      throw new RuntimeException("Whoops, I removed something else");
    }

    System.out.println("Tests completed successully");
  }
}
