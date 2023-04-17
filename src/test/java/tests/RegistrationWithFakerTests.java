package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static tests.TestData.*;
import static utils.RandomUtils.getRandomItemFromArray;


@DisplayName("Регистрация пользователя с использованием javafaker")
@Tag("FAKERTEST")
public class RegistrationWithFakerTests extends TestBase {

    @Order(1)
    @Test
    @DisplayName("Генерация данных и присвоених их переменным")
    void fillingForm() {
        String userFirstName = faker.name().firstName(),
                userLastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                userGender = getRandomItemFromArray(gender),
                userNumber = 8 + faker.phoneNumber().subscriberNumber(9),
                userBirthDay_day = String.format("%02d", faker.number().numberBetween(1, 28)),
                userBirthDay_month = getRandomItemFromArray(months),
                userBirthDay_year = String.valueOf(faker.number().numberBetween(1950, 2005)),
                userSubjects = getRandomItemFromArray(subjects),
                userHobbies = getRandomItemFromArray(hobbies),
                userPictureLocation = "img/photo_2022.jpg",
                userAddress = faker.address().fullAddress(),
                userState = "NCR",
                userCity = getRandomItemFromArray(cities);

        step("Открытие главной страницы регистрации", () -> {
            registrationPage.openPage();
        });
        step("Ввод FirstName and LastName", () -> {
            registrationPage.bannerRemoval()
                    .setFirstName(userFirstName)
                    .setLastName(userLastName);
        });
        step("Ввод email", () -> {
            registrationPage.setEmail(userEmail);
        });

        step("Выбор пола пользователя", () -> {
            registrationPage.clickUserGender(userGender);
        });

        step("Ввод последнего набора данных для успешной регистрации", () -> {
            registrationPage.setNumber(userNumber)
                    .setBirthDate(userBirthDay_day, userBirthDay_month, userBirthDay_year)
                    .setSubjects(userSubjects)
                    .setHobbies(userHobbies)
                    .fileUpload(userPictureLocation)
                    .setUserAddress(userAddress)
                    .getUserState(userState)
                    .getUserCity(userCity)
                    .submitForm();
        });

        step("Проверка валидности вывода результатов регистрации", () -> {
            registrationPage.verifyResultsModalAppears()
                    .verifyResult("Student Name", userFirstName + " " + userLastName)
                    .verifyResult("Student Email", userEmail)
                    .verifyResult("Gender", userGender)
                    .verifyResult("Mobile", userNumber)
                    .verifyResult("Date of Birth", userBirthDay_day + " " + userBirthDay_month + "," + userBirthDay_year)
                    .verifyResult("Subjects", userSubjects)
                    .verifyResult("Hobbies", userHobbies)
                    .verifyResult("Picture", "photo_2022.jpg")
                    .verifyResult("Address", userAddress)
                    .verifyResult("State and City", userState + " " + userCity);
        });


    }


    @Order(2)
    @Test
    @DisplayName("Проверка №1")
    void testUser2() {
        step("ввод данных для тестового юзера №2", () -> {
            Selenide.open("http://sovch.chuvashia.com");
        });

    }

    @Order(3)
    @Test
    @DisplayName("Проверка №2")
    void testUser3() {
        step("ввод данных для тестового юзера №3", () -> {
            Selenide.open("http://sovch.chuvashia.com");
        });

    }

    @Order(4)
    @Test
    @DisplayName("Проверка №3")
    void testUser4() {
        step("ввод данных для тестового юзера №4", () -> {
            Selenide.open("http://sovch.chuvashia.com");
        });

    }

    @Order(5)
    @Test
    @DisplayName("Проверка №4")
    void testUser5() {
        step("ввод данных для тестового юзера №5", () -> {
            Selenide.open("http://sovch.chuvashia.com1111");
        });

    }

    @Order(6)
    @Test
    @DisplayName("Проверка №5")
    void testUser6() {
        step("ввод данных для тестового юзера №6", () -> {
            Selenide.open("http://sovch.chuvashia.com");
        });

    }

    @Order(7)
    @Test
    @DisplayName("Проверка №6")
    void testUser7() {
        step("ввод данных для тестового юзера №7", () -> {
            Selenide.open("http://sovch.chuvashia.com");
        });

    }
}







