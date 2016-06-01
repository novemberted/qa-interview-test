# ONTRAPORT QA Test

Hello and welcome. In order to gauge the ability of our QA applicants, we’ve put together a brief test. It is comprised of three parts; one to test planning, execution and bug documentation ability, a second to measure skills with Java and Selenium WebDriver and finally, a few written questions about Java and OOP principles. The test is “open book” in that you have full access to the internet at your disposal. There is no time limit on this test, but we would like to know how long it took you, so please let us know when you submit your answers. For the written answers (parts 1 and 3), you may include the answers as a text file in your PR.

# Part 1:
Navigate to http://qatest.pages.ontraport.net

This is the account creation section of the app. Create a test plan around this page. There is no spec available, so you’ll have to create a test plan based on what you have available.
Using this test plan, test the page. Document any bugs you find.

# Part 2:
Fork this repo to your personal GitHub account
Write a test that does the following:

1. Navigate to the same page as above
2. Fill out the form
3. Click submit
4. Validate that you’re redirectedto the pricing page
5. Validate that the price (597) appears in the table

For #5, your page class should include two methods
* One that returns a List of the table rows as WebElements
* A second that iterates through that list and returns whether or not a price appears on the page

# Part 3:
1. Briefly describe object oriented programming.
2. What is inheritance? Give an example.
3. In Java, what is the difference between runtime and handled exceptions?
