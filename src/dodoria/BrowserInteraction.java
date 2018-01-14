package dodoria;

import static dodoria.App.driver;
import static dodoria.App.wait;
import static dodoria.Utility.info;
import static dodoria.Utility.warn;
import static dodoria.Utility.getMapFromJsonFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowserInteraction {

	public static void login() {
		info("navigating to login page");
		driver.get(ConfigurationConstants.SAINSBURYS_LOGIN_PAGE);
		// retrieve user login details
		String emailAddress = null, password = null;
		try (Scanner scanner = new Scanner(System.in);) {
			System.out.println("Please enter your login email address");
			emailAddress = scanner.nextLine();
			System.out.println("Please enter your password");
			password = scanner.nextLine();
		}

		// fill in user login form and press enter
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id(ConfigurationConstants.LOGIN_FIELD_ID)));
		WebElement emailInput = driver.findElement(By
				.id(ConfigurationConstants.LOGIN_FIELD_ID));
		emailInput.sendKeys(emailAddress);

		// password entry
		WebElement passwordInput = driver.findElement(By
				.id(ConfigurationConstants.PASSWORD_FIELD_ID));
		passwordInput.sendKeys(password + Keys.ENTER);
		password = null; // remove reference to password
	}

	// Presents the user with a delivery slot selection view
	// and waits until the user has selected his/her desired delivery slot
	// Only works if GUI based webdriver is being used &
	// `ConfigurationConstants.SKIP_DELIVERY_BOOKING` is false
	public static void chooseDeliverySlot() {
		if (ConfigurationConstants.SKIP_DELIVERY_BOOKING)
			return;
		try (Scanner scanner = new Scanner(System.in);) {
			clickButton(ConfigurationConstants.BOOK_DELIVERY_BUTTON_SELECTOR);
			clickButton(ConfigurationConstants.CHOOSE_DELIVERY_SLOT_BUTTON_SELECTOR);
			info("Choose your delivery slot");
			info("Enter any key once you're done");
			scanner.nextLine();
		}
	}

	public static void clickButton(String buttonCssSelector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector(buttonCssSelector)));
		driver.findElement(By.cssSelector(buttonCssSelector)).click();
	}

	// generates list of items to buy
	public static void updateShoppingList(Map<String, String> shoppingCartList)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> groceryOrderSpecification = getMapFromJsonFile();

		// add each item to `shoppingCartList` n times, where n is the quantity
		// specified in `groceryOrderSpecification`

		String categories[] = { ConfigurationConstants.DEFAULT,
				ConfigurationConstants.PROTEIN,
				ConfigurationConstants.CARBOHYDRATES,
				ConfigurationConstants.DRINKS,
				ConfigurationConstants.CONDIMENTS,
				ConfigurationConstants.SNACKS,
				ConfigurationConstants.FRUIT_VEGETABLES };
		String itemName = null, id = null;
		int quantity = 0;
		for (String foodCategory : categories) {
			@SuppressWarnings("unchecked")
			ArrayList<Map<String, String>> categoryItemsList = (ArrayList<Map<String, String>>) groceryOrderSpecification
					.get(foodCategory);
			for (Map<String, String> item : categoryItemsList) {
				itemName = item.get(ConfigurationConstants.ITEM_NAME);
				id = item.get(ConfigurationConstants.ITEM_ID);
				quantity = Integer.parseInt(item
						.get(ConfigurationConstants.ITEM_QUANTITY));
				// add `quantity` no of items to `shoppingCartList`
				for (int i = 1; i <= quantity; ++i) {
					shoppingCartList.put(itemName + "_X" + i, id);
				}

			}
		}
	}

	// searches for every item in `shoppingCartList` and adds it to the shopping
	// cart
	public static void updateShoppingCart(Map<String, String> shoppingCartList) {
		clickButton(ConfigurationConstants.START_SHOPPING_BUTTON_SELECTOR);
		Iterator<Entry<String, String>> it = shoppingCartList.entrySet()
				.iterator();
		Map.Entry<String, String> desiredItem = null;
		String groceriesLandingPageURL = null;
		while (it.hasNext()) {
			desiredItem = it.next();
			try {
				// search online store for `desiredItem`
				clickButton(ConfigurationConstants.GROCERIES_TOP_MENU_SELECTOR);
				groceriesLandingPageURL = driver.getCurrentUrl();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.id(ConfigurationConstants.SEARCH_BOX_ID)));
				WebElement searchBox = driver.findElement(By
						.id(ConfigurationConstants.SEARCH_BOX_ID));
				searchBox.clear();
				// TODO retrieve price
				info("Adding " + desiredItem.getKey() + " to basket");
				searchBox.sendKeys(desiredItem.getValue().toString()
						+ Keys.ENTER);

				// wait for browser to display search results containing
				// `desiredItem`
				wait.until(ExpectedConditions.not(ExpectedConditions
						.urlMatches(groceriesLandingPageURL)));
				Thread.sleep(ConfigurationConstants.DEFAULT_TIME_DELAY);
				clickButton(ConfigurationConstants.ADD_ITEM_TO_CART_BUTTON_SELECTOR);
			} catch (Exception e) {
				warn("Could not add " + desiredItem.getValue());
				warn("Skipping and moving to next item");
			}
		}
		info("Done with adding items to shopping cart");
	}

}
