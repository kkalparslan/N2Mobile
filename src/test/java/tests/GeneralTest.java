package tests;

import pages.HomePage;
import org.testng.annotations.Test;

public class GeneralTest extends TestBase {
    HomePage homePage = new HomePage();

    @Test
    public void n2GeneralTest() {
        homePage.popUpMtd();
        homePage.n2general();

    }

    @Test
    public void loginN2() {
        homePage.popUpMtd();
        homePage.loginUser();
    }
}
