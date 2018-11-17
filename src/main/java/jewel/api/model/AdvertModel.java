package jewel.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
public class AdvertModel implements Serializable {
    private String id;
    @NotEmpty
    private String title;
    private String imageUrl;
    private String description;
    @NotNull
    @ApiModelProperty("Active advert is visible for everyone.")
    private Boolean isActive;
    private Long createdAt;
    @NotNull
    private ContactsModel contacts;
    @NotNull
    private LocationModel location;
    @NotNull
    private RateModel rate;
    @NotNull
    private RequirementsModel requirements;
    @NotNull
    private SettingsModel settings;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "Contact information.")
    public static class ContactsModel implements Serializable {
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
    public static class LocationModel implements Serializable {
        @NotEmpty
        private String country;
        @NotEmpty
        private String city;
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
    public static class RequirementsModel implements Serializable {
        private String experience;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettingsModel implements Serializable {
        private Long autoDeactivateAt;
    }
}
