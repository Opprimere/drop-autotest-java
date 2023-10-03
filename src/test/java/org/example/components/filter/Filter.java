package org.example.components.filter;

import org.example.lib.Component;
import org.example.lib.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.List;

public class Filter extends Component {
    public Filter(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "form[name='filters']")
    private WebElement form;

    @FindBy(css = "form[name='filters'] div[data-ftid='sales__filter_fid']")
    private WebElement brand;

    @FindBy(css = "form[name='filters'] div[data-ftid='sales__filter_mid']")
    private WebElement model;

    @FindBy(css = "form[name='filters'] div[data-ftid='sales__filter_fuel-type']")
    private WebElement fuel;

    @FindBy(css = "form[name='filters'] label[for='sales__filter_unsold']")
    private WebElement unsold;

    @FindBy(css = "form[name='filters'] button[data-ftid='sales__filter_advanced-button']")
    private WebElement advancedSearchButton;

    @FindBy(css = "form[name='filters'] input[data-ftid='sales__filter_mileage-from']")
    private WebElement mileAgeFrom;

    @FindBy(css = "form[name='filters'] div[data-ftid='sales__filter_year-from']")
    private WebElement yearFrom;

    @FindBy(css = "form[name='filters'] button[data-ftid='sales__filter_submit-button']")
    private WebElement submitButton;

    @FindBy(css = "a[data-ftid='sales_search-location-picker_geoOverCity']")
    private WebElement anotherCityButton;

    public void chooseBrand(String brandname) throws InterruptedException {
        brand.click();
        WebElement dropdown = brand.findElement(By.cssSelector("div[data-ftid='component_select_dropdown']"));
        Helper.virtualScroll(driver, dropdown, "& > div > div", () -> {
            List<WebElement> dropdownItems = dropdown.findElements(By.cssSelector("& > div > div"));
            for (WebElement dropdownItem : dropdownItems) {
                WebElement foundModel = dropdownItem.findElement(By.cssSelector("& > div"));
                if (foundModel.getText().contains(brandname)) {
                    dropdownItem.click();
                    return true;
                }
            }
            return false;
        });
    }

    public void chooseModel(String modelname) throws InterruptedException {
        model.click();
        WebElement dropdown = model.findElement(By.cssSelector("div[data-ftid='component_select_dropdown']"));
        Helper.virtualScroll(driver, dropdown, "& > div > div", () -> {
            List<WebElement> dropdownItems = dropdown.findElements(By.cssSelector("& > div > div"));
            for (WebElement dropdownItem : dropdownItems) {
                WebElement foundModel = dropdownItem.findElement(By.cssSelector("& > div"));
                if (foundModel.getText().contains(modelname)) {
                    dropdownItem.click();
                    return true;
                }
            }
            return false;
        });
    }

    public void chooseFuel(String fueltype) {
        fuel.click();
        WebElement dropdown = fuel.findElement(By.cssSelector("div[data-ftid='component_select_dropdown']"));
        List<WebElement> dropdownItems = dropdown.findElements(By.cssSelector("& > div"));
        for (WebElement dropdownItem : dropdownItems) {
            if (dropdownItem.getText().contains(fueltype)) {
                dropdownItem.click();
                break;
            }
        }
    }

    public void chooseUnsold() {
        unsold.click();
    }

    public void clickAdvancedSearchButton() {
        advancedSearchButton.click();
    }

    public void enterMileAgeFrom(int mile) {
        mileAgeFrom.sendKeys(Integer.toString(mile));
    }

    public void enterYearFrom(int year) {
        yearFrom.click();
        WebElement dropdown = yearFrom.findElement(By.cssSelector("div[data-ftid='component_select_dropdown']"));
        List<WebElement> dropdownItems = dropdown.findElements(By.cssSelector("& > div"));
        for (WebElement dropdownItem : dropdownItems) {
            if (dropdownItem.getText().contains(Integer.toString(year))) {
                dropdownItem.click();
                break;
            }
        }
    }

    public void submit(){
        submitButton.click();
    }

    public void clickAnotherCityButton() {
        anotherCityButton.click();
    }

    public HashSet<String> getBrandList() throws InterruptedException {
        brand.click();
        WebElement dropdown = brand.findElement(By.cssSelector("div[data-ftid='component_select_dropdown']"));
        HashSet<String> brandList = new HashSet<>();
        Helper.virtualScroll(driver, dropdown, "& > div > div", () -> {
            List<WebElement> dropdownItems = dropdown.findElements(By.cssSelector("& > div > div"));
            for (WebElement dropdownItem : dropdownItems) {
                WebElement foundBrand = dropdownItem.findElement(By.cssSelector("& > div"));
                brandList.add(foundBrand.getText());
            }
            return false;
        });

        return brandList;
    }
}
