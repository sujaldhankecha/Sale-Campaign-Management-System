package Fifth_Project.Sale.Campaign.Management.System.DTO;

public class CampaignProductRequest {
    private Long productId;
    private int discount;

    public CampaignProductRequest(){}

    public CampaignProductRequest(Long productId, int discount) {
        this.productId = productId;
        this.discount = discount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
