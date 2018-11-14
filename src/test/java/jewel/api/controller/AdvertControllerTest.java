package jewel.api.controller;

import jewel.JobApplication;
import jewel.api.model.AdvertModel;
import jewel.domain.Advert;
import jewel.repository.AdvertRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {JobApplication.class, AdvertController.class})
@DataMongoTest
@RunWith(SpringRunner.class)
public class AdvertControllerTest {

    private static final String TITLE1 = "Title1";
    private static final String TITLE2 = "Title2";
    private static final String TITLE3 = "Title3";
    private static final String DESCRIPTION = "Description";
    private static final Boolean IS_ACTIVE = Boolean.FALSE;
    private static final DateTime TIME_CREATED = new DateTime(2018, 12, 28, 0, 1);
    // rate
    private static final Boolean IS_CONTRACTUAL = Boolean.FALSE;
    private static final String CURRENCY = "USD";
    private static final Integer FIXED_RATE = 123123;
    // contact
    private static final String PERSON = "Person";
    private static final String EMAIL = "asdasd@mail.net";
    // location
    private static final String COUNTRY = "Country";
    private static final String CITY = "City";
    // requirements
    private static final String EXP = "expirience";
    // settings
    private static final Boolean IS_REMOVED = Boolean.TRUE;

    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private AdvertController underTest;

    @Before
    public void cleanUp() {
        advertRepository.deleteAll();
    }

    @Test
    public void getAdverts_noParam() {
        advertRepository.saveAll(Arrays.asList(
                Advert.builder().title(TITLE1).build(),
                Advert.builder().title(TITLE2).build(),
                Advert.builder().title(TITLE3).build()
        ));

        List<AdvertModel> result = underTest.getAdverts(null);

        assertThat(result).hasSize(3);
    }

    @Test
    public void getAdverts_sizeParam() {
        advertRepository.saveAll(Arrays.asList(
                Advert.builder().title(TITLE1).build(),
                Advert.builder().title(TITLE2).build(),
                Advert.builder().title(TITLE3).build()
        ));

        List<AdvertModel> result = underTest.getAdverts(2);

        assertThat(result).hasSize(2);
    }

    @Test
    public void getAdverts_convert() {
        advertRepository.save(Advert.builder()
                .title(TITLE1)
                .description(DESCRIPTION)
                .isActive(IS_ACTIVE)
                .createdAt(TIME_CREATED)
                .contacts(Advert.Contacts.builder()
                        .email(EMAIL)
                        .person(PERSON)
                        .build())
                .location(Advert.Location.builder()
                        .country(COUNTRY)
                        .city(CITY)
                        .build())
                .rate(Advert.Rate.builder()
                        .isContractual(IS_CONTRACTUAL)
                        .fixedRate(FIXED_RATE)
                        .currency(CURRENCY)
                        .build())
                .requirements(Advert.Requirements.builder()
                        .experience(EXP)
                        .build())
                .settings(Advert.Settings.builder()
                        .isRemoved(IS_REMOVED)
                        .autoDeactivateAt(TIME_CREATED.plusMonths(1))
                        .build())
                .build());

        List<AdvertModel> result = underTest.getAdverts(null);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(TITLE1);
        assertThat(result.get(0).getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.get(0).getCreatedAt()).isEqualTo(TIME_CREATED.getMillis());
        assertThat(result.get(0).getLocation()).isNotNull();
        assertThat(result.get(0).getContacts()).isNotNull();
        assertThat(result.get(0).getRate()).isNotNull();
        assertThat(result.get(0).getSettings()).isNotNull();
        assertThat(result.get(0).getSettings().getAutoDeactivateAt()).isEqualTo(TIME_CREATED.plusMonths(1).getMillis());
    }
}
