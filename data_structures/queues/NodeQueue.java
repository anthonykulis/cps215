package data_structures.queues;

import data_structures.nodes.Node;

public class NodeQueue<T> implements QueueInterface<T> {

  private Node _front, _back;

  public NodeQueue(){
    this.clear();
  }

  public void push(T item){
    Node<T> back = new Node<>();
    back.setData(item);

    if(this.isEmpty()){
      this._front = back;
    } else {
      this._back.link(back);
    }

    this._back = back;
  }

  public T shift(){
    if(this._front == null){ return null; }

    Node<T> n = this._front;
    this._front = n.next();
    return (T)n.getData();
  }

  public T peek(){
    if(this._front == null){ return null; }
    return (T)this._front.getData();
  }

  public boolean isEmpty(){ return this._front == null; }

  public void clear(){
    this._front = null;
    this._back = null;
  }

  public static void main(String[] args){
    NodeQueue nq = new NodeQueue();
    nq.push("one");

    String one = (String)nq.peek();
    if(one == null){
      throw new RuntimeException("I pushed `one` and when peeking got " + one);
    }

    nq.push("two");
    one = (String)nq.peek();
    if(!one.equals("one")){
      throw new RuntimeException("I pushed one, and on peek got " + one);
    }

    one = (String)nq.shift();
    if(one == null){
      throw new RuntimeException("I shifted one, got " + one);
    }

    if(!one.equals("one")){
      throw new RuntimeException("I shifted one, and on shift got " + one);
    }

    nq.clear();
    if(!nq.isEmpty()){
      throw new RuntimeException("I cleared the queue and its not empty");
    }

    System.out.println("Woot. NodeQueue works");
  }
}
