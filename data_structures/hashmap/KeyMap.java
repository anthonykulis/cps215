package data_structures.hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*
  A very simple hashmap to
  hash a key to a value

  Meant to help understand the
  lab.

  Use for storing keys for later.
*/

public class KeyMap<K extends Comparable<? super K>> {

  private final int SIZE = 4096;
  private ArrayList<K> store = new ArrayList<>(SIZE);

  public void add(K key){


    // hash our key
    int keyHash = key.hashCode();

    /*
    Since we cannot set an index whose
    predecesors are not initialized, lazily
    initialize our array list to null as we need it
    */
    this.store.ensureCapacity(keyHash + 1);
    while(this.store.size() <= keyHash) this.store.add(null);

    this.store.set(keyHash, key);
  }

  /*
   NOTE: DOES NOT CONISDER KEYS OUT OF RANGE
  */
  public K get(K key){
    return (K)this.store.get(key.hashCode());
  }

  /*
   NOTE: DOES NOT CONISDER KEYS OUT OF RANGE
  */
  public K remove(K key){
    return (K)this.store.remove(key.hashCode());
  }

  /*
  There is trickery in here. I will comment each step
  */
  @SuppressWarnings("unchecked")
  public K[] toArray(){

    /*
      uses HashSet to automatically convert my ArrayList
      into a set. A set can have no duplicates. So removes all but one
      null that showed up from lazily populating the array
    */
    Set<K> v = new HashSet<K>(store);

    // remove that one remaining null
    v.remove(null);

    // now I have an array of just keys
    Object[] a = v.toArray();

    /*
      this is a fancy way of converting the object items in a to comparable
      with a lower bound of K. All it does is allow me to return to your
      keys method an array that can be sorted. Since the final type is
      comparable, you can use the polymorphism to call upon ArraySorter.quickSort().
      Just make sure to cast your sorted array back to Object[] to meet the
      signature requirements.
    */

    return Arrays.asList(a).toArray((K[])new Comparable[a.length]);
  }

}
