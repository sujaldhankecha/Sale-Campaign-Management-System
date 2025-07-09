package Fifth_Project.Sale.Campaign.Management.System.DTO;


import java.util.List;

public class CampaignListResponse {
    private List<CampaignDTO> currentCampaigns;
    private List<CampaignDTO> pastCampaigns;
    private List<CampaignDTO> upcomingCampaigns;

    public CampaignListResponse() {
    }

    public CampaignListResponse(List<CampaignDTO> currentCampaigns, List<CampaignDTO> pastCampaigns, List<CampaignDTO> upcomingCampaigns) {
        this.currentCampaigns = currentCampaigns;
        this.pastCampaigns = pastCampaigns;
        this.upcomingCampaigns = upcomingCampaigns;
    }

    public List<CampaignDTO> getCurrentCampaigns() {
        return currentCampaigns;
    }

    public List<CampaignDTO> getPastCampaigns() {
        return pastCampaigns;
    }

    public List<CampaignDTO> getUpcomingCampaigns() {
        return upcomingCampaigns;
    }
}
