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




    public OrderPage(WebDriver driver) {

        this.driver = driver;
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


}
