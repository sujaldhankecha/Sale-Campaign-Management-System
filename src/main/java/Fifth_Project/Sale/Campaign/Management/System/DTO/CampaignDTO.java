package Fifth_Project.Sale.Campaign.Management.System.DTO;

import java.time.LocalDate;

public class CampaignDTO {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public CampaignDTO(Long id, String title, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
