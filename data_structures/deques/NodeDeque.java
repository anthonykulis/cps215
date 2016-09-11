package data_structures.deques;

import data_structures.nodes.DoublyLinkedNode;

public class NodeDeque<T> implements DequeInterface<T> {

  private DoublyLinkedNode _front, _back;

  public NodeDeque(){
    this.clear();
  }

  /*
    two possibilities:
      1) deque is empty, so no linking
      2) deque is not empty, so need to create a new back side
         and link the previous back to the new back BOTH directions
  */
  public void push(T item){
    DoublyLinkedNode<T> n = new DoublyLinkedNode<>();
    n.setData(item);

    if(this.isEmpty()){
      this._front = n;
    } else {
      this._back.link(n);
      n.linkPrevious(this._back);
    }

    this._back = n;
  }

  /*
    When popping, need to unlink both nodes in BOTH directions
    But we could have only one in the queue, so becareful to check
  */
  public T pop(){

    if(this.isEmpty()){ return null; }

    // need last node and previous node
    DoublyLinkedNode<T> n = this._back;
    DoublyLinkedNode<T> back = n.previous();

    if(back != null){
      // unlink the new back from the current back
      back.unlink();

      // unlink the current back from the new back
      n.unlinkPrevious();
    }

    // set the new back - includes null
    this._back = back;

    // if back is null, then queue empty. cant use isEmpty bc we are
    // updating this._front.. have to be imperative
    if(this._back == null){
      this._front = null;
    }

    T data = (T)n.getData();
    n.clearData();

    // return the data
    return data;
  }

  /*
    Two possibilities:
      1. Deque is empty
      2. Deque is not empty

    Handle both
  */
  public void unshift(T item){
    DoublyLinkedNode<T> n = new DoublyLinkedNode<>();
    n.setData(item);

    if(this.isEmpty()){
      this._back = n;
    } else {
      n.link(this._front);
      this._front.linkPrevious(n);
    }

    this._front = n;
  }

  /*
    When shifting, as with popping, we need to unlink the two nodes,
    BOTH directions
  */
  public T shift(){
    if(this.isEmpty()){ return null; }

    DoublyLinkedNode<T> n = this._front;
    DoublyLinkedNode<T> front = n.next();

    // n is not the only node!
    if(front != null){

      // forget about the new front node
      n.unlink();

      // forget about the old front node
      front.unlinkPrevious();

    }

    this._front = front;

    // update back if the shift was on the only element known
    if(this._front == null){
      this._back = null;
    }

    // return our data
    T data = (T)n.getData();
    n.clearData();
    return data;
  }

  public T peekFirst(){
    if(this.isEmpty()){ return null; }
    return (T)this._front.getData();
  }

  public T peekLast(){
    if(this.isEmpty()){ return null; }
    return (T)this._back.getData();
  }

  public boolean isEmpty(){ return this._front == null; }

  public void clear(){ this._front = this._back = null; }

  public static void main(String[] args){
    NodeDeque<String> nd = new NodeDeque<>();

    // push
    nd.push("Middle");
    nd.push("Back");
    // unshift
    nd.unshift("Front");

    // do peeks
    String peek = (String)nd.peekFirst();
    if(!peek.equals("Front")){
      throw new RuntimeException("I peekFirst() and expected `Front`, got " + peek);
    }

    peek = (String)nd.peekLast();
    if(!peek.equals("Back")){
      throw new RuntimeException("I peekLast() and expected `Back`, got " + peek);
    }

    // if i pop, I should get back then front
    String back = (String)nd.pop();
    String middle = (String)nd.pop();
    String front = (String)nd.pop();

    if(!nd.isEmpty()){
      throw new RuntimeException("I popped until empty, but its not empty");
    }

    if(!(back.equals("Back") && front.equals("Front"))){
      throw new RuntimeException("I pushed `Back`, unshifted `Front`, then popped both, got something unexpected: " + back + "->" + front);
    }

    // test for shifts
    nd.push("Back");
    nd.unshift("Middle");
    nd.unshift("Front");
    front = nd.shift();
    middle = nd.shift();
    back = nd.shift();

    if(!(back.equals("Back") && front.equals("Front"))){
      throw new RuntimeException("I pushed `Back`, unshifted `Front`, then shifted both, got something unexpected: " + front + "->" + back);
    }

    // clear test - see how this is getting repetitive, we should be inheriting this method
    nd.push("to be cleared");
    nd.clear();
    if(!nd.isEmpty()){
      throw new RuntimeException("I could not clear the deque");
    }

    System.out.println("Woot. NodeDeque passed tests.");
  }
}
