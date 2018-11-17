package jewel.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Represents Advert information.")
public class AdvertModel {
    private String id;
    private String title;
    private String imageUrl;
    private String description;
    @ApiModelProperty("Active advert is visible for everyone.")
    private Boolean isActive;
    private Long createdAt;
    private ContactsModel contacts;
    private LocationModel location;
    private RateModel rate;
    private RequirementsModel requirements;
    private SettingsModel settings;

    @Data
    @Builder
    @ApiModel(description = "Contact information.")
    public static class ContactsModel {
        private String email;
        private String person;
        private String phone;
        private String userId;
        private String companyId;
    }

    @Data
    @Builder
    public static class LocationModel {
        private String country;
        private String city;
    }

    @Data
    @Builder
    public static class SettingsModel {
        private Long autoDeactivateAt;
    }

    @Data
    @Builder
    @ApiModel(description = "Rates and currency.")
    public static class RateModel {
        private Boolean isContractual;
        private Integer fixedRate;
        private Integer minRate;
        private Integer maxRate;
        private String currency;
    }

    @Data
    @Builder
    public static class RequirementsModel {
        private String experience;
    }
}
