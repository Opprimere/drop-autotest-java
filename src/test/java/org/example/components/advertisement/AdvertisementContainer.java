package org.example.components.advertisement;

import org.example.lib.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvertisementContainer extends Component {
    public AdvertisementContainer(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[data-bulletin-list]")
    private WebElement container;

    @FindBy(css = "div[data-ftid='component_pagination']")
    private WebElement pagination;

    public Advertisement getFirstAdvertisement() {
        List<WebElement> advertisements = container.findElements(By.cssSelector("a[data-ftid='bulls-list_bull']"));
        return new Advertisement(advertisements.get(0));
    }

    public boolean checkAutoYear(int year){
        Pattern patternYear = Pattern.compile("\\d+$");

        List<WebElement> resultTitles = container.findElements(By.cssSelector("span[data-ftid='bull_title']"));
        for (WebElement resultTitle : resultTitles) {
            Matcher matcherYear = patternYear.matcher(resultTitle.getText());
            if (!matcherYear.find()) return false;
            if (Integer.parseInt(matcherYear.group()) < year) return false;
        }

        return true;
    }

    public boolean checkHasMileAge() {
        Pattern patternMileAge = Pattern.compile("\\d+ км$");

        List<WebElement> resultDescs = container.findElements((By.cssSelector("div[data-ftid='component_inline-bull-description']")));
        for (WebElement resultDesc : resultDescs) {
            boolean isMileAgeAbsence = true;
            List<WebElement> descParams = resultDesc.findElements(By.cssSelector("& > span"));
            for (WebElement descParam : descParams) {
                Matcher matcherMileAge = patternMileAge.matcher(descParam.getText());
                if (matcherMileAge.find()) isMileAgeAbsence = false;
            }
            if (isMileAgeAbsence) return false;
        }

        return true;
    }

    public boolean checkHasntSellAuto(){
        List<WebElement> resultTitles = container.findElements(By.cssSelector("a[data-ftid='bulls-list_bull'] > div:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(1)"));
        for (WebElement resultTitle : resultTitles) {
            String textDecoration = resultTitle.getCssValue("text-decoration");
            if(textDecoration.contains("line-through")) return false;
        }

        return true;
    }

    public void changePage(int num){
        List<WebElement> pages = pagination.findElements((By.cssSelector("& > div")));
        for (WebElement page : pages) {
            if (Objects.equals(page.getText(), Integer.toString(num))) {
                page.click();
                break;
            }
        }
    }

    public void eachPage(int startPage, int endPage, Runnable fn) {
        for (int page = startPage; page < endPage; page++) {
            if (page != startPage) {
                changePage(page);
            }

            fn.run();
        }
    }
}
