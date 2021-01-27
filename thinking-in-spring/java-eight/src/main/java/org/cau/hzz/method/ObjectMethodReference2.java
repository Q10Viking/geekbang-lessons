package org.cau.hzz.method;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class ObjectMethodReference2 {
    public static void main(String[] args) {
        Goods goods = new Goods("Apple",BigDecimal.valueOf(13.5),3);
        Formula formula = new Formula();

        calculate(formula, goods, (a, b) -> a.normal(b));
        calculate(formula, goods, Formula::normal);
    }

    private static BigDecimal calculate(Formula formula, Goods goods, BiFunction<Formula,Goods,BigDecimal> func){
        return func.apply(formula,goods);
    }
}

class Formula{
    public BigDecimal normal(Goods goods){
       return goods
               .getPrice()
               .multiply(BigDecimal.valueOf(goods.getNumber()));
    }
}
