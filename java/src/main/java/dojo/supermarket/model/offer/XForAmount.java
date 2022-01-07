package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class XForAmount extends Offer {

    private final int x;
    private final double discountedPrice;

    public XForAmount(Product product, double discountedPrice, int x) {
        super(product);
        this.discountedPrice = discountedPrice;
        this.x = x;
    }

    public Discount getDiscount(double quantity, double unitPrice) {
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < x)
            return null;
        int intDivision = quantityAsInt / x;
        double pricePerUnit = discountedPrice * intDivision;
        double theTotal = (quantityAsInt % x) * unitPrice;
        double total = pricePerUnit + theTotal;
        double discountN = unitPrice * quantity - total;
        return new Discount(product, x + " for " + discountedPrice, -discountN);
    }

}
