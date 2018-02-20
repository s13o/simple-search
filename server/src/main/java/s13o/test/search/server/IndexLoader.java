package s13o.test.search.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import s13o.test.search.index.api.Index;
import s13o.test.search.index.simple.PlainTextDocument;

/**
 * @author {@link "mailto:roman.solodovnichenko@playtech.com" "romanso"}
 * @since 2/20/2018
 */
@Component
public class IndexLoader implements CommandLineRunner {
    private Index index;

    public IndexLoader(@Autowired Index index) {
        this.index = index;
    }

    @Override
    public void run(String... args) throws Exception {
/*
      | mail1   | How many cookies could a good cook cook |
      | mail2   | If a good cook could cook cookies?      |
      | mail3   | A good cook could cook as many cookies  |
      | mail4   | As a good cook who could cook cookies   |
*/
        index.add(new PlainTextDocument("mail1", "How many cookies could a good cook cook"));
        index.add(new PlainTextDocument("mail2", "If a good cook could cook cookies?"));
        index.add(new PlainTextDocument("mail3", "A good cook could cook as many cookies "));
        index.add(new PlainTextDocument("mail4", "As a good cook who could cook cookies"));

    }
}
