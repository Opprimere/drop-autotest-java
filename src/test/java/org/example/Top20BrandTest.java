package org.example;

import org.example.pages.AutoIndexPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.scripts.Top20Brand;

import java.time.Duration;
import java.util.HashSet;

public class Top20BrandTest {
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
    void getTableTop20Brands() throws InterruptedException {
        AutoIndexPage autoIndexPage = new AutoIndexPage(driver);
        autoIndexPage.openPage();

        autoIndexPage.filter.clickAnotherCityButton();
        autoIndexPage.regionForm.enterSearchField("Приморский край");
        autoIndexPage.regionForm.pressEnterSearchField();

        HashSet<String> brandList = autoIndexPage.filter.getBrandList();
        Top20Brand top20Brand = new Top20Brand(brandList);
        top20Brand.createTable();
    }
}
