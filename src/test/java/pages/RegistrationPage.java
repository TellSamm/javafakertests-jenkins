package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private CalendarComponent calendarComponent = new CalendarComponent();
    private RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    private final String TITLE_TEXT = "Student Registration Form";
    private SelenideElement

    firstNameInput = $("#firstName"),

    lastNameInput = $("#lastName"),

    userEmail = $("#userEmail"),

    userGender = $("#genterWrapper"),

    userNumber = $("#userNumber"),

    userSubjects = $("#subjectsInput"),

    userHobbies = $("#hobbiesWrapper"),

    uploadPicture = $("#uploadPicture"),

    dateOfBirthInput = $("#dateOfBirthInput"),

    userAddress = $("#currentAddress"),

    userState = $("#state"),

    userCity = $("#city"),

    buttonSubmit = $("#submit"),

    stateAndCitySelectionBlock = $("#stateCity-wrapper");


    private final String FIXED_BAN = "$('#fixedban').remove()";
    private final String FOOTER = "$('footer').remove()";


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        System.out.println("Start Test!");

        return this;
    }


    public RegistrationPage bannerRemoval() {
        executeJavaScript(FIXED_BAN);
        executeJavaScript(FOOTER);

        return this;
    }


    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmail.setValue(value);

        return this;
    }

    public RegistrationPage clickUserGender(String value) {
        userGender.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setNumber(String value) {
        userNumber.setValue(value);

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        userSubjects.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        userHobbies.$(byText(value)).click();

        return this;
    }


    public RegistrationPage fileUpload(String value) {
        uploadPicture.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setUserAddress(String value) {
        userAddress.setValue(value);

        return this;
    }

    public RegistrationPage getUserState(String state) {
        userState.click();
        stateAndCitySelectionBlock.$(byText(state)).click();

        return this;
    }

    public RegistrationPage getUserCity(String city) {
        userCity.click();
        stateAndCitySelectionBlock.$(byText(city)).click();

        return this;
    }

    public RegistrationPage verifyResultsModalAppears() {
        registrationResultsModal.verifyModalAppear();

        return this;
    }

    public RegistrationPage submitForm() {
        buttonSubmit.click();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }


}
