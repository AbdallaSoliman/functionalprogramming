/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author abdalla
 */
public class Pipeline {

    private static List<Double> Mydata = new ArrayList<Double>() {
        {
            add((double) 7);
            add((double) 4);
            add((double) 5);
            add((double) 6);
            add((double) 3);
            add((double) 8);
            add((double) 10);
        }
    };

    public static void main(String[] args) {

        long startTime = 0, endTime = 0;
        startTime = System.nanoTime();
        System.out.println("Imparative");
        imparative();
        endTime = System.nanoTime();

        System.out.println("time :" + (endTime - startTime));

        startTime = 0;
        endTime = 0;
        startTime = System.nanoTime();
        System.out.println("Declartive");
        declartive();
        endTime = System.nanoTime();
        System.out.println("time :" + (endTime - startTime));
    }

    private static void imparative() {
        for (Double x : Mydata) {
            System.out.println("" + SubtractTen(Square(Addone(x))));
        }
    }

    private static void declartive() {

//        Mydata.stream().map(x->Addone(x)).map(x->Square(x)).map(x->SubtractTen(x))
//        Mydata.stream().map(x->Addone(x)).map(x->Square(x)).filter(x->x<20).map(x->SubtractTen(x))
        Mydata.stream().map(x->Addone(x)).map(x->Square(x)).filter(x->x<70).sorted().limit(2).map(x->SubtractTen(x))
                .forEach(System.out::println);
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
}
