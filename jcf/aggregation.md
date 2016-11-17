# Lambdas and Mapping, Filtering, and Aggregation

## Lambdas

Purpose is to provide a "syntacical sugar" while using JCF. Instead of having to write many lines of code to manipulate a data structure, Java 8 has provided this syntax to speed up the process.

### Lambda Meaning
A lambda is a computer term which simply means the syntax has no belonging to a scope. It does not belong to the class nor a method in the class.

### Lambda Grammar and Example

#### Grammar
    (type variable, type variable, ...) -> (body of execution)
    
From Java 8 Docs:
    * The body can be either a single expression or a statement block. In the expression form, the body is simply evaluated and returned. In the block form, the body is evaluated like a method body and a return statement returns control to the caller of the anonymous method. The break and continue keywords are illegal at the top level, but are permitted within loops. If the body produces a result, every control path must return something or throw an exception.

#### Example
    (String s, int x, Object o) -> (return o.toString() + x + s);
    
### Where to use lambdas
A lambda is meant to replace code, so if we look a case of where we are using the interface `Runnable`, such that we have to implement `run`, we can simply use a lambda to simplify the code.

```java
public class Lambda{

    public static void main(String[] args){

        Runnable r1 = new Runnable(){
            public void run(){
                System.out.println("Hello from anonmyous");
            }
        };

        Runnable r2 = () -> (System.out.println("Hello from lambda"));

        r1.run();
        r2.run();

    }
}
```

Idealistically, we want to use lambdas in collections for "one off use", much in the same way we would anonymous classes with one method of use.

Imagine a collection of type `Person`. Person does not implement `Comparable`, but we want to be able to sort by last name. We can use the lambda instead.

```java
Collections.sort(persons, (p1,  p2) -> p2.getLastName().compareTo(p1.getLastName()));
```

This is dramatically simpler than implementing `compareTo`, and especially useful if we are using a `final` class that cannot be modified or inherited.

## Example class

```java
public class Student {
    Sex gender;
    private final String name;
    private double grade;
    
    public double getGrade(){}
    public double getName(){}
}
```

Imagine the following:
    
    List<Student> students = new ArrayList<>(lotsOfStudents);
    
    

## Filtering
Let us say we needed only the males in the list.

**Without Lambdas**

```java
List<Students> males = new ArrayList<>();
for(int i = 0, i < students.size(); i++){
    Student s = get(i);
    if(s.gender == MALE) males.add(s);
}
```

**With lambdas**
```java
List<Students> males = students.parallelStream().filter(s -> s.gender == MALE);
```

## Mapping
Imagine we needed to get all the grades in the class but have no student referenced to them. Mapping allows us to transform a list of one type into a list of another.

**Without lamdas**

```java
List<Double> grades = new ArrayList<>();
for(int i = 0; i < students.size(); i++){
    grades.add(students.get(i).getGrade());
}
```

**With Lambda**

```java
List<Double> grades = students.parallelStream().map(Student::getGrade);
```

## Aggregation
Many times our collection is "raw", meaning we need to aggregate data from our collection that may or may not be in an immediately useful form. We can generically use the term `reduce` to transform our collection into the data set needed. 



#### Aggregating 

Using the example class, how could we find the average grade of all female students and put it into a HashMap with keys of gender?

**Without Lambdas**

```java
double femaleTotalScore = 0;
double maleTotalScore = 0;
int numOfFemales = 0;
int numOfMales = 0;

for(int i = 0; i < students.size(); i++){
    Student s = studnets.get(i);
    if(s.gender == MALE){
        maleTotalScore += s.grade;
        numOfMales++;
    }
    else females.add(s){
        femaleTotalScore += s.grade;
        numOfFemales++;
    }
}

HashMap<Sex, Double> partionedAverages = new HashMap<>();
partionedAverages.add(MALE, maleTotalScore/numOfMales);
partionedAverages.add(FEMALE, femaleTotalSCore/numOfFemales);
```

**With Lambdas**

```java
HashMap<Sex, Double> partitionedAverages = new HashMap<>();
HashMap<Boolean, Student> isMale = students.stream().partitionBy(s -> s.gender == MALE);
partionedAverages.add(MALE, isMale.get(true).stream().collect(Collector.averagingDouble(Student::getGrade));
partionedAverages.add(FEMALE, isMale.get(false).stream().collect(Collector.averagingDouble(Student::getGrade));
```

## Filter, Map, Reduce
Now imagine a more complex scenario, where we need to get the average of only passing male students, but that average needs to be percentage. I will be omitting the **without lambda** for brevity.

**With Lambda**

```java
List<Student> passingMaleAverage = students.stream()
                                           .filter(s -> s.gender == MALE && s.getGrade() >= 60)
                                           .map(Student::getGrade)
                                           .collect(Collector.averagingDouble(grade -> grade * 100);
                                           
```