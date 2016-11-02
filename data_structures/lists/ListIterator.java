package data_structures.lists;
import data_structures.nodes.Node;

public interface ListIterator<T>{
  public boolean hasNext();
  public T next();
  public int index();
  public Node<T> nextNode();
}
