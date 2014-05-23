package com.challenge.mvc.dao;

import com.challenge.mvc.dao.impl.mongo.UserDAO;
import com.challenge.mvc.entities.User;
import com.mongodb.Mongo;
import org.junit.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.AbstractTest;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ITestUserDAO extends AbstractTest {

    private static final String LOCALHOST = "127.0.0.1";
    private static final String DB_NAME = "user_test";
    private static final int MONGO_TEST_PORT = 27028;

    private static MongodProcess mongoProcess;
    private static Mongo mongo;
    private MongoTemplate template;
    private UserDAO dao;

    @BeforeClass
    public static void initializeDB() throws IOException {

        RuntimeConfig config = new RuntimeConfig();
        config.setExecutableNaming(new UserTempNaming());

        MongodStarter starter = MongodStarter.getInstance(config);

        MongodExecutable mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, MONGO_TEST_PORT, false));
        mongoProcess = mongoExecutable.start();

        mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);
        mongo.getDB(DB_NAME);
    }

    @AfterClass
    public static void shutdownDB() throws InterruptedException {
        mongo.close();
        mongoProcess.stop();
    }

    @Before
    @Override
    public void setup() {
        dao = new UserDAO();
        template = new MongoTemplate(mongo, DB_NAME);
        dao.setMongoTemplate(template);
    }

    @After
    public void tearDown() throws Exception {
        template.dropCollection(User.class);
    }

    @Test
    public void saveAndFindAllTest() {
        User user = new User("furkan1", "Kaynak", "05001112233");
        User user2 = new User("furkan2", "Kaynak", "05001112233");
        User user3 = new User("furkan3", "Kaynak", "05001112233");

        template.save(user);
        template.save(user2);
        template.save(user3);

        int expectedSize = 3;
        int size = template.findAll(User.class).size();

        assertEquals(expectedSize, size);
    }

    @Test
    public void deleteTest() {
        User user = new User("furkan1", "Kaynak", "05001112233");
        User user2 = new User("furkan2", "Kaynak", "05001112233");
        User user3 = new User("furkan3", "Kaynak", "05001112233");

        template.save(user);
        template.save(user2);
        template.save(user3);

        int expectedSize = 3;
        List<User> users = template.findAll(User.class);

        System.out.println(users);

        assertEquals(expectedSize, users.size());

        template.remove(new Query(Criteria.where("id").is(users.get(0).getId())), User.class);
        template.remove(new Query(Criteria.where("id").is(users.get(1).getId())), User.class);

        int expectedSize2 = 1;
        List<User> users2 = template.findAll(User.class);

        assertEquals(1, users2.size());
    }

    @Test
    public void findByIdTest() {
        String id = "a123b456";

        User user = new User(id, "furkan1", "Kaynak", "05001112233");
        template.save(user);

        User userById = template.findById(id, User.class);

        assertEquals(user, userById);
        assertEquals(id, userById.getId());
    }

}
