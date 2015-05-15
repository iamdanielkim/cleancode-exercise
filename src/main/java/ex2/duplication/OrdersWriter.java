package ex2.duplication;

public class OrdersWriter {
    public String getContents() {
        StringBuffer contents = new StringBuffer();
        contents.append("<orders>");
        for (int i = 0; i < orders.getOrderCount(); i++) {
            Order order = orders.getOrder(i);
            contents.append("<order");
            contents.append(" id='");
            contents.append(order.getOrderId());
            contents.append("'>");
            for (int j=0; j < order.getProductCount(); j++) {
                Product product = order.getProduct(j);
                contents.append("<product");
                contents.append(" id='");
                contents.append(product.getID());
                contents.append("'");
                contents.append(" color='");
                contents.append(getColorFor(product));
                contents.append("'");
                if (product.getSize() != ProductSize.NOT_APPLICABLE) {
                    contents.append(" size='");
                    contents.append(getSizeFor(product));
                    contents.append("'");
                }
                contents.append(">");
                contents.append("<price");
                contents.append(" currency='");
                contents.append(getCurrencyFor(product));
                contents.append("'>");
                contents.append(product.getPrice());
                contents.append("</price>");
                contents.append(product.getName());
                contents.append("</product>");
            }
            contents.append("</order>");
        }
        contents.append("</orders>");
        return contents.toString();
    }

}
