package Fifth_Project.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CampaignProduct> campaignProducts = new ArrayList<>();

    public Campaign(){}

    public Campaign(String title, LocalDate startDate, LocalDate endDate, List<CampaignProduct> campaignProducts) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignProducts = campaignProducts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCampaignProducts(List<CampaignProduct> campaignProducts) {
        this.campaignProducts = campaignProducts;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<CampaignProduct> getCampaignProducts() {
        return campaignProducts;
    }
}
