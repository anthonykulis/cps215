package assignments.two;

public interface StackInterface<T> {
  public void push(T item);
  public void push(T[] items);
  public T pop();
  public T[] pop(int numberOfItems);
  public T peek();
  public boolean isEmpty();
  public void clear();
}
