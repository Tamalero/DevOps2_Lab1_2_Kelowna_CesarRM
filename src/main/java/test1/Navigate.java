package test1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert; // Import the File class
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; // Import the IOException class to handle errors
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void naVi(WebDriver driver) {
        Logger logger = Logger.getLogger(this.getClass());
        BasicConfigurator.configure();

        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String filePathString = "output.txt";
        Path path = Paths.get(filePathString);

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
                try { 
                    alert.accept();
                }
                catch ( Exception e) {
                    
                }
                Thread.sleep(1000);
                if ( selectableElement.getText().isEmpty() ) {
                    isCorrect = true;

                }
                if (Files.exists(path)) {
                    Files.delete(path);

                    if (isCorrect = false) {
                        System.out.println("Function works");
                        logger.info("Program works");
                        try {

                            File myObj = new File(path.toString());
                            FileWriter fw = new FileWriter(path.toString(), true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter out = new PrintWriter(bw);
                            out.println("SUCCESS");
                            out.close();

                        } catch (IOException e) {

                        }
                    } else {
                        System.out.println("Fatal error, program is not working");
                        logger.info("Failure in Program");
                        try {

                            File myObj = new File(path.toString());
                            FileWriter fw = new FileWriter(path.toString(), true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter out = new PrintWriter(bw);
                            out.println("ERROR");
                            out.close();

                        } catch (IOException e) {

                        }
                        driver.quit();
                    }
                }
            } catch (Exception e) {
                System.out.println("Alert not found. program working or error in Alert");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

}
