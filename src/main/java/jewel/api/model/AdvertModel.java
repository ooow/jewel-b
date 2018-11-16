package jewel.api.model;

import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents Advert information.")
public class AdvertModel {
    private String id;
    @NotEmpty
    private String title;
    private String imageUrl;
    private String description;
    @NotNull
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
    @AllArgsConstructor
    @NoArgsConstructor
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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationModel {
        private String country;
        private String city;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettingsModel {
        private Long autoDeactivateAt;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequirementsModel {
        private String experience;
    }
}
