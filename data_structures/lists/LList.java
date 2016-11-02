package data_structures.lists;

import data_structures.nodes.Node;

public class LList<T> implements ListInterface<T> {

  private Node<T> _front = null;
  private Node<T> _back = null;
  private int _size = 0;

  /*
    Adds item to back
  */
  public void add(T item){
    Node<T> back = _back;
    Node<T> i = new Node(item, null);
    if(back == null){
      this._front = i;
    } else {
      back.link(i);
    }
    this._back = i;
    this._size++;
  }

  /*
    Adds item at index
  */
  public void add(T item, int index) throws IndexOutOfBoundsException {

    ListIterator it = this.iterator();
    Node<T> node = null;
    Node<T> prev = this._front;

    // handle special cases
    if(index > this.size()){
      throw new IndexOutOfBoundsException();
    } else if(index == this.size()){
      this.add(item);
      return;
    } else if(index == 0){
      prev = null;
    }

    // find our node at index
    while(it.hasNext() && it.index() <= index){
      prev = node;
      node = it.nextNode();
    }

    // handle insert of node
    Node<T> i = new Node<>(item, node);
    if(prev != null){
      prev.link(i);
    } else {
      i.link(this._front);
      this._front = i;
    }

    // increase our size
    this._size++;
  }

  /*
    Remove item at front
  */
  public T remove(){
    Node<T> item = this._front;
    if(item.next() == null){
      this.clear();
    } else {
      this._front = item.next();
    }
    this._size--;
    return (T)item.getData();
  }

  /*
    Remove item at index
  */
  public T remove(int index){

    // handle special cases
    if(index > this.size()){
      throw new IndexOutOfBoundsException();
    } else if(index == 0){
      return this.remove();
    }

    Node<T> node = this._front;
    Node<T> prev = null;
    ListIterator it = this.iterator();

    // get node at index
    while(it.hasNext() && it.index() - 1 < index){
      prev = node;
      node = it.nextNode();
    }

    // link prev to next
    Node<T> next = node.next();
    prev.link(next);
    node.unlink();

    // reduce in size
    this._size--;

    return (T)node.getData();
  }

  public int size(){
    return this._size;
  }

  public void clear(){
    this._front = this._back =  null;
  }

  public boolean isEmpty(){
    return this.size() != 0;
  }

  /*
    Returns anonymous iterator class
  */
  public ListIterator<T> iterator(){
    return new ListIterator<T>(){

      private int _index = 0;
      private Node<T> _current = _front;

      public int index(){
        return this._index;
      }

      public boolean hasNext(){
        return this._index < _size;
      }

      public T next() throws IndexOutOfBoundsException {
        if(!this.hasNext()) throw new IndexOutOfBoundsException();
        Node<T> node = this._current;
        this._current = node.next();
        this._index++;
        return (T)node.getData();
      }

      public Node<T> nextNode() throws IndexOutOfBoundsException {
        if(!this.hasNext()) throw new IndexOutOfBoundsException();
        Node<T> node = this._current;
        this._current = node.next();
        this._index++;
        return node;
      }
    };
  }


  public static void main(String[] args){
    LList<String> ll = new LList<>();

    System.out.println("Staring with Dog and Cat");
    ll.add("Dog");
    ll.add("Cat");

    System.out.println("Adding horses\n");
    for(int i = 0; i <= 4; i += 2)
      ll.add("Horse", i);

    ListIterator it = ll.iterator();
    while(it.hasNext()){
      System.out.println(it.next());
    }

    System.out.println("\nRemoving Horses\n");

    for(int i = 4; i >= 0; i -= 2)
      ll.remove(i);

    it = ll.iterator();
    while(it.hasNext()){
      System.out.println(it.next());
    }
  }
}
