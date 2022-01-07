package dojo.supermarket.model;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.OfferRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    final Map<Product, Double> productQuantities = new HashMap<>();
    private final OfferRepository offerRepository = OfferRepository.getInstance();
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

    void handleOffers(Receipt receipt, SupermarketCatalog catalog) {
        for (Product p : productQuantities().keySet()) {
            if (!offerRepository.hasProductOffer(p))
                continue;
            Offer offer = offerRepository.getOffer(p);
            double quantity = productQuantities.get(p);
            double unitPrice = catalog.getUnitPrice(p);
            Discount discount = offer.getDiscount(quantity, unitPrice);
            if (discount != null)
                receipt.addDiscount(discount);
        }
    }
}
