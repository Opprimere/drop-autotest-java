package org.example.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Component {
    protected WebDriver driver;

    public Component(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
