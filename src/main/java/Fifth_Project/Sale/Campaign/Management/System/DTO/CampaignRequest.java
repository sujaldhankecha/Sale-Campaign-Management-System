package Fifth_Project.Sale.Campaign.Management.System.DTO;

import java.time.LocalDate;
import java.util.List;

public class CampaignRequest {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CampaignProductRequest> campaignDiscount;

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<CampaignProductRequest> getCampaignDiscount() {
        return campaignDiscount;
    }
}
