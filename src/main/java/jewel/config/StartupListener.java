package jewel.config;

import jewel.repository.AdvertRepository;
import jewel.repository.domain.Advert;
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
        advertRepository.save(Advert.builder()
                .title("Typical Active Ad")
                .description("Needs to investigate smth. Please look up.")
                .imageUrl("https://dogcentr.ru/wp-content/uploads/2016/10/5-300x206.jpg")
                .isActive(true)
                .contacts(Advert.Contacts.builder().companyId("123").phone("8-800-555-35-35").person("Alice").build())
                .location(Advert.Location.builder().country("CountryName").city("CityName").build())
                .rate(Advert.Rate.builder().isContractual(true).build())
                .requirements(Advert.Requirements.builder().build())
                .settings(Advert.Settings.builder().build())
                .build());
        advertRepository.save(Advert.builder()
                .title("Typical Active Advert")
                .description("Needs to investigate smth. Please look up.")
                .imageUrl("https://www.telegraph.co.uk/content/dam/women/2016/03/22/woman-postits-desk_trans_NvBQzQNjv4BqqVzuuqpFlyLIwiB6NTmJwfSVWeZ_vEN7c6bHu2jJnT8.jpg")
                .isActive(true)
                .contacts(Advert.Contacts.builder().phone("8-800-555-35-35").person("Alice").build())
                .location(Advert.Location.builder().country("CountryName").city("CityName2").build())
                .rate(Advert.Rate.builder().isContractual(false).minRate(2500).maxRate(3500).currency("PLN").build())
                .requirements(Advert.Requirements.builder().build())
                .settings(Advert.Settings.builder().build())
                .build());
        advertRepository.save(Advert.builder()
                .title("Tasdalsdadsklad")
                .description("Needs to investigate smth. Please look up.")
                .isActive(false)
                .imageUrl("https://amansquest.com/wp-content/uploads/2017/10/portrait-young-man-sitting-his-desk-hard-working-man-ss-feature.jpg")
                .contacts(Advert.Contacts.builder().phone("8-800-555-35-35").person("Alice").build())
                .location(Advert.Location.builder().country("CountryNam2").city("CityName123").build())
                .rate(Advert.Rate.builder().isContractual(false).currency("USD").fixedRate(7500).build())
                .requirements(Advert.Requirements.builder().build())
                .settings(Advert.Settings.builder().build())
                .build());
        log.info("Ads added: " + advertRepository.count());
    }
}
