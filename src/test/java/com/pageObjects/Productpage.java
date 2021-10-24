package com.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utils.WebdriverConfigs;

public class Productpage extends BasePage {

	ArrayList<String> productList = new ArrayList<String>();
	ArrayList<Double> price = new ArrayList<Double>();
	double lowestProdPrice =0;
	String lowestProdPriceName = null;

	public Productpage(WebdriverConfigs driver) {
		super(driver);

	}

	@FindBy(xpath="//*[text()='Add to wishlist']")
	public List<WebElement> wshListButton;

	@FindBy(xpath="//*[@class='product-compare-wishlist d-compare-wishlist']/..//h2")
	public List<WebElement> lblProductName;

	@FindBy(xpath="(//*[@class='lar la-heart'])[1]")
	public WebElement btnWishList;

	@FindBy(xpath="//td[@class='product-name']/a")
	public List<WebElement> wishListtable;

	@FindBy(xpath="//td[@class='product-price']//bdi")
	public List<WebElement> priceListTble;
		
	@FindBy(xpath="(//*[@class='la la-shopping-bag'])[1]")
	public WebElement btnClickCart;
	
	@FindBy(xpath="//*[@class='product-name']//a")
	public WebElement lblAddedProduct;
	
	@FindBy(xpath="//*[@class='cc-btn cc-accept-all cc-btn-no-href']")
	public WebElement btnAcceptAll;
	
	@FindBy(xpath="//*[text()='Product added to cart successfully']")
	public WebElement lblProductAdded;
	
	
	public void addProductToCart(String productValue) {
		int totalCount = Integer.parseInt(productValue);
		btnAcceptAll.click();
		if (wshListButton.size() > 0) {
			for (int productCount = 0; productCount < totalCount; productCount++) {
				moveToProductImage(wshListButton.get(productCount));
				waitForElementToBeVisible(wshListButton.get(0));
				productList.add(lblProductName.get(productCount).getText());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				wshListButton.get(0).click();
			}
		}
	}

	public void clickOnWishlist() {
		moveToProductImage(btnWishList);
		btnWishList.click();
	}


	public void wishListTable() {
		int wishSize = wishListtable.size();
		for(int i=0;i<wishSize;i++) {
			String act = wishListtable.get(i).getText();
			String exp = productList.get(wishSize-(i+1));
			Assert.assertTrue(act.equals(exp));
		}
	}

	public void getLowPriceProduct() {
		for(int i = 0; i< priceListTble.size(); i++) {
			String act = priceListTble.get(i).getText().replace("£", "");
			Double val = Double.parseDouble(act);
			price.add(val);
		}
		int amountElement=0;
		
			for(int amountValue = 1; amountValue< price.size(); amountValue++) {
				if(price.get(amountElement)< price.get(amountValue)) {
					lowestProdPrice = price.get(amountElement);
				} else {
					amountElement=amountValue;
				}
				if(amountValue== price.size())
					break;
			}
			
		
	}
	
	public void addLowPriceproduct() {
		lowestProdPriceName = driver.findElement(By.xpath("//bdi[contains(text(),'"+ lowestProdPrice +"')]/../../../..//*[@class='product-name']/a")).getText();
		driver.findElement(By.xpath("//bdi[contains(text(),'"+ lowestProdPrice +"')]/../../../..//*[@class='product-add-to-cart']/a")).click();
		Assert.assertTrue(lblProductAdded.isDisplayed());
	}
	
	public void verifyItemInmyCart() {
		btnClickCart.click();
		Assert.assertTrue(lowestProdPriceName.equals(lblAddedProduct.getText()));
	}


}
