package dojo.supermarket.model.offer;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Product;

public abstract class Offer {

    protected final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    public abstract Discount getDiscount(double quantity, double unitPrice);

    Product getProduct() {
        return this.product;
    }

}
