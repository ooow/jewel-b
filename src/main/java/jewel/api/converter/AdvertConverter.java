package jewel.api.converter;

import jewel.api.model.AdvertModel;
import jewel.domain.Advert;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.stream.StreamSupport.stream;

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
                .isRemoved(settings.getIsRemoved())
                .build();
    }

    public static List<AdvertModel> toModel(Iterable<Advert> adverts) {
        return stream(adverts.spliterator(), false)
                .map(AdvertConverter::toModel)
                .collect(Collectors.toList());
    }
}
