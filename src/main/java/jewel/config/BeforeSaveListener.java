package jewel.config;

import jewel.domain.Advert;
import jewel.repository.AdvertRepository;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class BeforeSaveListener extends AbstractMongoEventListener<Advert> {

    private final AdvertRepository advertRepository;

    public BeforeSaveListener(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<Advert> event) {
        Advert savedAdvert = event.getSource();
        Advert.Settings settings = savedAdvert.getSettings();
        if ((settings != null && settings.getAutoDeactivateAt() != null) || savedAdvert.getCreatedAt() == null) {
            return;
        }
        if (settings == null) {
            settings = Advert.Settings.builder().build();
            savedAdvert.setSettings(settings);
        }
        settings.setAutoDeactivateAt(savedAdvert.getCreatedAt().plusMonths(1));
//        advertRepository.(savedAdvert);
    }
}
