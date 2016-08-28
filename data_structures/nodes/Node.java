package data_structures.nodes;

public class Node<T>{

  private T _data;
  private Node _next;

  public Node(){
    this._data = null;
    this._next = null;
  }

  public Node(T item, Node next){
    this.setData(item);
    this.link(next);
  }

  public Node next(){
    return this._next;
  }

  public void link(Node next){
    this._next = next;
  }

  public void unlink(){
    this._next = null;
  }

  public T getData(){
    return this._data;
  }

  public void setData(T item){
    this._data = item;
  }

  public static void main(String args[]){
    String[] numbers = {"One", "Two"};
    Node<String> two = new Node<>();
    two.setData(numbers[1]);
    Node<String> one = new Node<>(numbers[0], two);

    if(one.getData() == null){
      throw new RuntimeException("I set data, yet I cannot get data from the node");
    }

    if(one.next() == null){
      throw new RuntimeException("I set the next node but get null on next()");
    }

    one.unlink();

    if(one.next() != null){
      throw new RuntimeException("I unlinked two from one, but was still able to call next on it");
    }

    System.out.println("Node tests success");

  }
}
