package jewel.api.controller;

import jewel.JobApplication;
import jewel.api.converter.AdvertConverter;
import jewel.api.model.AdvertModel;
import jewel.config.MongoConfiguration;
import jewel.exception.NotFoundException;
import jewel.repository.AdvertRepository;
import jewel.repository.ArchivedAdvertRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JobApplication.class, AdvertController.class, MongoConfiguration.class})
public class AdvertControllerTest {

    private static final String TITLE1 = "Title1";
    private static final String TITLE2 = "Title2";
    private static final String TITLE3 = "Title3";
    private static final String BROKEN_ID = "123";
    private static final String TITLE_NEW = "New title";

    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private ArchivedAdvertRepository archivedAdvertRepository;
    @Autowired
    private AdvertController underTest;

    @Before
    public void cleanUp() {
        advertRepository.deleteAll();
        archivedAdvertRepository.deleteAll();
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

    @Test
    public void deleteAdvert_archived() {
        Advert advert = advertRepository.save(Advert.builder().title(TITLE1).build());

        underTest.deleteAdvert(advert.getId());

        assertThat(advertRepository.findAll()).isEmpty();
        assertThat(archivedAdvertRepository.findAll()).hasSize(1);
        assertThat(archivedAdvertRepository.findAll().get(0).getId()).isEqualTo(advert.getId());
        assertThat(archivedAdvertRepository.findAll().get(0).getTitle()).isEqualTo(TITLE1);
    }

    @Test
    public void deleteAdvert_throws() {
        assertThrows(
                NotFoundException.class,
                () -> underTest.deleteAdvert(BROKEN_ID)
        );
    }

    @Test
    public void patchAdvert() {
        Advert advert = advertRepository.save(Advert.builder().title(TITLE1).build());
        AdvertModel advertModel = AdvertConverter.toModel(advert);
        advertModel.setTitle(TITLE_NEW);

        underTest.patchAdvert(advertModel);

        assertThat(advertRepository.findAll()).hasSize(1);
        assertThat(advertRepository.findAll().get(0).getId()).isEqualTo(advert.getId());
        assertThat(advertRepository.findAll().get(0).getTitle()).isEqualTo(TITLE_NEW);
    }

    @Test
    public void patchAdvert_throws() {
        assertThrows(
                NotFoundException.class,
                () -> underTest.patchAdvert(AdvertModel.builder().id(BROKEN_ID).build())
        );
    }
}
