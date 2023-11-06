@SW_InterviewTest
Feature: Automate the Login page and Some funcionality of Filtering, Add to Cart, Remove and logout (Sauce lab)
  I want to Automate the Login page with the help of the Decision Table technique and then Automate the Filtering and Add to Card funcionality

  #Background using because of the Same Runner Page on each Scenario
  Background: Opening the Sauce lab Application
    Given Open the sauce lab application
    Then Verify that the Login page is Appeared or not

  @SaucelabLoginPage
  Scenario: Test the login page using the Decision Table Technique
    Given Without Entering any Data click on Login Btn
    Then Verify that the UserName Validation is appeared
      | Epic sadface: Username is required |
    Then Enter the UserName and Click on Login Btn
    Then Verify that the Password Required Validation is Appeared
      | Epic sadface: Password is required |
    Then Enter the UserName and Password and Verify that the validation is working properly for ENtering Wrong user Name and Password
      | UserName         | Password         | ValidationMsg                                                             |
      | InvalidUserName  | secret_sauce     | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user  | InvalidPassword  | Epic sadface: Username and password do not match any user in this service |
      | Invalid_UserName | Invalid_Password | Epic sadface: Username and password do not match any user in this service |
    Then Verify that Case Sensitive is working
      | Standard_User | secret_sauce | Epic sadface: Username and password do not match any user in this service |
    Then Enter the Correct UserName Password and Login to the Application
    And Verify that the user is redirect to the Inventory Page

  @AddOrRemoveItems
  Scenario: Test The Filtering Product
    Given Enter the Correct UserName Password and Login to the Application
    Then Check by default sorting order from A to Z are selected
    Then Add to Cart Some Inventory Items and Before add to cart Get the Amount of that particular Product
      | ItemsName                         |
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
    Then Click on the Cart Button which is on the Header top left
    Then Verify that all Item which we selected are on the cart List and amount is appearing Correctly
      | ItemsName                         |
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
    Then Remove Some Selected Item
      | ItemsName                         |
      | Test.allTheThings() T-Shirt (Red) |
    And Click On Check Out Button
