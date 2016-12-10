package labs.two.solution;

public interface OrderedHashListIterator<K,V>{
  public int index();
  public boolean hasNext();
  public K next();
}
