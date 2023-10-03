package org.example.pages;

import org.example.components.advertisement.AdvertisementContainer;
import org.example.components.filter.Filter;
import org.example.components.header.Header;
import org.example.components.region.RegionForm;
import org.example.lib.Page;
import org.openqa.selenium.WebDriver;

public class AutoIndexPage extends Page {
    public static String page = "https://auto.drom.ru/";

    public void openPage() {
        driver.get(page);
    }

    public AutoIndexPage(WebDriver driver) {
        super(driver);
        
        filter = new Filter(driver);
        advertisementContainer = new AdvertisementContainer(driver);
        header = new Header(driver);
        regionForm = new RegionForm(driver);
    }

    public Filter filter;
    
    public AdvertisementContainer advertisementContainer;

    public Header header;

    public RegionForm regionForm;
}
