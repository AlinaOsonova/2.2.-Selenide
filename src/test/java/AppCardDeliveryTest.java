import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AppCardDeliveryTest {
    private String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:9999");
    }

    @Test
    void shouldSendValidForm() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Успешно! Встреча упешно забронирована на " + planningDate));

    }


    @Test
    void shouldErrorIfCityEmpty() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldErrorIfNameEmpty() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldErrorIfPhoneEmpty() {
        String planningDate = generateDate(8, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldErrorIfDateIncorrect() {
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Неверно введена дата")).shouldBe(visible);
    }


    @Test
    void shouldErrorIfCheckboxRemoved() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("button.button").click();
        $(".checkbox.input_invalid").shouldBe(visible);
    }

    @Test
    void shouldFindCityByTwoLetters() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Во");
        $(By.xpath("//span[text()='Волгоград']")).click();
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Успешно! Встреча упешно забронирована на" + planningDate));
    }
    }


