package examples.generics;


public class OrderedPair<T> implements Pairable<T> {
  private T _first, _second;

  public OrderedPair(T first, T second){
    this._first = first;
    this._second = second;
  }

  public T getFirst(){
    return this._first;
  }

  public T getSecond(){
    return this._second;
  }

  public void changeOrder(){
    T temp = this._first;
    this._first = this._second;
    this._second = temp;
  }

  public String toString(){
    return this._first.toString() + " -> " + this._second.toString();
  }

  public static void main(String args[]){
    OrderedPair<String> animals = new OrderedPair<>("dog", "cat");
    System.out.println(animals);
    animals.changeOrder();
    System.out.println(animals);
    OrderedPair<Integer> numbers = new OrderedPair<>(new Integer(1), new Integer(2));
    System.out.println(numbers);
    numbers.changeOrder();
    System.out.println(numbers);
  }
}
