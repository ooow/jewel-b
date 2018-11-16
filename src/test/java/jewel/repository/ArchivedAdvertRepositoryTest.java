package jewel.repository;

import jewel.JobApplication;
import jewel.config.MongoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JobApplication.class, MongoConfiguration.class})
public class ArchivedAdvertRepositoryTest {

    @Autowired
    private ArchivedAdvertRepository underTest;

    @Test
    public void findAll_empty() {
        assertThat(underTest.findAll()).isEmpty();
    }
}
