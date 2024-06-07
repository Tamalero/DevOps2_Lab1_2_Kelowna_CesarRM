package test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Main {
    private WebDriver driver;
    

    public static void main(String[] args) {
       // System.setProperty("webdriver.chrome.driver", "K:\\School\\SQA109 Software Test Automation 01\\Selenium Project\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); //locate chromedriver executable and instances it, Windows Local
        // System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); //linux
        WebDriverManager.chromedriver().setup();
       
        WebDriver driver = new ChromeDriver(); // New chrome driver instance
            
            Navigate naviGate = new Navigate(driver);  
           
           
            try {
                
                driver.get(Locators.webSite);  // browses to main page
                naviGate.naVi(driver); // Launches the method on the class Navigate.
                //driver.close();
               
     
            } catch ( Exception e) {
                e.printStackTrace();
            }
             
            finally {
                //driver.quit();
            }
    
           
        }

}