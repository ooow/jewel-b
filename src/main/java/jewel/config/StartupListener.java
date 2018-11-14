package jewel.config;

import jewel.domain.Advert;
import jewel.repository.AdvertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class StartupListener {
    private final AdvertRepository advertRepository;

    public StartupListener(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        addAds();
    }

    @Deprecated
    // TODO(gbondarenko): remove after CRUD enabled.
    private void addAds() {
        advertRepository.deleteAll();
        log.info("Try add test data.");
        if (advertRepository.count() > 0) {
            log.info("Ads are not empty. Skipped.");
            return;
        }
        advertRepository.save(Advert.builder()
                .title("Typical Active Advert")
                .description("Needs to investigate smth. Please look up.")
                .isActive(true)
                .contacts(Advert.Contacts.builder().build())
                .location(Advert.Location.builder().country("CountryName").city("CityName").build())
                .rate(Advert.Rate.builder().isContractual(true).build())
                .requirements(Advert.Requirements.builder().build())
                .settings(Advert.Settings.builder().isRemoved(false).build())
                .build());
        advertRepository.save(Advert.builder()
                .title("Typical Active Advert")
                .description("Needs to investigate smth. Please look up.")
                .isActive(true)
                .contacts(Advert.Contacts.builder().build())
                .location(Advert.Location.builder().country("CountryName").city("CityName2").build())
                .rate(Advert.Rate.builder().isContractual(false).minRate(2500).maxRate(3500).build())
                .requirements(Advert.Requirements.builder().build())
                .settings(Advert.Settings.builder().isRemoved(false).build())
                .build());
        advertRepository.save(Advert.builder()
                .title("Tasdalsdadsklad")
                .description("Needs to investigate smth. Please look up.")
                .isActive(false)
                .contacts(Advert.Contacts.builder().build())
                .location(Advert.Location.builder().country("CountryNam2").city("CityName123").build())
                .rate(Advert.Rate.builder().isContractual(false).currency("USD").fixedRate(7500).build())
                .requirements(Advert.Requirements.builder().build())
                .settings(Advert.Settings.builder().isRemoved(true).build())
                .build());
        log.info("Ads added: " + advertRepository.count());
    }
}
