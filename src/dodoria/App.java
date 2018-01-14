package dodoria;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import dodoria.ConfigurationConstants.DriverType;
import static dodoria.Utility.*;
import static dodoria.BrowserInteraction.*;

public class App {
	public static final WebDriver driver = DriverFactory
			.getDriver(DriverType.HEADLESS);
	public static final WebDriverWait wait = new WebDriverWait(driver,
			ConfigurationConstants.DEFAULT_WEBDRIVER_WAIT_TIME);

	public static void main(String[] args) {
		try {
			info("launching browser");
			login();
			Map<String, String> shoppingCartList = new HashMap<String, String>();
			updateShoppingList(shoppingCartList);
			updateShoppingCart(shoppingCartList);
		} catch (JsonMappingException | JsonGenerationException
				| JsonParseException exception) {
			error("Could not extract your desired grocery items from the JSON shopping list");
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}
	}
} // ! App.class
