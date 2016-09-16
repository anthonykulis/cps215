package assignments.two;

import data_structures.nodes.Node;

public class NodeStack<T> implements StackInterface<T>{

  private Node _top;

  public NodeStack(){
    this.clear();
  }

  public void push(T item){

    /*
      two possibilities
        1) stack is empty and hence we do not have a next
        2) stack has items

      why this causes a condition is because the Node class has two Constructors
      - one requires the item and next
      - the other is default and takes no arguments
      hence we have to do the extra step on an empty stack.
    */
    Node<T> n;
    if(this.isEmpty()){
      n = new Node<>();
      n.setData(item);
    } else {
      n = new Node<>(item, this._top);
    }

    this._top = n;
  }

  public void push(T[] items){
	  for(int i=0; i<items.length; i++){
		  this.push(items[i]);
	  }
  }

  public T pop(){
    if(this._top == null){ return null; }
    Node<T> n = this._top;
    this._top = n.next();
    T data = (T)n.getData();
    n.clearData();
    return data;
  }

  public T[] pop(int items){
	  T[] temp = (T[])new Object[items];
      for( int i = 0; i<items; i++){
	  		temp[i]=this.pop();
	  	}
	  	
	  return temp;
  }

  public T peek(){
    if(this._top == null){ return null; }
    return (T)this._top.getData();
  }

  public boolean isEmpty(){
    return this._top == null;
  }

  public void clear(){
    this._top = null;
  }

  public static void main(String args[]){
    NodeStack<String> ns = new NodeStack<>();
    ns.push("One");

    if(ns.isEmpty()){
      throw new RuntimeException("I pushed a string and the stack is empty");
    }

    String one = ns.peek();
    if(!one.equals("One")){
      throw new RuntimeException("I pushed `One` and got back: " + one);
    }

    one = ns.pop();
    if(one == null){
      throw new RuntimeException("I popped the stack, expected a return, got null");
    }

    if(!one.equals("One")){
      throw new RuntimeException("I popped the stack, expected a value of `One`, got: " + one);
    }

    ns.push("One");
    ns.push("Two");
    ns.push("Three");

    String top = ns.pop();
    if(!top.equals("Three")){
      throw new RuntimeException("I pushed one, two, and three, popped and didn't get three but got: " + top);
    }

    ns.clear();

    if(!ns.isEmpty()){
      throw new RuntimeException("I cleared two items, still have items");
    }

    String[] inputArray={"One", "Two", "Three"};
    ns.push(inputArray);
    if(ns.isEmpty()){
    	throw new RuntimeException("Input three strings, but it is empty");
    }

    Object[] output=ns.pop(3);
    if(output.length!=3){
    	throw new RuntimeException("Items were not poped");
    }

    System.out.println("NodeStack tests completed successfully");
  }
}
