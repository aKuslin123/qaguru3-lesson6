import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class lesson2 {
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
            genderOther = "Other",
            dayOfBirth = "13",
            monthOfBirth = "January",
            yearOfBirth = "2000",

            subject1Prefix = "m",
            subject1 = "Chemistry",
            subject2Prefix = "e",
            subject2 = "Commerce",

            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music";


    @Test
    void successfulFillFormTest() {
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
        $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
        $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();
        //Subjects
        $("#subjectsInput").val(subject1Prefix);
        $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
        $("#subjectsInput").val(subject2Prefix);
        $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
        // set hobbies
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();
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

        // asserts
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
        $x("//td[text()='Gender']").parent().shouldHave(text(genderOther));
        $x("//td[text()='Mobile']").parent().shouldHave(text(userNumber));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject1 + ", " + subject2));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2 + ", " + hobby3));
        $x("//td[text()='Picture']").parent().shouldHave(text(fileName));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(stateName + " " + cityName));
    }
}
