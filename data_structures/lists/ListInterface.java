package data_structures.lists;
import data_structures.lists.ListIterator;
public interface ListInterface<T> {
  public void add(T item);
  public void add(T item, int index);
  public T remove();
  public T remove(int index);
  public int size();
  public void clear();
  public boolean isEmpty();
  public ListIterator<T> iterator();
}
