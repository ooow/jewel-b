package jewel.api.controller;

import jewel.JobApplication;
import jewel.api.model.AdvertModel;
import jewel.config.MongoConfiguration;
import jewel.repository.AdvertRepository;
import jewel.repository.domain.Advert;
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

@DataMongoTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JobApplication.class, AdvertController.class, MongoConfiguration.class})
public class AdvertControllerTest {

    private static final String TITLE1 = "Title1";
    private static final String TITLE2 = "Title2";
    private static final String TITLE3 = "Title3";

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
        assertThat(result.get(0).getTitle()).isEqualTo(TITLE3);
        assertThat(result.get(1).getTitle()).isEqualTo(TITLE2);
        assertThat(result.get(2).getTitle()).isEqualTo(TITLE1);
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
        assertThat(result.get(0).getTitle()).isEqualTo(TITLE3);
        assertThat(result.get(1).getTitle()).isEqualTo(TITLE2);
    }
}
