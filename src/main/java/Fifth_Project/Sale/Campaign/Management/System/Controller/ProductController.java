package Fifth_Project.Sale.Campaign.Management.System.Controller;

import Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignListResponse;
import Fifth_Project.Sale.Campaign.Management.System.DTO.CampaignRequest;
import Fifth_Project.Sale.Campaign.Management.System.Model.PriceHistory;
import Fifth_Project.Sale.Campaign.Management.System.Model.Product;
import Fifth_Project.Sale.Campaign.Management.System.Repository.PriceHistoryRepository;
import Fifth_Project.Sale.Campaign.Management.System.Service.ProductService;
import Fifth_Project.Sale.Campaign.Management.System.Wrapper.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sale_campaign")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    // Add Products
    @PostMapping("add-product")
    public ResponseEntity<ApiResponse<List<Product>>> addProduct(@Valid @RequestBody List<Product> products) {
        logger.info("Adding {} products", products.size());

        List<Product> productList = productService.addProduct(products);
        ApiResponse<List<Product>> response = new ApiResponse<>(true, "Products fetched successfully", products);
        return ResponseEntity.ok(response);
    }

    // Create Campaign
    @PostMapping("campaigns")
    public ResponseEntity<ApiResponse<String>> createCampaign(@Valid @RequestBody CampaignRequest request) {
        logger.info("Creating campaign: {}", request.getTitle());

        productService.createCampaign(request);
        ApiResponse<String> response = new ApiResponse<>(true, "Campaign created successfully.", null);
        return ResponseEntity.ok(response);
    }

    // Paginated Products
    @GetMapping("products")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPaginatedProducts(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        logger.info("Fetching paginated products: page {}, size {}", page, pageSize);

        Map<String, Object> paginatedData = productService.getPaginatedProducts(page, pageSize);
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(true, "Products fetched successfully", paginatedData);
        return ResponseEntity.ok(response);
    }

    // Price History
    @GetMapping("{productId}/price-history")
    public ResponseEntity<ApiResponse<List<PriceHistory>>> getPriceHistory(@Valid @PathVariable Long productId) {
        logger.info("Fetching price history for product ID: {}", productId);

        List<PriceHistory> history = priceHistoryRepository.findByProductId(productId);
        ApiResponse<List<PriceHistory>> response = new ApiResponse<>(true, "Price history fetched successfully", history);
        return ResponseEntity.ok(response);
    }

    // All Campaigns
    @GetMapping("all-campaigns")
    public ResponseEntity<ApiResponse<CampaignListResponse>> getAllCampaigns() {
        logger.info("Fetching all campaigns");

        CampaignListResponse campaignList = productService.getAllCampaignCategorized();
        ApiResponse<CampaignListResponse> response = new ApiResponse<>(true, "All campaigns fetched successfully", campaignList);
        return ResponseEntity.ok(response);
    }

}
