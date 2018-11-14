package jewel.domain;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.PostPersist;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;

@Data
@Builder
@Document
public class Advert {
    @Id
    private String id;
    private String title;
    private String imageUrl;
    private String description;
    private Boolean isActive;
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime createdAt;
    private Contacts contacts;
    private Location location;
    private Rate rate;
    private Requirements requirements;
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
        private String country;
        private String city;
    }

    @Data
    @Builder
    public static class Settings {
        private Boolean isRemoved;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private DateTime autoDeactivateAt;
    }

    @Data
    @Builder
    public static class Rate {
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
        if (allNotNull(createdAt, settings) && isNull(settings.autoDeactivateAt)) {
            settings.setAutoDeactivateAt(createdAt.plusMonths(1));
        }
    }
}
