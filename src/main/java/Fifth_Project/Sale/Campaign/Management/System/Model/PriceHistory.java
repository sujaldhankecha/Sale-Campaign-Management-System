package Fifth_Project.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double priceBefore;
    private double priceAfter;

    private LocalDate changedAt;

    private String reason; // e.g. "Campaign Start", "Campaign End"

    public PriceHistory() {
    }

    public PriceHistory(Product product, double priceBefore, double priceAfter, LocalDate changedAt, String reason) {
        this.product = product;
        this.priceBefore = priceBefore;
        this.priceAfter = priceAfter;
        this.changedAt = changedAt;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public double getPriceBefore() {
        return priceBefore;
    }

    public double getPriceAfter() {
        return priceAfter;
    }

    public LocalDate getChangedAt() {
        return changedAt;
    }

    public String getReason() {
        return reason;
    }
}
