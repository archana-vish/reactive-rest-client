package ac.av8242n.restclient2.services;

import ac.av8242n.restclient2.json.JokeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    private Logger log = LoggerFactory.getLogger(JokeService.class);

    private RestTemplate template;
    private static final String BASE = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";

    @Autowired
    public JokeService(RestTemplateBuilder builder) {
        this.template = builder.build();
    }

    public String getJokeSync(String first, String last) {
        String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
        String joke = template.getForObject(url, JokeResponse.class).getValue().getJoke();
        log.info(joke);
        return  joke;
    }




}
