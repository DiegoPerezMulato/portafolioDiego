import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchSteps {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    private void pause(long mils)
    {
        try {
            Thread.sleep(mils);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the user is in the index page")
    public void theUserIsInIndexPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        driver.get("http://automationpractice.com/index.php");

    }

    @When("the user enters dresses in the search bar")
    public void userEntersDressesInTheSearchBar() {
        pause(5000);
        driver.findElement(By.id("search_query_top")).sendKeys("dresses");
    }

    @When("the user clicks the search button")
    public void userClicksTheSearchButton() {
        pause(5000);
        driver.findElement(By.name("submit_search")).click();
    }

    @Then("the dresses page appears")
    public void dressesPageAppears() {
        pause(5000);


        assertThat(driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/h1/span[1]")).getText(), is("\"DRESSES\""));
        pause(500);

        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}