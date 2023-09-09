package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

 // поле драйвера
    private WebDriver driver;

//URL страницы заказа

    public static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

 //таблица заказа
    private static final By TABLE_PAGE_ORDER = By.className("Order_Content__bmtHS");

// поле Имя
    private static final By NAME = By.xpath(".//input[@class ='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Имя']");

// поле Фамилия
    private static final  By SURNAME = By.xpath(".//div[@class = 'Order_Content__bmtHS']//div[@class='Input_InputContainer__3NykH'][2]/input");

//поле Адрес
    private static final By ADRESS = By.xpath(".//input[@class ='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Адрес: куда привезти заказ']");

//поле Метро
    private static final By SELECT_METRO = By.className("select-search__input");

//поле Телефон
    private static final By PHONE_NUMBER = By.xpath(".//input[@class ='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Телефон: на него позвонит курьер']");

//кнопка Далее
    private static final By BUTTON_NEXT = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");

//поле Срок доставки самоката
    private static final By DATE_ORDER = By.xpath(".//div[@class = 'react-datepicker__input-container']/input");

//дропдоун Срок аренды самоката
    private static final By RENTAL_PERIOD = By.className("Dropdown-arrow");

//поле комментарий
    private static final By COMMENT_FIELD = By.xpath(".//input[@class ='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = 'Комментарий для курьера']");

//кнопка Заказать в конце заказа после ввода всех полей
    private static final By BUTTON_ORDER_FINISH = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

//заказ успешно оформлен
    private static final By SUCCESSFUL_ISSUED = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

//окно Хотите оформить заказ?
    private static final By ARE_YOU_SURE = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ' and text() = 'Хотите оформить заказ?']");

//кнопка Ок на окне     Хотите оформить заказ?
    private static final By BUTTON_OK = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

//метод открытия страницы оформления заказа
    public void openOrderPage(){
        driver.get(ORDER_PAGE_URL);
    }


//метод ожидания страницы
 public void waitOrderPage(){
     new WebDriverWait(driver, 3)
             .until(ExpectedConditions.presenceOfElementLocated(TABLE_PAGE_ORDER));
 }

//берем адрес URL
    public String getOrderURL(){
        return driver.getCurrentUrl();

    }

//выбираем станцию метро
    public void chooseStationUnderground(String station){
        driver.findElement(SELECT_METRO).click();
        String xPathStation = ".//div[@class = 'Order_Text__2broi' and text()='" +  station + "']";
        driver.findElement(By.xpath(xPathStation)).click();
    }

//заполняем первую страницу
public void inputFirstPageOrder(String name,String surname,String adress,String station,String number) {
    // заполняем поле Имя
    driver.findElement(NAME).clear();
    driver.findElement(NAME).sendKeys(name);
    //заполняем поле Фамилия
    driver.findElement(SURNAME).sendKeys(surname);
    //заполняем поле Адрес
    driver.findElement(ADRESS).sendKeys(adress);
    //запоняем поле Номер Телефона
    driver.findElement(PHONE_NUMBER).sendKeys(number);
    //выбираем станцию метро
    driver.findElement(SELECT_METRO).click();
    String xPathStation = ".//div[@class = 'Order_Text__2broi' and text()='" +  station + "']";
    driver.findElement(By.xpath(xPathStation)).click();
    }



//нажимаем кнопку Далее
    public void clickButtonNext(){
        driver.findElement(BUTTON_NEXT).click();
    }


    //заполняем вторую страницу
    public void inputSecondPageOrder(String date,String period,String color,String comment){
    //заполняем срок доставки заказа
    driver.findElement(DATE_ORDER).sendKeys(date);
    //выбираем срок аренды
    driver.findElement(RENTAL_PERIOD).click();
    String xpathPeriod = ".//div[text() = '" + period + "']";
    driver.findElement(By.xpath(xpathPeriod)).click();
    //выбираем цвет самоката
    String xpathColor = ".//label[text()='" + color + "']";
    driver.findElement(By.xpath(xpathColor)).click();
    //ввод комментария для курьера
    driver.findElement(COMMENT_FIELD).sendKeys(comment);

    }

// нажимаем на кнопку Заказать
    public void buttonOrderFinish(){
        driver.findElement(BUTTON_ORDER_FINISH).click();
    }

// ждем загрузки страницы об успешном создании заказа
    public void waitLoadingSuccessfulIssued(){
        new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(SUCCESSFUL_ISSUED));
    }

//ждем загрузки окна Хотите оформить заказ?
    public void waitLoadingAreYouSure(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated((ARE_YOU_SURE)));
    }

//Кнопка ДА в окне вопрос    Хотите оформить заказ?
    public void clickButtonOk(){
        driver.findElement(BUTTON_OK).click();
    }


}
