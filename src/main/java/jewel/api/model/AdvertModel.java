package jewel.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class AdvertModel {
    @Id
    private String id;
    private String title;
    private String imageUrl;
    private String description;
    private Boolean isActive;
    private Long createdAt;
    private ContactsModel contacts;
    private LocationModel location;
    private RateModel rate;
    private RequirementsModel requirements;
    private SettingsModel settings;

    @Data
    @Builder
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
        private Boolean isRemoved;
        private Long autoDeactivateAt;
    }

    @Data
    @Builder
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
