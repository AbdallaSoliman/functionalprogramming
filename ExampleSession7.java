/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 *
 * @author abdalla
 */
public class ExampleSession7 {

    public static void main(String[] args) {
//        Function<Double, Double> Q10 = Test(Double.valueOf(10));
//        System.out.println("I am Here " + Q10.apply(Double.valueOf(4)));
//
//        Function<Double, Double> Q20 = Test(Double.valueOf(20));
//        System.out.println("I am Here " + Q20.apply(Double.valueOf(4)));

        List<Pair<String, Double>> z = new ArrayList<Pair<String, Double>>() {
            {
                add(new Pair<>("a", Double.valueOf(1000)));
                add(new Pair<>("b", Double.valueOf(2000)));
                add(new Pair<>("c", Double.valueOf(3000)));

            }
        };


        Map<String, Function<Double, Double>> GrossSalaryCalculators = z.stream()
                .collect(Collectors.toMap(data -> data.getKey(), data -> GrossSalaryCalculator(data.getValue())));

        System.out.println(GrossSalaryCalculators.get("a").apply(Double.valueOf(80)));
        System.out.println(GrossSalaryCalculators.get("b").apply(Double.valueOf(90)));
        System.out.println(GrossSalaryCalculators.getOrDefault("c", GrossSalaryCalculator(Double.valueOf(1000))).apply(Double.valueOf(100)));

    }

    public static Function<Double, Double> Test(Double x) {

        System.out.println("I am Here " + x);

        Double x1 = x + 10;

        return a -> a + x1;
    }

    public static Function<Double, Double> GrossSalaryCalculator(Double BasicSalary) {

        Double Tax = 0.2 * BasicSalary;

        return Bonus -> Bonus + Tax + BasicSalary;

    }
}
