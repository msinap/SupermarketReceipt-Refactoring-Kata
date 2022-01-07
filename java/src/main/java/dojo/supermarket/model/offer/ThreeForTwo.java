package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public class ThreeForTwo extends Offer {

    public ThreeForTwo(Product product) {
        super(product);
    }

    @Override
    public Discount getDiscount(double quantity, double unitPrice) {
        int quantityAsInt = (int) quantity;
        if (quantityAsInt <= 2)
            return null;
        int numberOfXs = quantityAsInt / 3;
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        return new Discount(product, "3 for 2", -discountAmount);
    }

}
