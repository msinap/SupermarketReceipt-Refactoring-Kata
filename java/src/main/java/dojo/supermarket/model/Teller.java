package dojo.supermarket.model;

import dojo.supermarket.model.offer.OfferRepository;
import dojo.supermarket.model.offer.SpecialOfferType;

import java.util.List;

public class Teller {

    private final SupermarketCatalog catalog;
    private final OfferRepository offerRepository = OfferRepository.getInstance();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        offerRepository.addOffer(offerType, product, argument);
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq : productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.handleOffers(receipt, this.catalog);

        return receipt;
    }

}
