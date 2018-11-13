package jewel.domain;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.PostPersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Document(collection = "ad")
public class Ad {
    @Id
    private String id;
    @NotEmpty
    private String title;
    private String photoUrl;
    private String description;
    @NotNull
    private Boolean isActive;
    @NotNull
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime createdAt;
    @NotNull
    private Contacts contacts;
    @NotNull
    private Location location;
    @NotNull
    private Rate rate;
    @NotNull
    private Requirements requirements;
    @NotNull
    private Settings settings;

    @Data
    @Builder
    public static class Contacts {
        private String email;
        private String person;
        private String phone;
        private String userId;
        private String companyId;
    }

    @Data
    @Builder
    public static class Location {
        @NotEmpty
        private String country;
        @NotEmpty
        private String city;
    }

    @Data
    @Builder
    public static class Settings {
        @NotNull
        private Boolean isRemoved;
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private DateTime autoDeactivateAt;
    }

    @Data
    @Builder
    public static class Rate {
        @NotNull
        private Boolean isContractual;
        private Integer fixedRate;
        private Integer minRate;
        private Integer maxRate;
        private String currency;
    }

    @Data
    @Builder
    public static class Requirements {
        private String experience;
    }

    @PostPersist
    private void postPersist() {
        if (createdAt != null
                && settings != null
                && settings.autoDeactivateAt == null) {
            settings.setAutoDeactivateAt(createdAt.plusMonths(1));
        }
    }
}
