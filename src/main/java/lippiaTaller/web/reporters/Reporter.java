package lippiaTaller.web.reporters;

import com.crowdar.driver.DriverManager;
import io.lippia.reporter.cucumber4.adapter.ReportServerApiAdapter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Reporter extends ReportServerApiAdapter {

    public Reporter(String arg) {
        super();
    }

    @Override
    public String getBase64Image() {
        return  ((TakesScreenshot) DriverManager.getDriverInstance()).getScreenshotAs(OutputType.BASE64);
    }

}
