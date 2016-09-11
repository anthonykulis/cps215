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
  
  public void push(T[] item){
   for(int index = 0; index<item.length; index++){
      this.push(item[index]);
   }
  }
  
  public T[] pop(int numOfItems){
   T[] itemArray = (T[]) new Object[numOfItems];
   T item = this.pop();
   
   int index=0;
   
   while(index < numOfItems){
      itemArray[index] = item;
      index++;
      item = this.pop();
   }
   return itemArray;
  }

  public T pop(){
    if(this._top == null){ return null; }
    Node<T> n = this._top;
    this._top = n.next();
    return (T)n.getData();
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
      String[] people = {"george", "seth", "bob", "dave"};
      ns.push(people);
      
      if(ns.isEmpty()){
         throw new RuntimeException("I pushed all my friends but they disappeared.");
      }
      
      Object[] peoplePopper = ns.pop(4);
      
      if(peoplePopper.length != 4){
         throw new RuntimeException("I popped four friends but didn't get four friends.");
      }
      
      System.out.println("can we go over typecasting? takes me awhile to realize when I need it \n\n complete");   
       
      
      
      
    }
  
}    