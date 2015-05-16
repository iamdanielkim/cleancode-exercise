package ex2.duplication;

/**
 * Created by iamdanielkim on 15. 5. 16..
 */
public interface Order {

    public int getOrderId();

    public int getProductCount();

    public Product getProduct(int j);
}
