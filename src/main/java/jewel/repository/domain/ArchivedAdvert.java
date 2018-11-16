package jewel.repository.domain;

import lombok.Builder;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ArchivedAdvert extends Advert {

    @Builder(builderMethodName = "extendedBuilder")
    public ArchivedAdvert(String id,
                          String title,
                          String imageUrl,
                          String description,
                          Boolean isActive,
                          DateTime createdAt,
                          Rate rate,
                          Location location,
                          Requirements requirements,
                          Contacts contacts,
                          Settings settings) {
        super(id, title, imageUrl, description, isActive, createdAt, rate, location, requirements, contacts, settings);
    }

    public static ArchivedAdvert of(Advert advert) {
        return ArchivedAdvert.extendedBuilder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .createdAt(advert.getCreatedAt())
                .imageUrl(advert.getImageUrl())
                .isActive(advert.getIsActive())
                .rate(advert.getRate())
                .location(advert.getLocation())
                .contacts(advert.getContacts())
                .requirements(advert.getRequirements())
                .settings(advert.getSettings())
                .build();
    }
}
