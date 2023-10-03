package org.example.components.advertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Advertisement {
    public Advertisement(WebElement advertisement) {
        this.advertisement = advertisement;
    }

    private WebElement advertisement;

    public void addToFavorite() {
        WebElement favoriteButton = advertisement.findElement(By.cssSelector("& > div:nth-of-type(3) > div:nth-of-type(3)"));
        favoriteButton.click();
    }
}
