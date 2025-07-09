package Fifth_Project.Sale.Campaign.Management.System.Repository;

import Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignDTO;
import Fifth_Project.Sale.Campaign.Management.System.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByStartDate(LocalDate startDate);

    List<Campaign> findByEndDate(LocalDate endDate);

    @Query("SELECT new Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignDTO(" +
            "c.id, c.title, c.startDate, c.endDate) " +
            "FROM Campaign c WHERE :today BETWEEN c.startDate AND c.endDate")
    List<CampaignDTO> findCurrentCampaigns(@Param("today") LocalDate today);

    @Query("SELECT new Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignDTO(" +
            "c.id, c.title, c.startDate, c.endDate) " +
            "FROM Campaign c WHERE c.endDate < :today")
    List<CampaignDTO> findPastCampaigns(@Param("today") LocalDate today);

    @Query("SELECT new Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignDTO(" +
            "c.id, c.title, c.startDate, c.endDate) " +
            "FROM Campaign c WHERE c.startDate > :today")
    List<CampaignDTO> findUpcomingCampaigns(@Param("today") LocalDate today);
}

