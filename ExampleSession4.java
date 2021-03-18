/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalprogramming;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 *
 * @author abdalla
 */
public class ExampleSession4 {
    
    private static Function<Integer, Pair<Double, Double>> A;
    private static List<Order> OrdersforProcessing = new ArrayList<Order>() {
        {
            add(new Order(BigDecimal.valueOf(1000)));
            add(new Order(BigDecimal.valueOf(6000)));
            add(new Order(BigDecimal.valueOf(5000)));
            add(new Order(BigDecimal.valueOf(300)));
        }
    };
    
    public static void main(String[] args) {
        
//        List<Order> OrderswithDiscounts = OrdersforProcessing.stream().map(x -> GetOrderwithDiscount(x, GetDiscountRules())).collect(Collectors.toList());
OrdersforProcessing.stream().map(x -> GetOrderwithDiscount(x, GetDiscountRules())).forEach(System.out::println);

    }
    
    public static Order GetOrderwithDiscount(Order R, List<Pair<Function<Order, Boolean>, Function<Order, BigDecimal>>> Rules) {
        BigDecimal discount = Rules.stream().filter(a -> a.getKey().apply(R)).map(b -> b.getValue().apply(R)).sorted().limit(3).collect(new BigDecimalAverageCollector());
        Order neworder = new Order(R);
        neworder.Discount = discount;
        return neworder;
    }
    
    public static List<Pair<Function<Order, Boolean>, Function<Order, BigDecimal>>> GetDiscountRules() {
        List<Pair<Function<Order, Boolean>, Function<Order, BigDecimal>>> DiscountRules
                = new ArrayList<Pair<Function<Order, Boolean>, Function<Order, BigDecimal>>>() {
            {
                add(new Pair<>(x -> isAQualified(x), x -> A(x)));
                add(new Pair<>(x -> isBQualified(x), x -> B(x)));
                add(new Pair<>(x -> isCQualified(x), x -> C(x)));
                add(new Pair<>(x -> isCQualified(x), x -> C(x)));
                add(new Pair<>(x -> isCQualified(x), x -> C(x)));
            }
        };
        
        return DiscountRules;
    }
    
    public static boolean isAQualified(Order r) {
        return true;
    }
    
    public static BigDecimal A(Order r) {
        
        return BigDecimal.valueOf(250);
    }
    
    public static boolean isBQualified(Order r) {
        return true;
    }
    
    public static BigDecimal B(Order r) {
        
        return BigDecimal.valueOf(500);
    }
    
    public static boolean isCQualified(Order r) {
        return false;
    }
    
    public static BigDecimal C(Order r) {
        
        return BigDecimal.valueOf(750);
    }
    
    public  static class Order {
        
        public Order(Order orderdata) {
            this.Discount = orderdata.Discount;
        }
        
        public Order(BigDecimal Discount) {
            this.Discount = Discount;
        }
        
        public BigDecimal Discount;

        @Override
        public String toString() {
            return ""+Discount;
        }
        
        
    }
    
}
