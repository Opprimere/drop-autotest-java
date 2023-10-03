package org.example.lib;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class Page extends Component {
    public static String page;

    public Page(WebDriver driver) {
        super(driver);
    }

    public abstract void openPage();

    public void sleep(int millis) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(millis);
    }
}
