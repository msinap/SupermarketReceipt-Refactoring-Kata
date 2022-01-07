package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

import java.util.HashMap;
import java.util.Map;

public class OfferRepository {

    private static OfferRepository instance = null;
    private final Map<Product, Offer> offers = new HashMap<>();

    public static OfferRepository getInstance() {
        if (instance == null) {
            instance = new OfferRepository();
        }
        return instance;
    }

    public void addOffer(SpecialOfferType offerType, Product product, double argument) {
        Offer offer;
        switch (offerType) {
            case ThreeForTwo:
                offer = new ThreeForTwo(product);
                break;
            case TwoForAmount:
                offer = new XForAmount(product, argument, 2);
                break;
            case FiveForAmount:
                offer = new XForAmount(product, argument, 5);
                break;
            case TenPercentDiscount:
                offer = new TenPercentDiscount(product, argument);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + offerType);
        }
        this.offers.put(product, offer);
    }

    public boolean hasProductOffer(Product p) {
        return offers.containsKey(p);
    }

    public Offer getOffer(Product p) {
        return offers.get(p);
    }

}
