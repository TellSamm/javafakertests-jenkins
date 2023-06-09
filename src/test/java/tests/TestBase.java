package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Locale;
import java.util.Map;



public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker(new Locale("en-US"));

    @BeforeAll
    static void openPracticeForm() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome"; // браузер
        Configuration.browserVersion = "100.0"; // версия браузера
        Configuration.holdBrowserOpen = false;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub"; //удалённый запуск браузера на selenoid

        //конфиг что бы добавилось enableVNC - это мы включаем что бы было окошко в окошке в Selenoid
        //, enableVideo - вкл.запись видео происходит + ниже есть настройка Attach.addVideo(); // ЗАПИСЬ ВИДЕО
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities; //конфигурация. Возможности браузера = возможности
    }


    //Перед каждым тестом записываем шаги с step-ы что бы если мы развернули их видно было что где прошло или упало
    @BeforeEach //
    void addListener() {
        SelenideLogger.addListener("AllureSelenide",new AllureSelenide());
    }
    // добавляется сценарий теста как в IDEA (шаги)
    // + в build.gradle добавили сначало зависимость "io.qameta.allure:allure-selenide:2.13.6"



    //Перед этими настройками добавили файл в дирректорию java/helpers название файла Attach c методами
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot"); //"Last screenshot" это просто название скрина который будет в Allure отчете
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo(); // ЗАПИСЬ ВИДЕО
    }

}
