package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    final Map<Product, Double> productQuantities = new HashMap<>();
    private final List<ProductQuantity> items = new ArrayList<>();

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    private int getXFor(SpecialOfferType specialOfferType) {
        switch (specialOfferType) {
            case ThreeForTwo:   return 3;
            case TwoForAmount:  return 2;
            case FiveForAmount: return 5;
            default:            return 1;
        }
    }

    private Discount getDiscountXForAmount(int x, Product p, double quantity, double unitPrice, double argument) {
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < x)
            return null;
        int intDivision = quantityAsInt / x;
        double pricePerUnit = argument * intDivision;
        double theTotal = (quantityAsInt % x) * unitPrice;
        double total = pricePerUnit + theTotal;
        double discountN = unitPrice * quantity - total;
        return new Discount(p, x + " for " + argument, -discountN);
    }

    private Discount getDiscountForProduct(Product p, Offer offer, double unitPrice) {
        double quantity = productQuantities.get(p);
        int quantityAsInt = (int) quantity;
        int x = getXFor(offer.offerType);
        if (offer.offerType == SpecialOfferType.TwoForAmount || offer.offerType == SpecialOfferType.FiveForAmount) {
            return getDiscountXForAmount(x, p, quantity, unitPrice, offer.argument);
        }
        if (offer.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
            int numberOfXs = quantityAsInt / x;
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            return new Discount(p, "3 for 2", -discountAmount);
        }
        if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
            return new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
        }
        return null;
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p : productQuantities().keySet()) {
            if (!offers.containsKey(p))
                continue;
            Offer offer = offers.get(p);
            double unitPrice = catalog.getUnitPrice(p);
            Discount discount = getDiscountForProduct(p, offer, unitPrice);
            if (discount != null)
                receipt.addDiscount(discount);
        }
    }
}
