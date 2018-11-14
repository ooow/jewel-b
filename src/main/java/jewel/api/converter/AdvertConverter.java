package jewel.api.converter;

import jewel.api.model.AdvertModel;
import jewel.domain.Advert;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

public class AdvertConverter {

    private AdvertConverter() {
    }

    public static AdvertModel toModel(Advert advert) {
        return AdvertModel.builder()
                .title(advert.getTitle())
                .build();
    }

    public static List<AdvertModel> toModel(Iterable<Advert> adverts) {
        return stream(adverts.spliterator(), false)
                .map(AdvertConverter::toModel)
                .collect(Collectors.toList());
    }
}
