import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pageObject.MainPage;
import ru.yandex.praktikum.pageObject.OrderPage;

import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CreateNewOrder extends BaseTest  {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String number;
    private final String dateOrder;
    private final String periodRenta;
    private final String color;
    private final String comment;


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
                {"Вика","Семенова","ул.Ивантеевская, 45","Марьино","95326553236","27.07.2023","четверо суток","чёрный жемчуг","УРА!! У меня будет самокат"},
                {"Нина","Иванова","ул.Проезжая, 115","Курская","85694231586","17.08.2023","двое суток","серая безысходность","Позвонить за час до доставки"},
        };
    }


    @Test

    public void createOrderSuccessfully(){
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.openOrderPage();
        objOrderPage.waitOrderPage();
        MainPage objMainPage = new MainPage(driver);
        objMainPage.findCookieQuestion();
        objOrderPage.inputFirstPageOrder(name,surname,address,metro,number);
        objOrderPage.clickButtonNext();
        objOrderPage.waitOrderPage();
        objOrderPage.inputSecondPageOrder(dateOrder,periodRenta,color,comment);
        objOrderPage.buttonOrderFinish();
        objOrderPage.waitLoadingAreYouSure();
        objOrderPage.clickButtonOk();
        objOrderPage.waitLoadingSuccessfulIssued();

    }






}
