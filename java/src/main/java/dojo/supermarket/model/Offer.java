package dojo.supermarket.model;

public class Offer {
    final SpecialOfferType offerType;
    final double argument;
    private final Product product;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

}
