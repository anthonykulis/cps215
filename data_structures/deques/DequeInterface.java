package data_structures.deques;

public interface DequeInterface<T> {
  public void push(T item);
  public T pop();
  public void unshift(T item);
  public T shift();
  public T peekFirst();
  public T peekLast();
  public boolean isEmpty();
  public void clear();
}
