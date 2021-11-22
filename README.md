User story:

Day 1)  
You are assigned to test the "Guru99 Bank" project  
Here is a link to the AUT: http://demo.guru99.com/V4/  
The following test case needs to be automated: img_1

Steps to get access to test site:  
	Visit http://demo.guru99.com/  
	Enter your email id  
	Login credentials are allocated to you and mailed at your id  
	Login credentials are only valid for 20 days!

Day 2)  
There is a major flaw in the test case we created - there is no verification step  
The script is no-good if it can't produce Pass/Fail status  
Go ahead and modify the script: img_2  

There is another major flaw - the script is not production ready  
Here is how to make the script production ready:  
	A Chrome profile for testing must be created  
	(A profile is a collection of bookmarks, browser settings, extensions, passwords and history
	- all your personal settings. Browser uses a default profile to store them.
	If something goes wrong with the testing, you still have your default profile to fall back to
	- your personal data still safe.)
	 
	The amount of time the driver should wait when searching for a GUI element should be specified
	The code to setup and launch driver should be created as a separate method
	All script initialization parameters like location of driver, Home URL, User Name, Password etc.
	should be stored in a separate file (Params.java)

Day 3)
The client has enhanced the test scenario to include more test cases: img_3
The best technique to automate the above test suite is to parametrize the script
The most famous tool to store data for parametrization is MS Excel
Go ahead and create a script to read test-data from an Excel and execute Webdriver test cases
