import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {
    private String generationData(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @BeforeEach
    void openSite() {
        open("http://localhost:9999");
    }

    @Test
    void positiveTest1(){

        $("[data-test-id=city] input").setValue("Москва");
        String currentData = generationData(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(currentData);
        $("[data-test-id=name] input").setValue("Елена Байрамова");
        $("[data-test-id=phone] input").setValue("+79777306807");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на "  + currentData));
    }

    }


