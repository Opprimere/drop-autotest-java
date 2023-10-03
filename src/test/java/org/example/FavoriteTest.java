package org.example;

import org.example.pages.AutoIndexPage;
import org.example.pages.SignPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FavoriteTest {
    private static WebDriver driver;

    @BeforeAll
    public static void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

//    @AfterAll
//    public static void destroy() {
//        driver.quit();
//    }

    @Test
    void loginAndAddFavoriteCar() {
        AutoIndexPage autoIndexPage = new AutoIndexPage(driver);
        autoIndexPage.openPage();

        SignPage signPage = autoIndexPage.header.pressLoginButton();

        signPage.loginForm.enterLogin("89963816228");
        signPage.loginForm.enterPassword("qwer1234");
        signPage.loginForm.clickSubmitButton();

        autoIndexPage.advertisementContainer.getFirstAdvertisement().addToFavorite();
    }
}
