package Fifth_Project.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double mrp;
    private double currentPrice;
    private int discount; // base discount without campaign
    private int inventory;

    public Product(){}

    public Product(String title, double mrp, double currentPrice, int discount, int inventory) {
        this.title = title;
        this.mrp = mrp;
        this.currentPrice = currentPrice;
        this.discount = discount;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getMrp() {
        return mrp;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public int getInventory() {
        return inventory;
    }
}
