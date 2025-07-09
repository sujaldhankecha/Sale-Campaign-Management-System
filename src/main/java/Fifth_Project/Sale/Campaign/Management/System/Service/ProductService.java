package Fifth_Project.Sale.Campaign.Management.System.Service;

import Fifth_Project.Sale.Campaign.Management.System.DTO.*;

import Fifth_Project.Sale.Campaign.Management.System.Model.Campaign;
import Fifth_Project.Sale.Campaign.Management.System.Model.CampaignProduct;
import Fifth_Project.Sale.Campaign.Management.System.Model.Product;
import Fifth_Project.Sale.Campaign.Management.System.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignProductRepository campaignProductRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    public List<Product> addProduct(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Transactional
    public void createCampaign(CampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setTitle(request.getTitle());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());

        List<CampaignProduct> campaignProducts = new ArrayList<>();

        for (CampaignProductRequest item : request.getCampaignDiscount()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));

            // Save campaign product entry
            CampaignProduct cp = new CampaignProduct();
            cp.setCampaign(campaign);
            cp.setProduct(product);
            cp.setDiscount(item.getDiscount());

            campaignProducts.add(cp);

        }

        campaign.setCampaignProducts(campaignProducts);

        // Save both campaign and campaignProducts using cascade
        campaignRepository.save(campaign);
    }

    public Map<String, Object> getPaginatedProducts(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        //Mapping Product Entity to Response DTO
        List<ProductResponse> products = productPage.getContent().stream().map(this::mapToDTOWithCampaignDiscount).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("product", products);
        response.put("page", page);
        response.put("pageSize", pageSize);
        response.put("totalPages", productPage.getTotalPages());

        return response;
    }

    private ProductResponse mapToDTOWithCampaignDiscount(Product product) {
        double basePrice = product.getCurrentPrice();
        int campaignDiscount = getTotalActiveCampaignDiscount(product.getId());

        if (campaignDiscount > 0) {
            basePrice = basePrice - (basePrice * campaignDiscount / 100);
        }

        return new ProductResponse(product.getId(), product.getMrp(), basePrice, campaignDiscount, product.getInventory());
    }

    private int getTotalActiveCampaignDiscount(Long productId) {
        LocalDate today = LocalDate.now();
        List<CampaignProduct> activeCampaigns = campaignProductRepository.findByProductIdAndCampaign_StartDateBeforeAndCampaign_EndDateAfter(productId, today, today);

        // Sum all discounts
        return activeCampaigns.stream()
                .mapToInt(CampaignProduct::getDiscount)
                .sum();
    }

    public CampaignListResponse getAllCampaignCategorized() {
        LocalDate today = LocalDate.now();
        List<CampaignDTO> current = campaignRepository.findCurrentCampaigns(today);
        List<CampaignDTO> past = campaignRepository.findPastCampaigns(today);
        List<CampaignDTO> upcoming = campaignRepository.findUpcomingCampaigns(today);

        return new CampaignListResponse(current, past, upcoming);
    }

}
