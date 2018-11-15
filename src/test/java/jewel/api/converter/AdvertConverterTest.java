package jewel.api.converter;

import jewel.api.model.AdvertModel;
import jewel.domain.Advert;
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
    // settings
    private static final Boolean IS_REMOVED = Boolean.TRUE;

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
                        .isRemoved(IS_REMOVED)
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
        assertThat(advert.getSettings().getIsRemoved()).isEqualTo(IS_REMOVED);
    }
}