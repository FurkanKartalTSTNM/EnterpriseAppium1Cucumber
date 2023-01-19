package com.saha.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by segeli on 9.02.2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = "pretty", glue = {
  "com.saha.cucumber.step"}, monochrome = true)
public class CucumberRunner {
}
