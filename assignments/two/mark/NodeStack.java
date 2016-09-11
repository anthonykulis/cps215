// CPS215 Assignment 2
// Mark Kiel

package data_structures.stacks;

import data_structures.nodes.Node;

public class NodeStack<T> implements StackInterface<T> {
    private Node _top;

    public NodeStack() {
        this.clear();
    }

    public void push(T item) {

        Node<T> n;
        
        if(this.isEmpty())
        {
            n = new Node<>();
            n.setData(item);
        }
        else { n = new Node<>(item, this._top); }
        
        this._top = n;
    }
    
    public void push(T[] items) {
        if ( (items != null) && (items.length > 0) )
            for (T item : items) {
                push(item);
            }
    }
    
    @SuppressWarnings("unchecked")
    public T pop() {
        if(this._top == null) { return null; }
        Node<T> n = this._top;
        this._top = n.next();
        return (T)n.getData();
    }
    
    @SuppressWarnings("unchecked")
    public T[] pop(int numItems) {
        Object[] items = new Object[numItems];
        int index = 0;
        
        while( !this.isEmpty() && index < numItems ) {
            items[index] = this.pop();
            index++;
        }
        
        return (T[])items;
    }
    
    @SuppressWarnings("unchecked")
    public T peek() {
        if(this._top == null) { return null; }
        return (T)this._top.getData();
    }
    
    public boolean isEmpty() {
        return this._top == null;
    }
    
    public void clear() {
        this._top = null;
    }

    public static void main(String[] args) {   
    
        NodeStack<String> ns = new NodeStack<>();
        ns.push("One");
        
        if(ns.isEmpty()) {
            throw new RuntimeException("I pushed a string and the stack is empty");
        }
        
        String one = ns.peek();
        if(!one.equals("One")) {
            throw new RuntimeException("I pushed 'One' and got back: " + one);
        }
        
        one = ns.pop();
        if(one == null) {
            throw new RuntimeException("I popped the stack, expected a return, got null");
        }
        
        if(!one.equals("One")) {
            throw new RuntimeException("I popped the stack, expected a value of 'One', got: " + one);
        }
        
        ns.push("one");
        ns.push("Two");
        ns.push("Three");
        
        String top = ns.pop();
        if(!top.equals("Three")) {
            throw new RuntimeException("I pushed one, two, and three, popped and didn't get three but got: " + top);
        }
        
        ns.clear();
        
        if(!ns.isEmpty()) {
            throw new RuntimeException("I cleared two items, still have items");
        }
        
        // -------------------------- My Tests -----------------------------------------
        String[] numbers = { "One", "Two", "Three", "Four", "Five" };
 
        ns.push(numbers);        
        
        if(ns.isEmpty()) {
            throw new RuntimeException("I pushed a string array and the stack is empty");
        }     
        
        String s = ns.pop();
        if(!s.equals("Five")) {
            throw new RuntimeException("I popped the top element, excepted five but got: " + s);
        }
        
        s = ns.peek();
        if(!s.equals("Four")) {
            throw new RuntimeException("I peeked at the next element, expected four but got: " + s);
        }
        
        Object[] items;        
        items = ns.pop(3);
        s = items[1].toString();
        
        if(!s.equals("Three")) {
            throw new RuntimeException("Accessed index 1 of the popped array, expected three but got: " + s);
        }
        
        if(ns.isEmpty()) {
            throw new RuntimeException("There should be one item left on the stack, but it is empty");
        }
        
        System.out.println("NodeStack tests completed successfully");
    }
}