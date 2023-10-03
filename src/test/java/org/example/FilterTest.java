package org.example;

import java.time.Duration;

import org.example.pages.AutoIndexPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FilterTest {
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
    void filterAndCheck() throws InterruptedException {
        AutoIndexPage autoIndexPage = new AutoIndexPage(driver);
        autoIndexPage.openPage();

        autoIndexPage.filter.chooseBrand("Toyota");
        autoIndexPage.sleep(300);

        autoIndexPage.filter.chooseModel("Harrier");
        autoIndexPage.sleep(200);

        autoIndexPage.filter.chooseFuel("Гибрид");
        autoIndexPage.filter.chooseUnsold();
        autoIndexPage.filter.clickAdvancedSearchButton();
        autoIndexPage.filter.enterMileAgeFrom(1);
        autoIndexPage.filter.enterYearFrom(2007);
        autoIndexPage.filter.submit();

        autoIndexPage.advertisementContainer.eachPage(1,3, () -> {
            Assertions.assertTrue(autoIndexPage.advertisementContainer.checkAutoYear(2007));
            Assertions.assertTrue(autoIndexPage.advertisementContainer.checkHasMileAge());
            Assertions.assertTrue(autoIndexPage.advertisementContainer.checkHasntSellAuto());
        });
    }
}
