package com.saha.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by segeli on 9.02.2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.saha.cucumber.step"},
        plugin = {"pretty","summary"},
        monochrome = true
)
public class CucumberRunner {
}
