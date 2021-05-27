package blazedome;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestSteps {
    private WebDriver driver;
    static String TestString;

    @Before
    public void setUp() {

        // ChromeDriver path on development machine, which is Windows
        String OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32/chromedriver.exe").toString());

        }

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver!=null) {
            driver.close();
            driver.quit();
        }
    }

    @Given("Navigate to Page Blazedemo")
    public void navigateToPageBlazedemo() {
        driver.navigate().to("https://blazedemo.com/");
    }

    @When("I select departure destination")
    public void selectFromDestination() {
       WebElement w =  driver.findElement(By.name("fromPort")).selectByValue("Paris");
    }

    @And("I select arrival destination")
    public void selectToDestination() {
        driver.findElement(By.name("toPort")).selectByValue("New York");
    }

    @And("I click on Find Flights button")
    public void aUserClicksOnFindButton() {
        driver.findElement(By.xpath("//input[@Value='Find Flights']")).click();
}

    @And("I choose the Airlines")
    public void chooseAirlines() {
        driver.findElement(By.xpath("//table//td[text()='United Airlines']//parent::tr//input[@value='Choose This Flight']")).click();
    }

    @When("I enter all the required information")
    public void enterDetails() {
        driver.findElement(By.xpath("//input[@name='inputName']")).sendKeys("Suman");
        driver.findElement(By.xpath("//input[@name='address']")).sendKeys("1st Main, Bangalore");
    }

    @And("I enter payment details")
    public void enterPaymentDetails() {
        driver.findElement(By.xpath("//select[@name='cardType']")).selectByValue("Visa");
        driver.findElement(By.xpath("//input[@name='creditCardNumber']")).sendKeys("123456789012");
        driver.findElement(By.xpath("//input[@name='creditCardMonth']")).sendKeys("11");
        driver.findElement(By.xpath("//input[@name='creditCardYear']")).sendKeys("2024");
        driver.findElement(By.xpath("//input[@name='nameOnCard']")).sendKeys("Suman");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
    }


    @And("I capture the confirmation details")
    public void confirmationDetails(){
        String bookingId = driver.findElement(By.xpath("//table//tr[1]//td[1]")).getText();
        String bookingStatus = driver.findElement(By.xpath("//table//tr[2]//td[1]")).getText();
        System.out.println("Booking Id:" +bookingId);
        System.out.println("Booking Status: " +bookingStatus);
    }

    @And("I enter invalid payment details")
    public void enterPaymentDetails() {
        driver.findElement(By.xpath("//select[@name='cardType']")).selectByValue("Visa");
        driver.findElement(By.xpath("//input[@name='creditCardNumber']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@name='creditCardMonth']")).sendKeys("11");
        driver.findElement(By.xpath("//input[@name='creditCardYear']")).sendKeys("2000");
        driver.findElement(By.xpath("//input[@name='nameOnCard']")).sendKeys("Suman");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
    }

    @And("I capture the error Message")
    public void errorMessage(){
        String error = driver.findElement(By.xpath("//div[@class='error Message']")).getText();
        System.out.println("error Message: " +error);
    }




