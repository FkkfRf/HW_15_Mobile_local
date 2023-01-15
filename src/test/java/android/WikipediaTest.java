package android;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikipediaTest extends BaseTest {
    @Test
    void successSearchTest() {

        step("Проверить заголовок на главном экране", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Перейти на экран добавления языка", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/section_header_text")).shouldHave(text("Your languages"));
        });
        step("Нажать опцию +ADD LANGUAGE", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).findBy(text("ADD LANGUAGE")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/section_header_text")).shouldHave(text("All languages"));
        });
        step("Нажать Руский язык", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/localized_language_name")).findBy(text("Русский")).click();
        });
        step("Проверить, что Руский появился в списке языков", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).shouldHave(CollectionCondition.texts("English", "Русский", "ADD LANGUAGE"));
        });
        step("Вернуться на главный экран, проверить, что Руский появился в списке языков", () -> {
            back();
            $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave(text("We’ve found the following on your device:"));
            $$(AppiumBy.id("org.wikipedia.alpha:id/option_label")).shouldHave(CollectionCondition.texts("English", "Русский"));
        });
        step("Перейти на экран поиска ", () -> {
            back();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).shouldHave(text("Search Wikipedia"));
        });

    }
}
