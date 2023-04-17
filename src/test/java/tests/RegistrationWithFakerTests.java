package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static tests.TestData.*;
import static utils.RandomUtils.getRandomItemFromArray;


@DisplayName("Регистрация пользователя с использованием javafaker")
public class RegistrationWithFakerTests extends TestBase {

    @Test
    @Tag("FAKERTEST")
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
            step("ввод email", () -> {
                registrationPage.setEmail(userEmail);
            });

            step("выбор пола пользователя", () -> {
                registrationPage.clickUserGender(userGender);
            });

            step("ввод остального набора данных необходимых для регистрации", () -> {
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
}
