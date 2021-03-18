/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.util.function.Function;
import javafx.util.Pair;

/**
 *
 * @author abdalla
 */
public class HigherOrderFunctions {

    private static Order R = new Order(10, 100, 20, 4);
    private static Function<Integer, Pair<Double, Double>> A = x -> ProductParamtersFood(x);
    private static Function<Integer, Pair<Double, Double>> B = x -> ProductParamtersBeverage(x);
    private static Function<Integer, Pair<Double, Double>> C = x -> ProductParamtersRawMaterial(x);

    public static void main(String[] args) {

        ProductType product = ProductType.Food;
        Function<Integer, Pair<Double, Double>> P = (product == ProductType.Food) ? A : ((product == ProductType.Beverage) ? B : C);
        System.out.println(ClaculateDiscount(P, R));

//        Function< Double, Double> DlgTest1 = x -> Test1(x);
//        Function<Double, Double> DlgTest2 = x -> Test2(x);
//        List<Function<Double, Double>> z = new ArrayList<Function< Double, Double>>() {
//            {
//                add(DlgTest1);
//                add(DlgTest2);
//            }
//        };
//        System.out.println(Test2(Test1(5)));
//        System.out.println(Test1(Test2(5)));
//        System.out.println(z.get(0).apply((double) 5));
//        System.out.println(z.get(1).apply((double) 5));
//        System.out.println(Test3(x -> Test1(x), (double) 5));
//        System.out.println(Test3(x -> Test2(x), (double) 5));
    }

//    public static double Test1(double x) {
//        return x / 2;
//    }
//    public static double Test2(double x) {
//        return x / 4 + 1;
//    }
//    public static double Test3(Function< Double, Double> F, Double value) {
//        return F.apply(value) + value;
//    }
    public static double ClaculateDiscount(Function<Integer, Pair<Double, Double>> ProductParamterCalc, Order Order) {
        Pair<Double, Double> paramters = ProductParamterCalc.apply(Order.ProductIndex);
        return paramters.getKey() * Order.Quantity + paramters.getValue() * Order.UnitPrice;
    }

    public static Pair<Double, Double> ProductParamtersFood(int ProductIndex) {
        return new Pair(ProductIndex / (double) (ProductIndex + 100), ProductIndex / (double) (ProductIndex + 300));
    }

    public static Pair<Double, Double> ProductParamtersBeverage(int ProductIndex) {
        return new Pair(ProductIndex / (double) (ProductIndex + 300), ProductIndex / (double) (ProductIndex + 400));
    }

    public static Pair<Double, Double> ProductParamtersRawMaterial(int ProductIndex) {
        return new Pair(ProductIndex / (double) (ProductIndex + 400), ProductIndex / (double) (ProductIndex + 700));
    }

    public enum ProductType {
        Food,
        Beverage,
        RawMaterial

    }

    public static class Order {

        public int OrderID;
        public int ProductIndex;
        public double Quantity;
        public double UnitPrice;

        public Order(int OrderID, int ProductIndex, double Quantity, double UnitPrice) {
            this.OrderID = OrderID;
            this.ProductIndex = ProductIndex;
            this.Quantity = Quantity;
            this.UnitPrice = UnitPrice;
        }

    }
}
