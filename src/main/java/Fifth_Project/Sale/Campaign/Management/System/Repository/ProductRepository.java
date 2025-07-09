package Fifth_Project.Sale.Campaign.Management.System.Repository;

import Fifth_Project.Sale.Campaign.Management.System.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
