package Fifth_Project.Sale.Campaign.Management.System.Repository;

import Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignRequest;
import Fifth_Project.Sale.Campaign.Management.System.Model.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignProductRepository extends JpaRepository<CampaignProduct, Long> {
    //SELECT cp FROM CampaignProduct cp
    //WHERE cp.productId = :productId
    //  AND cp.campaign.startDate < :start
    //  AND cp.campaign.endDate > :end
    List<CampaignProduct> findByProductIdAndCampaign_StartDateBeforeAndCampaign_EndDateAfter(Long productId, LocalDate start, LocalDate end);
    List<CampaignProduct> findByCampaign(CampaignRequest campaign);

}

