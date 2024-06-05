package test1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.*;

public class Navigate {
    private WebDriver driver;
    
    @FindBy(id = "lastname")
    private WebElement lastName;  
    @FindBy(id = "firstname")
    private WebElement firstName;
    @FindBy(id = "GroupSize")
    private WebElement groupSize;   
    @FindBy(id = "addMemberBtn")
    private WebElement addMember;   
    @FindBy(id = "deleteMemberBtn")
    private WebElement delMember;   
    @FindBy(xpath = "/html/body/div[1]/table/tbody/tr/td[1]/form/table/tbody/tr/td[2]/table/tbody/tr/td[1]/select/option\r\n")
    private WebElement selectableElement; 


    
  
        public Navigate(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // initializes all the WebElement parts.
    }

       
    public void naVi(WebDriver driver)  {
        final Logger logger = Logger.getLogger(this.getClass());
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        try {
            driver.get(Locators.webSite);
           // wait.until(ExpectedConditions.visibilityOf(userNameField));
           Thread.sleep(4000);
            lastName.sendKeys("Cesar");
            firstName.sendKeys("Rodriguez");
            groupSize.sendKeys("3");
            addMember.click();
            Thread.sleep(1000);
            selectableElement.click();
            delMember.click();
            boolean isCorrect;
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());         
                String alertText = alert.getText();
                isCorrect = alertText.contains("Error");
                Thread.sleep(4000);
                alert.accept();
                 Thread.sleep(1000);    
                if (isCorrect = false) {
                    System.out.println("Function works");
                    logger.info("Program works");
                 }
                 else {
                    System.out.println("Fatal error, program is not working");
                    logger.error("Failure in Program");
                    driver.quit();       
                 } 
                }      
                 catch (Exception e) {
                    System.out.println("Alert not found or error in Alert");
                 }
        }
        catch (Exception e){
            e.printStackTrace();
              }
              finally {
                driver.quit();
              }

    }


}


