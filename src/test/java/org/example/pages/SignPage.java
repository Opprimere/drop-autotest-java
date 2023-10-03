package org.example.pages;

import org.example.components.login.LoginForm;
import org.example.lib.Page;
import org.openqa.selenium.WebDriver;

public class SignPage extends Page {
    public static String page = "https://my.drom.ru/sign";

    public void openPage() {
        driver.get(page);
    }

    public SignPage(WebDriver driver) {
        super(driver);

        loginForm = new LoginForm(driver);
    }

    public LoginForm loginForm;
}
