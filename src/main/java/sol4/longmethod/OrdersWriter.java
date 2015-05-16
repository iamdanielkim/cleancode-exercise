package sol4.longmethod;


public class OrdersWriter {
//    public String getContents() {
//        TagNode ordersTag = new TagNode("orders");
//        writeOrdersTo(ordersTag);
//        return ordersTag.toString();
//    }
//
//    private void writeOrdersTo(TagNode ordersTag) {
//        for (int i = 0; i < orders.getOrderCount(); i++) {
//            Order order = orders.getOrder(i);
//            TagNode orderTag = new TagNode("order");
//            orderTag.addAttribute("id", order.getOrderId().toString());
//            for (int j = 0; j < order.getProductCount(); j++) {
//                Product product = order.getProduct(j);
//                writeProductTo(product, orderTag);
//            }
//            ordersTag.add(orderTag);
//        }
//    }
//
//    private void writeProductTo(Product product, TagNode orderTag) {
//        TagNode productTag = new TagNode("product");
//        productTag.addAttribute("id", product.getID());
//        productTag.addAttribute("color", getColorFor(product));
//        if (product.getSize() != ProductSize.NOT_APPLICABLE)
//            productTag.addAttribute("size", getSizeFor(product));
//        writePriceTo(product, productTag);
//        productTag.addValue(product.getName());
//        orderTag.add(productTag);
//    }
//
//    private void writePriceTo(Product product, TagNode productTag) {
//        TagNode priceTag = new TagNode("price");
//        priceTag.addAttribute("currency", getCurrencyFor(product));
//        priceTag.addValue(product.getPrice() + "");
//        productTag.add(priceTag);
//    }
}
