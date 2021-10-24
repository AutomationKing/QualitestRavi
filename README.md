# QualitestRavi

To use this Repo you need to clone or download this .
To clone use https://github.com/AutomationKing/QualitestRavi.git
Once imported to your local you can use maven commands 
mvn clean install to get all the dependencies .
This repo is build on Selenium Cucumber Java following Page Object Design Pattern .
This repo is divided into 5 packages which has pagObjects  , Runner , Steps for stepdefinition , Utils and resources 
To run this open the Runner file and run TestRunner



<img width="1776" alt="Screenshot 2021-10-24 at 18 44 12" src="https://user-images.githubusercontent.com/34009503/138606276-04aaa2df-8ce1-4752-9898-2139d10ab2db.png">
<img width="1465" alt="Screenshot 2021-10-24 at 18 44 48" src="https://user-images.githubusercontent.com/34009503/138606282-3f934a09-9d02-4b5f-a4e5-5bf85b7dc448.png">
<img width="1730" alt="Screenshot 2021-10-24 at 18 45 27" src="https://user-images.githubusercontent.com/34009503/138606283-40a9e835-5c40-4b03-bd5d-10d4d08dc54e.png">


OnGoing issues - Sometimes it hits "HTTP Status: '404' -> incorrect JSON status mapping for 'stale element reference' (400 expected)" and there is a bug logged on geckoDriver . If you face this issue ,  probably rerun for the execution to take place 

Solution
Upgrade JDK to recent levels JDK 8u191.
Upgrade Selenium to current levels Version 3.14.0.
Upgrade GeckoDriver to GeckoDriver v0.23.0 level.
GeckoDriver is present in the specified location.
GeckoDriver is having executable permission for non-root users.
Upgrade Firefox version to Firefox vv63.0.1 levels.
Clean your Project Workspace through your IDE and Rebuild your project with required dependencies only.
If your base Web Client version is too old, then uninstall it through Revo Uninstaller and install a recent GA and released version of Web Client.
Take a System Reboot.
Execute your Test as a non-root user.
Always invoke driver.quit() within tearDown(){} method to close & destroy the WebDriver and Web Client instances gracefully
