package ex4.logmethod;

import java.math.BigDecimal;

/**
 * Created by iamdanielkim on 15. 5. 16..
 */
public interface RecordSet {

    public String getItem(int row, String issue_name);

    public BigDecimal getDecimal(int row, String market_price);

    public int getRowCount();
}
