package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
        //Uncomment if you run the tests from testng.xml and comment if you run it from the IntelliJ runner
        browser = System.getProperty("browser", browser);
        //Comment if you run the tests from testng.xml and uncomment if you run it from the IntelliJ runner
//        if (browser == null) browser = "CHROME";
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER " + getDriver());
    }
    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER " + getDriver());
        if (result.getStatus() == ITestResult.FAILURE){
            File destinationFile = new File("screenshots"
                    + File.separator
                    + browser
                    + File.separator
                    + result.getTestClass().getRealClass().getSimpleName()
                    + "_"
                    + result.getMethod().getMethodName()
                    + ".png"
            );
//           takeScreenshot(destinationFile);
            takeScreenshotUsingAShot(destinationFile);
        }
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie :
                seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenshot(File destinationFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destinationFile);
    }

    private void takeScreenshotUsingAShot(File destinationFile){
       Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());
       try {
           ImageIO.write(screenshot.getImage(), "PNG", destinationFile);
       } catch (IOException e){
           e.printStackTrace();
       }
//In case if you face issues with getting a clear entire screen you should change from your windows display setting in the "Scale and Layout from 150% to 100%
    }
}
