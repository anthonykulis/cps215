package data_structures.lists;
import data_structures.nodes.Node;

public interface ListIterator<T>{
  public boolean hasNext();
  public T next();
  public int index();
  
  /* 
    Only for our in-class purpose. 
    We would never do this in real life (nor be expected to know it for a test)
  */
  public Node<T> nextNode();
}
