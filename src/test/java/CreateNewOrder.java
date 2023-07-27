import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageObject.MainPage;
import ru.yandex.praktikum.pageObject.OrderPage;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class CreateNewOrder  {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String number;
    private final String dateOrder;
    private final String periodRenta;
    private final String color;
    private final String comment;

    private WebDriver driver;

    public CreateNewOrder(String name, String surname, String address, String metro, String number, String dateOrder, String periodRenta, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.number = number;
        this.dateOrder = dateOrder;
        this.periodRenta = periodRenta;
        this.color = color;
        this.comment = comment;
    }


    @Parameters
    public static Object[][] getCredentials(){
        return new Object[][] {
                {"Вика","Семенова","ул.Ивантеевская, 45","Марьино","95326553236","27.07.2023","четверо суток","серая безысходность","УРА!! У меня будет самокат"},
        };
    }



    @Before
    public void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    @Test

    public void createOrderSuccessfully(){
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.openOrderPage();
        objOrderPage.waitOrderPage();
        MainPage objMainPage = new MainPage(driver);
        objMainPage.findCookieQuestion();
        objOrderPage.inputName(name);
        objOrderPage.inputSurname(surname);
        objOrderPage.inputAddress(address);
        objOrderPage.chooseStationUnderground(metro);
        objOrderPage.inputPhoneNumber(number);
        objOrderPage.clickButtonNext();
        objOrderPage.waitOrderPage();
        objOrderPage.inputDateOrder(dateOrder);
        objOrderPage.chooseRentalPeriod(periodRenta);
        objOrderPage.chooseColorScooter(color);
        objOrderPage.inputComment(comment);
        objOrderPage.buttonOrderFinish();
        objOrderPage.waitLoadingAreYouSure();
        objOrderPage.clickButtonOk();
        objOrderPage.waitLoadingSuccessfulIssued();

    }






}
