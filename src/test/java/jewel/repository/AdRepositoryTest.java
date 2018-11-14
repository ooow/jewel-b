package jewel.repository;

import jewel.domain.Advert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@RunWith(SpringRunner.class)
public class AdRepositoryTest {

    private static final String TITLE1 = "Title1";
    private static final String TITLE2 = "Title2";
    private static final String TITLE3 = "Title3";

    @Autowired
    private AdvertRepository underTest;

    @Before
    public void cleanUp() {
        underTest.deleteAll();
    }

    @Test
    public void findAll_empty() {
        assertThat(underTest.findAll()).isEmpty();
    }

    @Test
    public void saveAll_success() {
        underTest.saveAll(Arrays.asList(
                Advert.builder().title(TITLE1).build(),
                Advert.builder().title(TITLE2).build(),
                Advert.builder().title(TITLE3).build()
        ));

        assertThat(underTest.findAll()).hasSize(3);
    }

    @Test
    public void findAll_pagination() {
        underTest.saveAll(Arrays.asList(
                Advert.builder().title(TITLE1).build(),
                Advert.builder().title(TITLE2).build(),
                Advert.builder().title(TITLE3).build()
        ));

        List<Advert> ads = underTest.findAll(PageRequest.of(0, 2, Sort.Direction.DESC, "createdAt")).getContent();

        assertThat(ads).hasSize(2);
        assertThat(ads.get(0).getTitle()).isEqualTo(TITLE3);
        assertThat(ads.get(1).getTitle()).isEqualTo(TITLE2);
    }
}