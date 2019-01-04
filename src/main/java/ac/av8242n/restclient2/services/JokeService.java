package ac.av8242n.restclient2.services;

import ac.av8242n.restclient2.json.JokeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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


    public Mono<String> getJokeAsync(String first, String last) {
        WebClient client = WebClient.create("http://api.icndb.com");
        String path = "/jokes/random?limitTo=[nerdy]&firstName={first}&lastName={last}";
        return client.get()
                .uri(path, first, last)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .map(jokeResponse -> jokeResponse.getValue().getJoke());
    }




}
