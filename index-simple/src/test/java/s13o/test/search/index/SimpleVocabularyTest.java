package s13o.test.search.index;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lombok.Data;
import org.junit.runner.RunWith;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:SimpleVocabularyTest.feature"},
        plugin = "json:target/cucumber/SimpleVocabularyTest-report.json",
        glue = {"s13o.test.search.index"}
)
public class SimpleVocabularyTest {

}
