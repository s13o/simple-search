package s13o.test.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import s13o.test.search.index.simple.SimpleDocStore;
import s13o.test.search.index.simple.SimpleIndex;
import s13o.test.search.index.simple.SimpleTokFactory;
import s13o.test.search.index.simple.SimpleVocabulary;
import s13o.test.search.index.api.DocStore;
import s13o.test.search.index.api.Index;
import s13o.test.search.index.api.TokFactory;
import s13o.test.search.index.api.Vocabulary;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/19/2018
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
