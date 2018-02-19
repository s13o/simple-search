package s13o.test.search.index;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import lombok.Data;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import s13o.test.search.index.api.DocRef;
import s13o.test.search.index.api.Token;
import s13o.test.search.index.api.Vocabulary;
import s13o.test.search.index.simple.SimpleConfig;
import s13o.test.search.index.simple.SimpleDocRef;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@ContextConfiguration(classes = {SimpleConfig.class})
public class SimpleVocabularyTestSteps {

    @Autowired
    private Vocabulary vocabulary;

    @Given("^set of tokens to be added to Vocabulary$")
    public void given(List<TestData> data){
        data.forEach(
                (d) -> {
                    vocabulary.add(
                            new SimpleDocRef(null, d.getDocIndex(), null),
                            Stream.of(new Token(0, d.getToken())));
                }
        );
    }

    @Then("^with token \"(.*)\" we have found such documents$")
    public void then(String token, List<Integer> expected){
        List<Integer> actual =  vocabulary.get(token).map(DocRef::getId).sorted().collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
    }

    /**
     * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
     * @since 2/18/2018
     */
    @Data
    public static class TestData {
        final int docIndex;
        final String token;
    }

}
