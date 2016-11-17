package jcf;

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