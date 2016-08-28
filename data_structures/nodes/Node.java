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

  public Node unlink(){
    this._next = null;
  }

  public T getData(){
    return this._data;
  }

  public void setData(T item){
    this._data = item;
  }

  public static void main(String args[]){
    Node<String> n = new Node<>();
  }
}
