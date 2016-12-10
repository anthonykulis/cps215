package data_structures.lists;
import data_structures.lists.ListIterator;
public interface ListInterface<T> {
  public void add(T item);
  public void add(T item, int index);
  public T remove();
  public T remove(int index);
  public int size();
  public ListIterator<T> iterator();
  
  /* 
    Typical helpers. 
  */
  public void clear();
  public boolean isEmpty();
  
}
