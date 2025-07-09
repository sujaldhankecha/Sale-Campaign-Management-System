package Fifth_Project.Sale.Campaign.Management.System.Repository;

import Fifth_Project.Sale.Campaign.Management.System.Model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    boolean existsByProductIdAndReasonContaining(Long productId, String reason);
    List<PriceHistory> findByProductId(Long productId);
}

