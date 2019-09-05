package com.vytrack.step_definitions;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.DBType;
import com.vytrack.utilities.DBUtility;
import com.vytrack.utilities.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {



    @Before
    public void setUp(Scenario scenario){
        if(scenario.getSourceTagNames().contains("DB")){
            DBUtility.establishConnection(DBType.MYSQL);
        }
        System.out.println("Before hooks");
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Before("@database")
    public void setUpDBCOnn(){
        DBUtility.establishConnection(DBType.MYSQL);
        System.out.println("Setting up DB connection");
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.getSourceTagNames().contains("DB")){
            DBUtility.closeConnections();
        }
        System.out.println("After hooks");
        // check if the scenario is failed
        if (scenario.isFailed()){
            // take that screenshot
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            // attach the scenario to the report
            scenario.embed(screenshot, "image/png");
        }
        Driver.closeDriver();
    }

    @After("@database")
    public void tearDownConnection(Scenario scenario){
        DBUtility.closeConnections();
        System.out.println("Closing DB connection");
    }
}
