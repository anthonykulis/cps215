package data_structures.bags;

public interface BagInterface<T> {
  public int getCurrentSize();
  public boolean isEmpty();
  public boolean add(T item);
  public T remove();
  public void clear();
  public boolean contains(T item);
  public T[] toArray();
}
