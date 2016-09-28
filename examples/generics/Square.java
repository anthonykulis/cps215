public class Square<T extends Number>{
  private T _side;
  public Square(T side){
    this._side = side;
  }
  public double getArea(){
    double s = this._side.doubleValue();
    return s * s;
  }

  public static void main(String[] args){

    // all legal
    Square<Integer> intSquare = new Square<>(4);
    Square<Double> doubleSquare = new Square<>(5.5);

    System.out.println(intSquare.getArea());
  }
}
