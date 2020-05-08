package app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
public class ConfigClientAppConfiguration {
    public ConfigClientAppConfiguration() {
    }

    public String getFirsttest() {
        return firsttest;
    }

    public void setFirsttest(String firsttest) {
        this.firsttest = firsttest;
    }

    private String firsttest;

    public String getSecondtest() {
        return secondtest;
    }

    public void setSecondtest(String secondtest) {
        this.secondtest = secondtest;
    }

    private String secondtest;

    public String getThirdtest() {
        return thirdtest;
    }

    public void setThirdtest(String thirdtest) {
        this.thirdtest = thirdtest;
    }

    private String thirdtest;

    public String getFourthtest() {
        return fourthtest;
    }

    public void setFourthtest(String fourthtest) {
        this.fourthtest = fourthtest;
    }

    private String fourthtest;
}
