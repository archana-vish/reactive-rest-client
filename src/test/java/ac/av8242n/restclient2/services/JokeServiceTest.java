package ac.av8242n.restclient2.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JokeServiceTest {

    @Autowired
    JokeService jokeService;

    @Test
    public void testJoke() {
        String joke = jokeService.getJokeSync("Chuck", "Norris");
        assertTrue(joke.contains("Chuck") || joke.contains("Norris"));
    }
}