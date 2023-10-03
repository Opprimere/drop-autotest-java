package org.example.lib;

import org.example.lib.interfaces.VirtualScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class Helper {
    public static void virtualScroll(WebDriver driver, WebElement dropdown, String itemSelector, VirtualScroll fn) throws InterruptedException {
        WebElement lastItem;
        WebElement newLastItem;

        do {
            List<WebElement> dropdownItems = dropdown.findElements(By.cssSelector(itemSelector));

            boolean _break = fn.run();
            if (_break) break;

            lastItem = dropdownItems.get(dropdownItems.size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", lastItem);
            TimeUnit.MILLISECONDS.sleep(100);
            newLastItem = dropdown.findElement(By.cssSelector(itemSelector + ":last-of-type"));
        } while (!Objects.equals(lastItem, newLastItem));
    }
}
