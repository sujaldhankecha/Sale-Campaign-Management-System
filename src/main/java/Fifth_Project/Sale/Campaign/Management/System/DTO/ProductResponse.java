package Fifth_Project.Sale.Campaign.Management.System.DTO;

public class ProductResponse {
    private Long id;
    private double mrp;
    private double currentPrice;
    private int discount;
    private int inventory;

    public ProductResponse(){}

    public ProductResponse(Long id, double mrp, double currentPrice, int discount, int inventory) {
        this.id = id;
        this.mrp = mrp;
        this.currentPrice = currentPrice;
        this.discount = discount;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
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
