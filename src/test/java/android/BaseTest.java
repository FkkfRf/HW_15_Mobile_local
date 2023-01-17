package android;

import com.codeborne.selenide.Configuration;
import config.MobileConfig;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class);
        String caseEmulate = mobileConfig.deviceEmulate();

        switch (caseEmulate) {
            case ("browserstack"):
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case ("real"):
                break;
            default:
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
        }
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

