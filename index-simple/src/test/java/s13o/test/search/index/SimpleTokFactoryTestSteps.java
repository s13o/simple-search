package s13o.test.search.index;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import s13o.test.search.index.api.Document;
import s13o.test.search.index.api.TokFactory;
import s13o.test.search.index.api.Token;
import s13o.test.search.index.simple.PlainTextDocument;
import s13o.test.search.index.simple.SimpleConfig;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@ContextConfiguration(classes = {SimpleConfig.class})
public class SimpleTokFactoryTestSteps {

    @Autowired
    private TokFactory factory;

    private String content;

    @Given("^text content is \"(.*)\"$")
    public void given(String document) {
        this.content = document;
    }

    @Then("^we have another tokens$")
    public void Then(List<Token> expected) throws Exception {
        Document doc = new PlainTextDocument("any", content);
        List<Token> actual = factory.create(doc).tokensOf(doc)./*sorted().*/collect(Collectors.toList());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

}
