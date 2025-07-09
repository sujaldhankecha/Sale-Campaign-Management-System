package Fifth_Project.Sale.Campaign.Management.System.Config;

import Fifth_Project.Sale.Campaign.Management.System.Model.Campaign;
import Fifth_Project.Sale.Campaign.Management.System.Model.CampaignProduct;
import Fifth_Project.Sale.Campaign.Management.System.Model.PriceHistory;
import Fifth_Project.Sale.Campaign.Management.System.Model.Product;
import Fifth_Project.Sale.Campaign.Management.System.Repository.CampaignProductRepository;
import Fifth_Project.Sale.Campaign.Management.System.Repository.CampaignRepository;
import Fifth_Project.Sale.Campaign.Management.System.Repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class CampaignScheduler {

    @Autowired
    private CampaignProductRepository campaignProductRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void handleCampaigns() {
        LocalDate today = LocalDate.now();

        // Start campaigns
        List<Campaign> startingCampaigns = campaignRepository.findByStartDate(today);
        for (Campaign campaign : startingCampaigns) {

            List<CampaignProduct> products = campaign.getCampaignProducts();
            for (CampaignProduct cp : products) {
                Product product = cp.getProduct();

                // Check if campaign start is already logged in price history
                boolean startLogged = priceHistoryRepository.existsByProductIdAndReasonContaining(
                        product.getId(), "Campaign Start: " + campaign.getTitle());

                if (!startLogged) {
                    double oldPrice = product.getCurrentPrice();
                    double discount = cp.getDiscount(); // e.g. 10.0 means 10% off
                    double newPrice = oldPrice - (oldPrice * discount / 100);

                    priceHistoryRepository.save(new PriceHistory(
                            product, oldPrice, newPrice, today, "Campaign Start: " + campaign.getTitle()
                    ));
                }
            }
        }

        // End campaigns
        List<Campaign> endingCampaigns = campaignRepository.findByEndDate(today);
        for (Campaign campaign : endingCampaigns) {
            List<CampaignProduct> products = campaign.getCampaignProducts();
            for (CampaignProduct cp : products) {
                Product product = cp.getProduct();

                boolean endLogged = priceHistoryRepository.existsByProductIdAndReasonContaining(
                        product.getId(), "Campaign End: " + campaign.getTitle());

                if (!endLogged) {
                    double discountedPrice = product.getCurrentPrice();
                    double discount = cp.getDiscount();
                    double originalPrice = discountedPrice / (1 - discount / 100);

                    priceHistoryRepository.save(new PriceHistory(
                            product, discountedPrice, originalPrice, today, "Campaign End: " + campaign.getTitle()
                    ));
                }
            }
        }
    }
}