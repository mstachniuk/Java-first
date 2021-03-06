package org.example.googleSearch;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainResult {

    public static void displayGoogleResultTest(String googleText) throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless",
                "--disable-gpu",
                "--window-size=1366,1200",
                "--ignore-certificate-errors",
                "--silent");

        // download compatible chromedriver to Chrome version
        // https://chromedriver.chromium.org/downloads
        System.setProperty("webdriver.gecko.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver(options);

        driver.get("http://google.com");

        driver.findElement(By.name("q")).sendKeys(googleText);

        WebElement element = driver.findElement(By.name("btnK"));
//        driver.switchTo().frame(element);

        element.submit();

        // wait until element exist
        WebDriverWait wait = new WebDriverWait(driver, 30);
        By addItem = By.xpath("//div[@class='rc']");
        wait.until(ExpectedConditions.presenceOfElementLocated(addItem));

//        make screenshoot
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File out = new File("screen.png");
//        FileUtils.copyFile(screenshot, out);

        // what to find
        By descriptionTextXPath = By.xpath("//*[@class='r']//a//h3");
        List<WebElement> elements = driver.findElements(descriptionTextXPath);

        // display result as aLink or h3 text
        for (WebElement webElement : elements) {
            // for //a
            // System.out.println("Google elements: " + webElement.getAttribute("href"));
            // for //a//h3
            System.out.println("Google elements: " + webElement.getText());
        }
        driver.close();
    }
}

