package base;

import com.codeborne.selenide.Configuration;


import drivers.MobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    @BeforeAll
    public static void setup() {
        Configuration.browser = MobileDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();
    }
}

