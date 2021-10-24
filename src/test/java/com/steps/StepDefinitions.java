package com.steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.pageObjects.Productpage;
import com.utils.WebdriverConfigs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
	 WebdriverConfigs driverManager;
	 Productpage product;
	 public static Properties prop = new Properties();
	 Scenario scenario;
	 

	    
	    public StepDefinitions(WebdriverConfigs driverManager){
	    	loadProperties();
	        this.driverManager = driverManager;
	        product = new Productpage(driverManager);
	        
	    }
	    
	 public void loadProperties() {
		 try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			 e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 @Before
	 public void testSetup(Scenario scenario) {
		 this.scenario = scenario;
		 
	 }
	
	 @Given("User is on homepage")
	public void user_is_on_homepage() {
		 loadProperties();
		 driverManager.getDriver().get(prop.getProperty("url"));
	}
	 
	 @Given("I add {string} different products to my wishlist")
	 public void i_add_four_different_products_to_my_wishlist(String productCount) {
		 product.addProductToCart(productCount);
	 }
	 
	 @When("I view my wishlist table")
	 public void i_view_my_wishlist_table() {
		 product.clickOnWishlist();
	 }
	 
	 @Then("I find total four selected items in my wishlist")
	 public void i_find_total_four_selected_items_in_my_wishlist() {
		 product.wishListTable();
	 }
	 
	 @When("I search for lowest price product")
	 public void i_search_for_lowest_price_product() {
		 product.getLowPriceProduct();
	 }
	 
	 @When("I am able to add the lowest price item to my cart")
	 public void i_am_able_to_add_the_lowest_price_item_to_my_cart() {
		 product.addLowPriceproduct();
	 }
	 
	 @Then("I am able to verify the item in my cart")
	 public void i_am_able_to_verify_the_item_in_my_cart() {
		 product.verifyItemInmyCart();
	 }
	 
	 @AfterStep
	 public void takeScreenshot() {
		 TakesScreenshot screenshot = (TakesScreenshot) driverManager.getDriver();
		 byte[] byteScreenshot = screenshot.getScreenshotAs(OutputType.BYTES);
		 scenario.attach(byteScreenshot, "image/png", "screenshot"); 
	 }
	 
	 @After
	 public void tearDown(){
		 driverManager.getDriver().quit();
	 }

}
