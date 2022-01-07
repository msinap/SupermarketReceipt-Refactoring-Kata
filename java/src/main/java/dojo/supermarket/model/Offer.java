package dojo.supermarket.model;

public class Offer {
    final SpecialOfferType offerType;
    private final Product product;
    final double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

}
