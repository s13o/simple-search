package s13o.test.search.index;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:SimpleTokFactoryTest.feature"},
        plugin = "json:target/cucumber/SimpleTokFactoryTest-report.json",
        glue = {"s13o.test.search.index"}
)
public class SimpleTokFactoryTest {

}
