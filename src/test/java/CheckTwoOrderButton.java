import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageObject.MainPage;
import ru.yandex.praktikum.pageObject.OrderPage;

import static org.junit.runners.Parameterized.*;
import static ru.yandex.praktikum.pageObject.MainPage.BUTTON_ORDER_IN_FINISH_PAGE;
import static ru.yandex.praktikum.pageObject.MainPage.BUTTON_ORDER_IN_HEAD;
import static ru.yandex.praktikum.pageObject.OrderPage.ORDER_PAGE_URL;


@RunWith(Parameterized.class)
public class CheckTwoOrderButton extends BaseTest {

    private final By buttonOrder;
    private final String urlOrderPage;

    public CheckTwoOrderButton(By buttonOrder, String urlOrderPage) {
        this.buttonOrder = buttonOrder;
        this.urlOrderPage = urlOrderPage;
    }


    @Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {BUTTON_ORDER_IN_HEAD,ORDER_PAGE_URL},
                {BUTTON_ORDER_IN_FINISH_PAGE,ORDER_PAGE_URL},
        };
    }

    @Test

    public void CheckButtonOrder(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.waitForLoadingMainPage();
        objMainPage.findCookieQuestion();
        objMainPage.clickOnButton(buttonOrder);
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.waitOrderPage();
        String actualUrl = objOrderPage.getOrderURL();

        Assert.assertEquals("Ожидаемая URL --->"+ ORDER_PAGE_URL + "<--- окна заказа не соответствует фактической --->"+ actualUrl,
                ORDER_PAGE_URL, actualUrl);

    }



}
