package s13o.test.search.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/19/2018
 */
@SpringBootApplication(
        scanBasePackages = {"s13o.test.search", "s13o.test.search.server"}
)
public class SearchServer {
    public static void main(String[] args) {
        SpringApplication.run(SearchServer.class, args);
    }

}
