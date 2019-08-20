package com.vytrack.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/default-cucumber-reports",
                  "json:target/parallel-cucumber2.json",
                  "rerun:target/rerun.txt"
        },
        features = "src/test/resources/com/vytrack/features/contacts",
        glue = "com/vytrack/step_definitions"
)
public class ContactsParallelRunner {
}
