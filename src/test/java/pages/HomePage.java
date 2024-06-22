package pages;

import utilities.BrowserUtils;
import utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class HomePage extends BasePage {
    Actions actions = new Actions(Driver.getDriver());
    @FindBy(xpath = "//a[.='Sisteme Giriş Yap']")
    public WebElement entrySystem_loc;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement userName_loc;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement userPassword_loc;

    @FindBy(xpath = "//button[@id='login_btn']")
    public WebElement loginBtn_loc;

    @FindBy(xpath = "//a[.='Kurumsal ']")
    public WebElement kurumsal_loc;

    @FindBy(xpath = "//a[.='Ürünler ']")
    public WebElement urunler_loc;

    @FindBy(xpath = "//a[.='Çözümler ']")
    public WebElement cozumler_loc;

    @FindBy(xpath = "//a[.='İletişim ']")
    public WebElement iletisim_loc;

    @FindBy(xpath = "//a[.='Kampanyalar ']")
    public WebElement kampanyalar_loc;

    @FindBy(xpath = "//a[.='Araç Takip Kampanyası']")
    public WebElement aracTakipKampanya_loc;

    @FindBy(css = "#type-2")
    public WebElement mesaiSaatiBtn_loc;

    @FindBy(css = ".btn.btn-second.w-100")
    public WebElement gonderBtn_loc;

    public void hover(WebElement element, long pause) {
        new Actions(Driver.getDriver()).moveToElement(element).pause(pause).perform();
    }

    public void runOverMenu() {
        List<WebElement> mainMenus = Driver.getDriver().findElements(By.cssSelector(".nav-menu>li"));
        for (WebElement mainMenu : mainMenus) {
            hover(mainMenu,500);
        }
    }

    public void popUpMtd() {
        WebElement popUp = Driver.getDriver().findElement(By.xpath("//span[@aria-hidden='true']"));
        popUp.click();
    }

    public void n2general() {
        runOverMenu();
        BrowserUtils.waitFor(1);
        actions.moveToElement(kampanyalar_loc).perform();
        BrowserUtils.waitFor(1);
        aracTakipKampanya_loc.click();
        BrowserUtils.waitFor(1);
        WebElement adSoyad = Driver.getDriver().findElement(By.id("exe2"));
        actions.sendKeys(adSoyad, "Test")
                .sendKeys(Keys.TAB)
                .sendKeys("123")
                .sendKeys(Keys.TAB)
                .sendKeys("test@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("test amaçlı").perform();
        mesaiSaatiBtn_loc.click();
        gonderBtn_loc.click();
        BrowserUtils.waitFor(1);
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//span[@class='icon-ok']")).isEnabled());
        String expectedUrl = "https://n2mobil.com.tr/kampanyalar/tesekkurler";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        System.out.println("actualUrl = " + actualUrl);
    }

    public void loginUser() {
        entrySystem_loc.click();
        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
        for (String tab : windowHandles) {
            Driver.getDriver().switchTo().window(tab);
            if (Driver.getDriver().getTitle().equals("New Window")) {
                break;
            }
        }
        BrowserUtils.waitFor(1);
        userName_loc.sendKeys("");
        userPassword_loc.sendKeys("");
        loginBtn_loc.click();
        BrowserUtils.waitFor(20);
    }
}
