package s13o.test.search.index;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:SimpleIndexTest.feature"},
        plugin = "json:target/cucumber/SimpleIndexTest-report.json",
        glue = {"s13o.test.search.index"}
)
public class SimpleIndexTest {

}
