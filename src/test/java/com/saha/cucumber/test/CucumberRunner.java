package com.saha.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by segeli on 9.02.2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",   // feature yolu
        glue = {"com.saha.cucumber.step"},          // STEPS PAKETİN tam adı
        plugin = {"pretty","summary"},
        monochrome = true
        // tags'ı komuttan veriyorsun, burada bırakmıyoruz
)
public class CucumberRunner {
}
