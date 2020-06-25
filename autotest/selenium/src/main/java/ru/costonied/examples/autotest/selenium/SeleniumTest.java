package ru.costonied.examples.autotest.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Example how to use Selenium for autotesting.
 *
 * @implSpec install chromedriver on your machine
 */
public class SeleniumTest {

    public static void main(String[] args) throws Exception {

        testGoogleSearch();

    }

    public static void testGoogleSearch() throws InterruptedException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        // Or you could set it via -Dwebdriver.chrome.driver for JVM arguments
        System.setProperty("webdriver.chrome.driver", "/Users/u16712687/environment/tools/web_drivers/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com/");
        Thread.sleep(2000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(2000);  // Let the user actually see something!
        driver.quit();
    }

}
