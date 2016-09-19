package base;

import java.math.BigDecimal;

public class MathTest {
    public static void main(String[] args) {
        BigDecimal oresult =
                new BigDecimal(0).divide(new BigDecimal(0), 5, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(oresult );
    }
}
