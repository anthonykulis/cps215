package data_structures.nodes;

public class DoublyLinkedNode<T> extends Node<T>{

  private DoublyLinkedNode _previous;
  private T _data;

  public DoublyLinkedNode(){
    super();
    this._previous = null;
  }

  public DoublyLinkedNode(T item, DoublyLinkedNode next, DoublyLinkedNode previous){
    super(item, next);
    this.linkPrevious(previous);
  }

  public DoublyLinkedNode next(){
    // UGH! Generic class extended, need to manually override what it wants to do
    // Is this correct approach? I'm not 100% on this.
    return (DoublyLinkedNode)super.next();
  }

  public DoublyLinkedNode previous(){
    return this._previous;
  }

  public void unlinkPrevious(){
    this._previous = null;
  }

  public void linkPrevious(DoublyLinkedNode previous){
    this._previous = previous;
  }

  public static void main(String[] args){
    String[] numbers = {"One", "Two", "Three"};
    DoublyLinkedNode<String> three = new DoublyLinkedNode<>();
    three.setData(numbers[2]);
    DoublyLinkedNode<String> one = new DoublyLinkedNode<>();
    one.setData(numbers[0]);
    DoublyLinkedNode<String> two = new DoublyLinkedNode<>(numbers[1], three, one);
    one.link(two);
    three.linkPrevious(two);

    two = three.previous();
    if(!two.getData().equals("Two")){
      throw new RuntimeException("I requested two's data, expected `Two`, got " + two.getData());
    }

    one = two.previous();
    if(!one.getData().equals("One")){
      throw new RuntimeException("I requested one's data, expected `One`, got " + one.getData());
    }

    two.unlinkPrevious();
    if(two.previous() != null){
      throw new RuntimeException("I unlinked two from one and expected null, got " + (String)two.previous().getData());
    }

    System.out.println("Woot. DoublyLinkedNode works");
  }

}
