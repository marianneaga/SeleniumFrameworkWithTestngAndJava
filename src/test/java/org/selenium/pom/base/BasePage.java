package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;
import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected final WebDriverWait wait;
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void load(String endPoint) {
        driver.get(ConfigLoader.getInstanceMethod().getBaseUrl() + endPoint);
    }

    public void waitForOverlaysToDisappear() {
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("Overlay SIZE: " + overlays.size());
        if (overlays.size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("Overlays are Invisible: ");
        } else {
            System.out.println("Overlay not found");
        }
    }

    public WebElement getVisibilityOfElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement getElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
