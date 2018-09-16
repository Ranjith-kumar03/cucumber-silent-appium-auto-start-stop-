package start;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features= ("src/test/java/features"),glue= {"implementation"},plugin= {"pretty","html:target/cucumber"})

public class runner extends AbstractTestNGCucumberTests{

}
