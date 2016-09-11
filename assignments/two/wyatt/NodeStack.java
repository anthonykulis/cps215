// package data_structures.stacks;
package assignments.two;

/*
  Instructor Notes:
  Your code does not compile. Your implementation does not follow the interface.

  Rules given in assignment
    1) We should overload push to accept an array of type <T>
    2) We should overload pop to accept n of type int and return and array of n length. 

*/
import data_structures.nodes.Node;

/*
   Author: Wyatt Melton
*/

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
      }
      else {
         n = new Node<>(item, this._top);
      }

      this._top = n;
   }

   /*
    Instructor notes:
    Reactoring:
      - The variable passed should not be capitalized.
      - Not declarative.
      - Tab formatting wrong (this._top = n;)
   */
   public void push(T[] Items) {
      Node<T> n;

      for (int i = 0; i < Items.length; i++)  {

         if (this.isEmpty())   {
            n = new Node<>();
            n.setData(Items[i]);
         }

         else   {
            n = new Node<>(Items[i], this._top);
         }

      this._top = n;
      }



   }

   public T pop(){
      if(this._top == null){
         return null; }
      Node<T> n = this._top;
      this._top = n.next();
      return (T)n.getData();
   }

   /*
    Instructor note:
    This doesn't work. I am popping *n* items. You return the data on the top,
    not the first n items. Also, the return type is wrong. You are returning one item,
    not *n* items.

    Refactoring
      - Tab formatting WAAAAY off. Please take the time to make the code
      readble for others. Remember, the point of publishing code is for the
      next guy to be able to read it. Also, if you submitted code at work for
      review and you did not follow basic tabbing standards, your boss might be
      very suspect of your skills.
      - Be declarative. Pop already exists, no need to re-implement it. Now if there
      were some reason for another developer to come into the code at a later date and
      re-implment pop, they would have to do it in 2 spots instead of one. Because
      of this, you just cost your company 2x the costs than what it should have been.

   */
   public T pop(int n)  {

   Node<T> stack = this._top;

   for (int i = 0; i < n; i++) {
      if (this._top == null) {
         return null; }
      else  {
         this._top = stack.next();
      }
   }
        return (T)stack.getData();
   }

   public T peek(){
      if(this._top == null){
         return null; }
      return (T)this._top.getData();
   }

   public boolean isEmpty(){
      return this._top == null;
   }

   public void clear(){
      this._top = null;
   }

   /*
    Instructor Notes:
    Wyatt, you did not test for push(T []items);

   */
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

      String[] newArray = {"One", "Two", "Three",};
      ns.push(newArray);

      if(ns.isEmpty()) {
         throw new RuntimeException("I added some items, still have nothing");
      }

      String newPeek = ns.peek();

      if(newPeek != "Three")  {
         throw new RuntimeException("Tried to take a peek at three, but I got: " + newPeek);
      }

      /*
        Instructor notes:
        There is no test for pop(int numberOfItems). Just because you called it doesn't test the runtime logic,
        only the runtime grammar. Because it didn't crash does not mean it is correct.
      */
      ns.pop(newArray.length);

      if(ns.pop() == null)   {
         throw new RuntimeException("I popped multiple items, expected a return, got null");
      }

      System.out.println("NodeStack tests completed successfully");
   }
}
