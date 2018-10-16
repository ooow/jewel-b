package com.jewel.job.jobapplication.config;

import com.jewel.job.jobapplication.domain.Ad;
import com.jewel.job.jobapplication.repository.AdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class StartupListener {

    private final AdRepository adRepository;

    public StartupListener(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        addAds();
    }

    // TODO(gbondarenko): remove after CRUD enabled.
    @Deprecated
    private void addAds() {
        adRepository.deleteAll();
        log.info("Try add test data.");
        if (adRepository.count() > 0) {
            log.info("Ads are not empty. Skipped.");
            return;
        }
        adRepository.save(Ad.builder()
                .title("Typical Active Ad")
                .description("Needs to investigate smth. Please look up.")
                .isActive(true)
                .contacts(Ad.Contacts.builder().build())
                .location(Ad.Location.builder().country("CountryName").city("CityName").build())
                .rate(Ad.Rate.builder().isContractual(true).build())
                .requirements(Ad.Requirements.builder().build())
                .settings(Ad.Settings.builder().isRemoved(false).build())
                .build());
        adRepository.save(Ad.builder()
                .title("Typical Active Ad")
                .description("Needs to investigate smth. Please look up.")
                .isActive(true)
                .contacts(Ad.Contacts.builder().build())
                .location(Ad.Location.builder().country("CountryName").city("CityName2").build())
                .rate(Ad.Rate.builder().isContractual(false).minRate(2500).maxRate(3500).build())
                .requirements(Ad.Requirements.builder().build())
                .settings(Ad.Settings.builder().isRemoved(false).build())
                .build());
        adRepository.save(Ad.builder()
                .title("Tasdalsdadsklad")
                .description("Needs to investigate smth. Please look up.")
                .isActive(false)
                .contacts(Ad.Contacts.builder().build())
                .location(Ad.Location.builder().country("CountryNam2").city("CityName123").build())
                .rate(Ad.Rate.builder().isContractual(false).currency("USD").fixedRate(7500).build())
                .requirements(Ad.Requirements.builder().build())
                .settings(Ad.Settings.builder().isRemoved(true).build())
                .build());
        log.info("Ads added: " + adRepository.count());
    }
}
