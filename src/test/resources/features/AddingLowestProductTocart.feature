Feature: Testing functionality of testscript website


  Scenario Outline:  Verify lowest product in cart is added
		Given User is on homepage
		And I add "<count>" different products to my wishlist
		When I view my wishlist table
		Then I find total four selected items in my wishlist
		When I search for lowest price product
		And I am able to add the lowest price item to my cart
		Then I am able to verify the item in my cart
		
		Examples:
		| count |
		| 4			|
    
  