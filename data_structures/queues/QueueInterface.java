package data_structures.queues;

public interface QueueInterface<T> {
  public void push(T item);
  public T shift();
  public T peek();
  public boolean isEmpty();
  public void clear();
}
