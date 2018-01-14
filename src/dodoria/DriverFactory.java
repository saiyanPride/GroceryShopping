package dodoria;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import dodoria.ConfigurationConstants.DriverType;
import static dodoria.Utility.info;

public class DriverFactory {
	public static WebDriver getDriver(DriverType type) {
		WebDriver driver = null;
		if (type == DriverType.HEADLESS) {// TODO: stop phantomjs logs to
											// Sys.Out
			System.setProperty("phantomjs.binary.path",
					ConfigurationConstants.PHANTOMJS_EXECUTABLE_PATH);
			info("setting driver to phantomJS");
			driver = new PhantomJSDriver();
		} else if (type == DriverType.FIREFOX) {
			System.setProperty("webdriver.gecko.driver",
					ConfigurationConstants.GECKODRIVER_EXECUTABLE_PATH);
			info("setting driver to firefox");
			driver = new FirefoxDriver();
		}
		return driver;

	}
}