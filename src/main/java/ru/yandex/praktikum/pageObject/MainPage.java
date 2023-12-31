package ru.yandex.praktikum.pageObject;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MainPage {
 // поле драйвера
    private WebDriver driver;

// URL стартовой страницы
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

//Cookie Massage
    private static final By COOKIE_MASSAGE = By.className("App_CookieConsent__1yUIN");

//Кнопка согласия использования Cookie

    private static final By COOKIE_BUTTON_ACCEPT = By.className("App_CookieButton__3cvqF");

//таймаут 5 секунд
    private static final int WAIT_FIVE = 5;

 //кнопка Заказать в заголовке страницы
    public static final By BUTTON_ORDER_IN_HEAD = By.className("Button_Button__ra12g");

 //кнопка Заказать в конце страницы
    public static final By BUTTON_ORDER_IN_FINISH_PAGE = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text()= 'Заказать']");

//Вопросы о Важном

//класс поля ответов на вопросы
    private static final By ANSWER_FIELD = By.className("accordion__panel");

// поле Вопрос
    private static final By BUTTON_QUESTION = By.xpath(".//div[@data-accordion-component = 'AccordionItemButton']");

// поле Ответ на Вопрос
    private static final By ANSWER_ON_BUTTON_QUESTION = By.className("accordion__panel");


//Конструктор класса
    public MainPage(WebDriver driver){

        this.driver = driver;
    }

//метод открытия страницы
    public void openMainPage(){
        driver.get(PAGE_URL);
    }

//метод ожидания загрузки страницы
    public void waitForLoadingMainPage(){
        new WebDriverWait(driver, WAIT_FIVE)
                .until(ExpectedConditions.elementToBeClickable(BUTTON_ORDER_IN_HEAD));
    }

//метод ожидания загрузки ответа на вопрос
    public void waitForLoadAnswerQuestion(){
        new WebDriverWait(driver, WAIT_FIVE)
                .until(ExpectedConditions.presenceOfElementLocated(ANSWER_FIELD));
    }

//метод нажатия кнопки
    public void clickOnButton(By button){
        assertTrue("Кнопка не активна!!!",driver.findElement(button).isEnabled());
        driver.findElement(button).click();
    }

//Находим кнопку Вопросы о Важном, по номеру вопроса, проверяем соотвествие текста вопроса ожидаемому, кликаем на нее
    public void findAndClickButtonQuestionAboutImportant(int number, String expectedQuestion){
        List<WebElement> elementsQuestion = driver.findElements(BUTTON_QUESTION);
        WebElement question = elementsQuestion.get(number);
        String actualQuestion = question.getText();
        assertEquals("Ожидаемый текст вопроса не соответствует фактическому",expectedQuestion,actualQuestion);
        question.click();
    }

//Находим кнопку ответа по номеры ответа в списке, получаем текст
    public String findAnswerInQuestionAboutImportant(int number){
        List<WebElement> elementsAnswer = driver.findElements(ANSWER_ON_BUTTON_QUESTION);
        return elementsAnswer.get(number).getText();

    }

//метод проверки наличия на странице сообщения о куки и закрытия их, в случае если они отобразились на странице
    public void findCookieQuestion() {
        boolean cookieOnPage = driver.findElement(COOKIE_MASSAGE).isDisplayed();
        if(cookieOnPage){
            clickOnButton(COOKIE_BUTTON_ACCEPT);
        }
    }








}
