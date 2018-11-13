package jewel.api.converter;

import jewel.api.model.AdModel;
import jewel.domain.Ad;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

public class AdConverter {

    private AdConverter() {
    }

    public static AdModel toModel(Ad ad) {
        return AdModel.builder()
                .title(ad.getTitle())
                .build();
    }

    public static List<AdModel> toModel(Iterable<Ad> ads) {
        return stream(ads.spliterator(), false)
                .map(AdConverter::toModel)
                .collect(Collectors.toList());
    }
}
