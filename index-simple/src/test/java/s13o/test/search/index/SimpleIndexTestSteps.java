package s13o.test.search.index;

import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import s13o.test.search.index.api.DocRef;
import s13o.test.search.index.api.Document;
import s13o.test.search.index.api.Index;
import s13o.test.search.index.simple.PlainTextDocument;
import s13o.test.search.index.simple.SimpleConfig;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@ContextConfiguration(classes = {SimpleConfig.class})
public class SimpleIndexTestSteps {

    @Autowired
    private Index index;

    @Given("^set of documents$")
    public void given(List<PlainTextDocument> data) throws Exception {
        for (Document d : data)
            index.add(d);
    }

    @Given("^findAny by \"(.*)\" will find$")
    public void then(String searchWords, List<String> expected) throws Exception {
        final List<String> actual = index.findAny(searchWords).map(Document::getKeyword).sorted().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
    }

    @Given("^findAll by \"(.*)\" will find$")
    public void and(String searchWords, List<String> expected) throws Exception {
        final List<String> actual = index.findAll(searchWords).map(Document::getKeyword).sorted().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
    }

}
