package ex4.logmethod;

import java.math.BigDecimal;

/**
 * Created by iamdanielkim on 15. 5. 16..
 */
public interface Assessor {
    BigDecimal getRiskCoefficient(String issue_family, BigDecimal term_two);
}
