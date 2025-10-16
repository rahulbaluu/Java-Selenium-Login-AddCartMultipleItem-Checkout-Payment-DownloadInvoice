import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AddCart {
	public static void main(String[] args) {
		String baseUrl = "https://automationexercise.com";
		System.setProperty("webdriver.chrome.driver", "/Users/rahulbalashanmugam/Downloads/chromedriver-mac-arm64/chromedriver");

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String[] itemNeeded = {"Blue Top", "Sleeveless Dress", "GRAPHIC DESIGN MEN T SHIRT - BLUE"};

		driver.get(baseUrl);

		driver.findElement(By.cssSelector("p.fc-button-label")).click(); // Consent
		driver.findElement(By.linkText("Signup / Login")).click();
		driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys("178@gmail.com");
		driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys("178");
		driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

		driver.findElement(By.cssSelector("i.material-icons.card_travel")).click();

		List<String> itemsToAdd = Arrays.asList(itemNeeded);

		for (int i = 0; i < itemsToAdd.size(); i++) {
			String item = itemsToAdd.get(i);

			// Refresh the product list each time to avoid stale elements
			List<WebElement> products = driver.findElements(By.className("product-image-wrapper"));
			boolean itemFound = false;

			for (WebElement product : products) {
				String productName = product.getText();
				if (productName.contains(item)) {
					System.out.println("Adding to cart: " + item);
					product.findElement(By.cssSelector("a.add-to-cart")).click();

					// Wait for modal popup to appear
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));

					if (i < itemsToAdd.size() - 1) {
						// For all but last item, click Continue Shopping
						WebElement continueBtn = driver.findElement(By.cssSelector(".btn.btn-success.close-modal"));
						continueBtn.click();

					} else {
						// For last item, click View Cart in the popup
						WebElement viewCartBtn = driver.findElement(By.partialLinkText("View Cart"));
						viewCartBtn.click();
					}

					itemFound = true;
					break;
				}
			}

			if (!itemFound) {
				System.out.println("Item not found: " + item);
			}
		}

		driver.findElement(By.linkText("Proceed To Checkout")).click();
		//In the Cart click the Proceed To Checkout
		driver.findElement(By.linkText("Place Order")).click();
		//In the Checkout page click the Place Order button
		driver.findElement(By.className("form-control")).sendKeys("178@gmail.com");
		//In the payment this will enter the Full name of the cardholder
		driver.findElement(By.name("card_number")).sendKeys("178");
		//In the payment this will enter the number of the card number
		driver.findElement(By.name("cvc")).sendKeys("178");
		//In the payment this will enter the CVV of the card
		driver.findElement(By.name("expiry_month")).sendKeys("178");
		//In the payment this will enter the expiry month
		driver.findElement(By.name("expiry_year")).sendKeys("2088");
		//In the payment this will enter the expiry year
		driver.findElement(By.id("submit")).click();
		//In the payment this submit the card detail we entered
		driver.findElement(By.linkText("Download Invoice")).click();
		//Once the payment is successful it will click Download Invoice page and Download the invoice
		driver.findElement(By.linkText("Continue")).click();
		//This will click the Continue button and land into the home page
		driver.findElement(By.linkText("Logout")).click();
		//This will click the logout button and the session will logout
		driver.close();
	}
}
