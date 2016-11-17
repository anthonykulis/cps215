package jcf;

import java.util.*;
import java.util.stream.*;

public class AggregationExample {

    private int nextId = 1;

    MyObject getObject() {
        MyObject o = new MyObject(this.nextId);
        this.nextId++;
        return o;
    }

    private class MyObject {
        private int id;

        MyObject(int id) {
            this.id = id;
        }

        public String toString() {
            return "MyObject@" + id;
        }
    }

    public static void main(String[] args) {
        AggregationExample ae = new AggregationExample();
        List<MyObject> objex = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            objex.add(ae.getObject());

        // simple lambda forEach
        System.out.println("*** Sequential ***");
        objex.stream().forEach(myObject -> System.out.println(myObject));

        // parallel lamda forEach
        System.out.println("\n*** Parallel ***");
        objex.parallelStream().forEach(myObject -> System.out.println(myObject));

        // simple map and reduce
        System.out.println("\n*** Squential Map|Reduce");
        System.out.println(objex.stream().map(MyObject::toString).collect(Collectors.joining(", ")));

        // parallel map and reduce
        System.out.println("\n*** Parallel Map|Reduce");
        System.out.println(objex.parallelStream().map(MyObject::toString).collect(Collectors.joining(", ")));
    }
}
