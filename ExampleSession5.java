/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author abdalla
 */
public class ExampleSession5 {

    private static List<Double> Mydata = new ArrayList<Double>() {
        {
            add((double) 3);
            add((double) 5);
            add((double) 7);
            add((double) 8);
        }
    };
      public static Function<Double, Double> MyComposedFunction =
			ComposeFunction(x -> Addone(x), x -> Square(x), x -> SubtractTen(x));

    public static void main(String[] args) {
        Mydata.stream().map(x -> Addone(x)).map(x -> Square(x)).map(x -> SubtractTen(x)).forEach(System.out::println);
        System.out.println("====================================");
        Mydata.stream().map(x -> SubtractTen(Square(Addone(x)))).forEach(System.out::println);
        System.out.println("====================================");
        Mydata.stream().map(x -> MyComposedFunction.apply(x)).forEach(System.out::println);
        System.out.println("====================================");
        Mydata.stream().map(AddoneSquareSubtractTen()).forEach(System.out::println);
    }

    public static Function<Double, Double> Test() {
        return x -> x + 1;
    }

    public static double Addone(double x) {
        return x + 1;
    }

    public static double Square(double x) {
        return Math.pow(x, 2);
    }

    public static double SubtractTen(double x) {
        return x - 10;
    }

    public static Function<Double, Double> ComposeFunction(Function<Double, Double> F1, Function<Double, Double> F2, Function<Double, Double> F3) {
        return (x)
                -> {
            return F3.apply(F2.apply(F1.apply(x)));
        };
    }

    public static Function<Double, Double> AddoneSquareSubtractTen() {
        Function< Double, Double> q1 = x -> Addone(x);
        Function< Double, Double> q2 = x -> Square(x);
        Function< Double, Double> q3 = x -> SubtractTen(x);

        return q1.andThen(q2).andThen(q3);

    }
    //Compose() C# example is a built in function in java = andThen()  no need to implement it and to make reverse action use compose() in java
//	public static Function<T1, T3> Compose<T1, T2, T3>(this  Func<T1, T2> f,Func<T2, T3> g)
//		{
//			return (x) => g(f(x));
//		}

}
