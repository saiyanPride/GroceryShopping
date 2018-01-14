package dodoria;

public interface ConfigurationConstants {
	final String LOGIN_BUTTON_SELECTOR = "div[class=\"formSectionFooter\"] input[value='Log in']";
	final String BOOK_DELIVERY_BUTTON_SELECTOR = "li[class='navPanelBookDelivery']";
	final String CHOOSE_DELIVERY_SLOT_BUTTON_SELECTOR = "div[class='eSpotContainer']+a";
	final String START_SHOPPING_BUTTON_SELECTOR = "li[class='navPanelStartShopping']";
	final String SEARCH_BOX_ID = "search";
	final String LOGIN_FIELD_ID = "logonId";
	final String PASSWORD_FIELD_ID = "logonPassword";
	final String ADD_ITEM_TO_CART_BUTTON_SELECTOR = "input[name='Add'][value='Add'][type='submit']";
	final String GROCERIES_TOP_MENU_SELECTOR = "li[id='groceriesTopMenu']";
	final boolean SKIP_DELIVERY_BOOKING = true;

	final int DEFAULT_WEBDRIVER_WAIT_TIME = 30; // seconds
	final int DEFAULT_TIME_DELAY = 2000; // milliseconds

	// shopping list JSON
	final String SHOPPING_LIST_FILE_PATH = "json/shoppingList.json";
	final String DEFAULT = "default";
	final String PROTEIN = "protein";
	final String CARBOHYDRATES = "carbohydrates";
	final String DRINKS = "drinks";
	final String CONDIMENTS = "condiments";
	final String SNACKS = "snacks";
	final String FRUIT_VEGETABLES = "fruitAndVegetables";
	final String ITEM_NAME = "name";
	final String ITEM_ID = "id";
	final String ITEM_QUANTITY = "quantity";

	// driver
	final String SAINSBURYS_LOGIN_PAGE = "https://www.sainsburys.co.uk/webapp/wcs/stores/servlet/LogonView?langId=44&storeId=10151&krypto=7jB9jPdJqSDtPWicBUu25advKBRt7It8%2Bcydq6b5qLQFm%2Byito%2BybHSnP1e8XhDcvgwTUd2G51tN8iQmINu8D6EfPFLpb0kuDTLVdGdKLl28sBPY3JnfFjJouUJGmlPDZwXnTpQcMvSfWB6AJU3e%2Ff01yZ2GjR%2FTiUwunkfcAbtE7CA0wjicUU2y01hb4Uu6waIDjiwOvFLLyf6eWLlK%2FMmkC6G3dca8A%2BYixHJ0sxr%2BrCyiToGH%2BejoGj17zXZH&ddkey=https%3ALogonView";

	enum DriverType {
		HEADLESS, FIREFOX
	};

	final String PHANTOMJS_EXECUTABLE_PATH = "/Users/NiranPyzzle/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs";
	final String GECKODRIVER_EXECUTABLE_PATH = "/Users/NiranPyzzle/Downloads/geckodriver";
}
