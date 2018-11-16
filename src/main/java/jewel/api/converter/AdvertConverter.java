package jewel.api.converter;

import jewel.api.model.AdvertModel;
import jewel.repository.domain.Advert;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.List;

import static java.util.Objects.isNull;
import static org.jooq.lambda.Seq.seq;

public class AdvertConverter {

    private AdvertConverter() {
    }

    public static AdvertModel toModel(Advert advert) {
        return AdvertModel.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .imageUrl(advert.getImageUrl())
                .isActive(advert.getIsActive())
                .createdAt((isNull(advert.getCreatedAt()) ? null :
                        advert.getCreatedAt().getMillis()))
                .contacts(toModel(advert.getContacts()))
                .location(toModel(advert.getLocation()))
                .rate(toModel(advert.getRate()))
                .requirements(toModel(advert.getRequirements()))
                .settings(toModel(advert.getSettings()))
                .build();
    }

    private static AdvertModel.ContactsModel toModel(Advert.Contacts contacts) {
        if (isNull(contacts)) {
            return AdvertModel.ContactsModel.builder().build();
        }
        return AdvertModel.ContactsModel.builder()
                .userId(contacts.getUserId())
                .companyId(contacts.getCompanyId())
                .email(contacts.getEmail())
                .person(contacts.getPerson())
                .phone(contacts.getPhone())
                .build();
    }

    private static AdvertModel.LocationModel toModel(Advert.Location location) {
        if (isNull(location)) {
            return AdvertModel.LocationModel.builder().build();
        }
        return AdvertModel.LocationModel.builder()
                .country(location.getCountry())
                .city(location.getCity())
                .build();
    }

    private static AdvertModel.RateModel toModel(Advert.Rate rate) {
        if (isNull(rate)) {
            return AdvertModel.RateModel.builder().build();
        }
        return AdvertModel.RateModel.builder()
                .isContractual(rate.getIsContractual())
                .fixedRate(rate.getFixedRate())
                .maxRate(rate.getMaxRate())
                .minRate(rate.getMinRate())
                .currency(rate.getCurrency())
                .build();
    }

    private static AdvertModel.RequirementsModel toModel(Advert.Requirements requirements) {
        if (isNull(requirements)) {
            return AdvertModel.RequirementsModel.builder().build();
        }
        return AdvertModel.RequirementsModel.builder()
                .experience(requirements.getExperience())
                .build();
    }

    private static AdvertModel.SettingsModel toModel(Advert.Settings settings) {
        if (isNull(settings)) {
            return AdvertModel.SettingsModel.builder().build();
        }
        return AdvertModel.SettingsModel.builder()
                .autoDeactivateAt(isNull(settings.getAutoDeactivateAt()) ? null :
                        settings.getAutoDeactivateAt().getMillis())
                .build();
    }

    public static List<AdvertModel> toModel(Iterable<Advert> adverts) {
        return seq(adverts)
                .map(AdvertConverter::toModel)
                .toList();
    }

    public static Advert toDomain(AdvertModel advertModel) {
        return Advert.builder()
                .id(advertModel.getId())
                .title(advertModel.getTitle())
                .description(advertModel.getDescription())
                .imageUrl(advertModel.getImageUrl())
                .isActive(advertModel.getIsActive())
//                .createdAt((isNull(advertModel.getCreatedAt()) ? null :
//                        new DateTime(advertModel.getCreatedAt(), DateTimeZone.UTC)))
                .contacts(toDomain(advertModel.getContacts()))
                .location(toDomain(advertModel.getLocation()))
                .rate(toDomain(advertModel.getRate()))
                .requirements(toDomain(advertModel.getRequirements()))
                .settings(toDomain(advertModel.getSettings()))
                .build();
    }

    private static Advert.Contacts toDomain(AdvertModel.ContactsModel contacts) {
        if (isNull(contacts)) {
            return Advert.Contacts.builder().build();
        }
        return Advert.Contacts.builder()
                .userId(contacts.getUserId())
                .companyId(contacts.getCompanyId())
                .email(contacts.getEmail())
                .person(contacts.getPerson())
                .phone(contacts.getPhone())
                .build();
    }

    private static Advert.Location toDomain(AdvertModel.LocationModel location) {
        if (isNull(location)) {
            return Advert.Location.builder().build();
        }
        return Advert.Location.builder()
                .country(location.getCountry())
                .city(location.getCity())
                .build();
    }

    private static Advert.Rate toDomain(AdvertModel.RateModel rate) {
        if (isNull(rate)) {
            return Advert.Rate.builder().build();
        }
        return Advert.Rate.builder()
                .isContractual(rate.getIsContractual())
                .fixedRate(rate.getFixedRate())
                .maxRate(rate.getMaxRate())
                .minRate(rate.getMinRate())
                .currency(rate.getCurrency())
                .build();
    }

    private static Advert.Requirements toDomain(AdvertModel.RequirementsModel requirements) {
        if (isNull(requirements)) {
            return Advert.Requirements.builder().build();
        }
        return Advert.Requirements.builder()
                .experience(requirements.getExperience())
                .build();
    }

    private static Advert.Settings toDomain(AdvertModel.SettingsModel settings) {
        if (isNull(settings)) {
            return Advert.Settings.builder().build();
        }
        return Advert.Settings.builder()
//                .autoDeactivateAt(isNull(settings.getAutoDeactivateAt()) ? null :
//                        new DateTime(settings.getAutoDeactivateAt(), DateTimeZone.UTC))
                .build();
    }
}
