/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javafx.util.Pair;

/**
 *
 * @author abdalla
 */
public class ExampleSession8 {

    public static List<Double> MyData = new ArrayList<Double>() {
        {
            add(Double.valueOf(2));
            add(Double.valueOf(1));
            add(Double.valueOf(3));
            add(Double.valueOf(6));
            add(Double.valueOf(9));
            add(Double.valueOf(10));
            add(Double.valueOf(11));
            add(Double.valueOf(13));
            add(Double.valueOf(18));
        }
    };

    public static void main(String[] args) {

        Declarative();
        Imperative();

    }

    public static void Declarative() {
        List<Double> Result1
                = MyData.stream().map(x -> Addone(x)).map(x -> Square(x)).map(x -> SubtractTen(x)).filter(x -> x > 5).limit(2).collect(Collectors.toList());
        for (Double R1 : Result1) {
            System.out.println(R1);
        }

    }

    public static void Imperative() {
        List< Double> Result2 = StreamSupport.stream(DoTakeTwo().spliterator(), false)
                .collect(Collectors.toList());
        for (Double R2 : Result2) {
            System.out.println(R2);
        }
    }

    public static Double Addone(Double x) {
        System.out.println("I am Adding one");
        return x + 1;
    }

    public static Double Square(Double x) {
        System.out.println("I am Doing a Square");
        return Math.pow(x, 2);
    }

    public static Double SubtractTen(Double x) {
        System.out.println("I am Subtracing Ten--------------");
        return x - 10;
    }

    public static Yielderable<Double> DoAddOne() {
        return yield -> {
            for (Double v : MyData) {
                yield.returning(Addone(v));
            };

        };
    }

    public static Yielderable<Double> DoSquare() {
        return yield -> {
            for (Double v : DoAddOne()) {
                yield.returning(Square(v));
            };

        };
    }

    public static Yielderable<Double> DoSubtractTen() {
        return yield -> {
            for (Double v : DoSquare()) {
                yield.returning(SubtractTen(v));
            };

        };
    }

    public static Yielderable<Double> DoWhere() {
        return yield -> {
            Function<Double, Boolean> W = x -> x > 5;
            for (Double v : DoSubtractTen()) {
                if (W.apply(v)) {
                    yield.returning(v);
                }

            };
            yield.breaking();
        };
    }

    public static Yielderable<Double> DoTakeTwo() {
        return yield -> {
            int i = 0, n = 2;
            ClosableIterator<Double> Cursor = DoWhere().iterator();
            do {
                Double v = null;
                if (i != n && Cursor.hasNext()) {
                    v = Cursor.next();
                } else{
                     yield.breaking();
                }

                if (i < n) {
                    yield.returning(v);
                    i = i + 1;
                } else {
                    yield.breaking();
                }
            } while (true);

        };
    }
}
