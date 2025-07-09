package Fifth_Project.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;

@Entity
public class CampaignProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int discount; // campaign-specific discount %

    public CampaignProduct(){}

    public CampaignProduct(Campaign campaign, Product product, int discount) {
        this.campaign = campaign;
        this.product = product;
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public Product getProduct() {
        return product;
    }

    public int getDiscount() {
        return discount;
    }
}
