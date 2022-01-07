package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class TenPercentDiscount extends Offer {

    private final double argument;

    public TenPercentDiscount(Product product, double argument) {
        super(product);
        this.argument = argument;
    }

    @Override
    public Discount getDiscount(double quantity, double unitPrice) {
        return new Discount(product, argument + "% off", -quantity * unitPrice * argument / 100.0);
    }

}
