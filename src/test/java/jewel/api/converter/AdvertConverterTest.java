package jewel.api.converter;

import jewel.api.model.AdvertModel;
import jewel.repository.domain.Advert;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdvertConverterTest {

    private static final String ID = "123knjoidnoi12";
    private static final String TITLE1 = "Title1";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGE_URL = "http://source/image.png";
    private static final Boolean IS_ACTIVE = Boolean.FALSE;
    private static final DateTime CREATED_DATE = new DateTime();
    // rate
    private static final Boolean IS_CONTRACTUAL = Boolean.FALSE;
    private static final String CURRENCY = "USD";
    private static final Integer FIXED_RATE = 123123;
    private static final Integer MAX_RATE = Integer.MAX_VALUE;
    private static final Integer MIN_RATE = 0;
    // location
    private static final String COUNTRY = "Country";
    private static final String CITY = "City";
    // requirements
    private static final String EXP = "expirience";
    // contact
    private static final String PERSON = "Person";
    private static final String EMAIL = "asdasd@mail.net";
    private static final String COMPANY_ID = "company123";
    private static final String USER_ID = "asdasdUser";
    private static final String PHONE = "+498-450-6712";

    @Test
    public void toModel_fullEntity() {
        Advert advert = Advert.builder()
                .id(ID)
                .title(TITLE1)
                .description(DESCRIPTION)
                .isActive(IS_ACTIVE)
                .createdAt(CREATED_DATE)
                .imageUrl(IMAGE_URL)
                .rate(Advert.Rate.builder()
                        .isContractual(IS_CONTRACTUAL)
                        .fixedRate(FIXED_RATE)
                        .maxRate(MAX_RATE)
                        .minRate(MIN_RATE)
                        .currency(CURRENCY)
                        .build())
                .location(Advert.Location.builder()
                        .country(COUNTRY)
                        .city(CITY)
                        .build())
                .requirements(Advert.Requirements.builder()
                        .experience(EXP)
                        .build())
                .contacts(Advert.Contacts.builder()
                        .email(EMAIL)
                        .companyId(COMPANY_ID)
                        .phone(PHONE)
                        .userId(USER_ID)
                        .person(PERSON)
                        .build())
                .settings(Advert.Settings.builder()
                        .build())
                .build();

        AdvertModel advertModel = AdvertConverter.toModel(advert);

        assertThat(advertModel).isNotNull();
        assertThat(advertModel.getId()).isEqualTo(ID);
        assertThat(advertModel.getTitle()).isEqualTo(TITLE1);
        assertThat(advertModel.getImageUrl()).isEqualTo(IMAGE_URL);
        assertThat(advertModel.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(advertModel.getIsActive()).isEqualTo(IS_ACTIVE);
        assertThat(advertModel.getCreatedAt()).isEqualTo(CREATED_DATE.getMillis());
        // rate
        assertThat(advertModel.getRate()).isNotNull();
        assertThat(advertModel.getRate().getIsContractual()).isEqualTo(IS_CONTRACTUAL);
        assertThat(advertModel.getRate().getCurrency()).isEqualTo(CURRENCY);
        assertThat(advertModel.getRate().getFixedRate()).isEqualTo(FIXED_RATE);
        assertThat(advertModel.getRate().getMaxRate()).isEqualTo(MAX_RATE);
        assertThat(advertModel.getRate().getMinRate()).isEqualTo(MIN_RATE);
        // location
        assertThat(advertModel.getLocation()).isNotNull();
        assertThat(advertModel.getLocation().getCountry()).isEqualTo(COUNTRY);
        assertThat(advertModel.getLocation().getCity()).isEqualTo(CITY);
        // requirements
        assertThat(advertModel.getRequirements()).isNotNull();
        assertThat(advertModel.getRequirements().getExperience()).isEqualTo(EXP);
        // contacts
        assertThat(advertModel.getContacts()).isNotNull();
        assertThat(advertModel.getContacts().getCompanyId()).isEqualTo(COMPANY_ID);
        assertThat(advertModel.getContacts().getEmail()).isEqualTo(EMAIL);
        assertThat(advertModel.getContacts().getPerson()).isEqualTo(PERSON);
        assertThat(advertModel.getContacts().getPhone()).isEqualTo(PHONE);
        assertThat(advertModel.getContacts().getUserId()).isEqualTo(USER_ID);
        // settings
        assertThat(advertModel.getSettings()).isNotNull();
    }

    @Test
    public void toDomain_fullEntity() {
        AdvertModel advertModel = AdvertModel.builder()
                .id(ID)
                .title(TITLE1)
                .description(DESCRIPTION)
                .isActive(IS_ACTIVE)
                .createdAt(CREATED_DATE.getMillis())
                .imageUrl(IMAGE_URL)
                .rate(AdvertModel.RateModel.builder()
                        .isContractual(IS_CONTRACTUAL)
                        .fixedRate(FIXED_RATE)
                        .maxRate(MAX_RATE)
                        .minRate(MIN_RATE)
                        .currency(CURRENCY)
                        .build())
                .location(AdvertModel.LocationModel.builder()
                        .country(COUNTRY)
                        .city(CITY)
                        .build())
                .requirements(AdvertModel.RequirementsModel.builder()
                        .experience(EXP)
                        .build())
                .contacts(AdvertModel.ContactsModel.builder()
                        .email(EMAIL)
                        .companyId(COMPANY_ID)
                        .phone(PHONE)
                        .userId(USER_ID)
                        .person(PERSON)
                        .build())
                .settings(AdvertModel.SettingsModel.builder()
                        .build())
                .build();

        Advert advert = AdvertConverter.toDomain(advertModel);

        assertThat(advert).isNotNull();
        assertThat(advert.getId()).isEqualTo(ID);
        assertThat(advert.getTitle()).isEqualTo(TITLE1);
        assertThat(advert.getImageUrl()).isEqualTo(IMAGE_URL);
        assertThat(advert.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(advert.getIsActive()).isEqualTo(IS_ACTIVE);
        // rate
        assertThat(advert.getRate()).isNotNull();
        assertThat(advert.getRate().getIsContractual()).isEqualTo(IS_CONTRACTUAL);
        assertThat(advert.getRate().getCurrency()).isEqualTo(CURRENCY);
        assertThat(advert.getRate().getFixedRate()).isEqualTo(FIXED_RATE);
        assertThat(advert.getRate().getMaxRate()).isEqualTo(MAX_RATE);
        assertThat(advert.getRate().getMinRate()).isEqualTo(MIN_RATE);
        // location
        assertThat(advert.getLocation()).isNotNull();
        assertThat(advert.getLocation().getCountry()).isEqualTo(COUNTRY);
        assertThat(advert.getLocation().getCity()).isEqualTo(CITY);
        // requirements
        assertThat(advert.getRequirements()).isNotNull();
        assertThat(advert.getRequirements().getExperience()).isEqualTo(EXP);
        // contacts
        assertThat(advert.getContacts()).isNotNull();
        assertThat(advert.getContacts().getCompanyId()).isEqualTo(COMPANY_ID);
        assertThat(advert.getContacts().getEmail()).isEqualTo(EMAIL);
        assertThat(advert.getContacts().getPerson()).isEqualTo(PERSON);
        assertThat(advert.getContacts().getPhone()).isEqualTo(PHONE);
        assertThat(advert.getContacts().getUserId()).isEqualTo(USER_ID);
        // settings
        assertThat(advert.getSettings()).isNotNull();
    }
}
