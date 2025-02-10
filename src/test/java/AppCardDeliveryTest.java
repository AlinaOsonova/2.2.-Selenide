import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {
    private String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void test1() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void test2() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        Selenide.open("http://localhost:9999");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void test3() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void test4() {
        String planningDate = generateDate(8, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void test5() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Неверно введена дата")).shouldBe(visible);
    }


    @Test
    void test6() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Волгоград");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("button.button").click();
        $(".checkbox.input_invalid").shouldBe(visible);
    }

    @Test
    void test7() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Во");
        $(By.xpath("//span[text()='Волгоград']")).click();
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Алина Осонова");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

}

