package org.example.components.header;

import org.example.lib.Component;
import org.example.pages.SignPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends Component {
    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-ftid='component_header_login']")
    private WebElement loginButton;

    public SignPage pressLoginButton() {
        loginButton.click();
        return new SignPage(driver);
    }
}
