import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTests {
    public static final String firstName = "Alexey",
            lastName = "Kuslin",
            userEmail = "Alexey@gmail.com",
            userNumber = "1234567890",
            fileName = "Screenshot_1.png",
            currentAddress = "ulitsa Lesnaya, 5, kv. 176",
            stateName = "NCR",
            cityName = "Delhi",
            genderMale = "Male",
            genderFemale = "Female",
            genderOther = "Other";

    public static final String[] subjects = {"Arts", "Maths"},
            hobbies = {"Sports", "Reading", "Music"};

    @BeforeAll
    static void start() {
        open("https://demoqa.com/automation-practice-form");
        //firstname lastname email
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(userEmail);
        //Gender
        $(byText(genderMale)).click();
        $(byText(genderFemale)).click();
        $(byText(genderOther)).click();
        //Mobile
        $("#userNumber").val(userNumber);
        //Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("January");
        $(".react-datepicker__year-select").selectOptionContainingText("2000");
        $(byText("13")).click();
        //Subjects
        $("#subjectsInput").val("A");
        for (String subject : subjects) {
            $("#subjectsInput").val(subject).pressTab();
        }
        //Hobbies
        for (String hobby : hobbies) {
            $(byText(hobby)).click();
        }
        //Picture
        File file = new File(fileName);
        $("#uploadPicture").uploadFile(file);
        //Current Address
        $("#currentAddress").val(currentAddress);
        //State and City
        $(byText("Select State")).scrollTo().click();
        $(byText(stateName)).click();
        $(byText("Select City")).scrollTo().click();
        $(byText(cityName)).click();
        $("#submit").scrollTo().click();
    }

    @AfterAll
    static void end() {

        $("#closeLargeModal").click();
        $("body").shouldNotHave(text("Thanks for submitting the form"));
    }

    @Test
    void modalFormIsOpen() {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    @Test
    void modalFormHaveStudentName() {
        $("tr:nth-child(1)>td:nth-child(2)").shouldHave(text(firstName + " " + lastName));
    }

    @Test
    void modalFormHaveStudentEmail() {
        $("tr:nth-child(2)>td:nth-child(2)").shouldHave(text(userEmail));
    }

    @Test
    void modalFormHaveGenderOther() {
        $("tr:nth-child(3)>td:nth-child(2)").shouldHave(text(genderOther));
    }

    @Test
    void modalFormHaveMobile() {
        $("tr:nth-child(4)>td:nth-child(2)").shouldHave(text(userNumber));
    }

    @Test
    void modalFormHaveDateOfBirth() {
        String Date = $("#dateOfBirthInput").getText();
        $("tr:nth-child(4)>td:nth-child(2)").shouldHave(text(Date));
        System.out.println(Date);
    }

    @Test
    void subjectsInModalDialogTest() {
        StringBuilder sbj = new StringBuilder();
        for (String subject : subjects) {
            sbj.append(subject).append(", ");
        }
        $("tr:nth-child(6)>td:nth-child(2)").shouldHave(
                text(sbj.delete(sbj.length() - 2, sbj.length() - 1).toString()));
    }

    @Test
    void hobbiesInModalDialogTest() {
        StringBuilder sbj = new StringBuilder();
        for (String hobby : hobbies) {
            sbj.append(hobby).append(", ");
        }
        $("tr:nth-child(7)>td:nth-child(2)").shouldHave(
                text(sbj.delete(sbj.length() - 2, sbj.length() - 1).toString()));
    }

    @Test
    void modalFormHavePicture() {
        $("tr:nth-child(8)>td:nth-child(2)").shouldHave(text(fileName));
    }

    @Test
    void modalFormHaveAddress() {
        $("tr:nth-child(9)>td:nth-child(2)").shouldHave(text(currentAddress));
    }

    @Test
    void modalFormHaveStateAndCity() {
        $("tr:nth-child(10)>td:nth-child(2)").shouldHave(text(stateName + " " + cityName));
    }
}
