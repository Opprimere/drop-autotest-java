package org.example.components.region;

import org.example.lib.Component;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegionForm extends Component {
    public RegionForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="div[data-ga-stats-name='city_search'] input")
    private WebElement searchField;

    public void enterSearchField(String region) {
        searchField.sendKeys(region);
    }

    public void pressEnterSearchField() {
        searchField.sendKeys(Keys.ENTER);
    }
}
