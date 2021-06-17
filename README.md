# Kenzan-Code-Project
The Employee REST API for the Kenzan Back End Developer position
I made a guide with pictures on Google Docs, to help supplement: https://docs.google.com/document/d/1mAny7WMDlg6JK0XKMtjZHAmP54LUPbsRmJN6W4sNSI8/edit?usp=sharing
This is my first time working with Spring and HTTP endpoints, there are comments in the java files that talk about my decisions. Those java files can be found in the src folder

# How to run
This project was made using Java, Spring, and Gradle
1. Open up terminal/command prompt and navigate into this directory
2. (Optional) In the root folder, you can locate employees.json, and replace it with your own, assuming that it follows the same schema. I also used random UUIDs for the id portion, and if a UUID is not specified one will randomly be assigned.
3. Start up the web server by typing in "gradlew bootRun", Spring information should start up
4. Open up any browser and type in "localhost:8080/api/v1/employee" into the address bar and hit enter, this is what loads the JSON as well as gets all active employees

# How to use
In order to work with HTTP endpoints, I ended up using Insomnia, found here: https://insomnia.rest/, but feel free to use something else that can manipulate endpoints and JSON body sections. A lot of what I mention below is more for me to remember how I did it later on.

Here's where I advise checking out the google doc for pictures on what exactly it looks like. However I will give the layout of each request that gets sent out.

Get employees by an ID
Utilizing GET endpoint, attach the UUID at the end of the url and send it

Create new employees
Utilizing POST
Set up the JSON's body to look like this: (It didn't format the way I wanted it to, check out the google doc), then hit send. Nothing should return, but you should get a 200 code which means it worked. If you want to specify a specific UUID, add ' "id": "[Insert UUID here]", ' right above the firstName property. Otherwise it will be randomized.
{
	"firstName": "John",
	"middleInitial": "A",
	"lastName": "Doe",
	"dateOfBirth": "07/23/1999",
	"dateOfEmployment": "06/28/2021",
	"active": true
}

Update existing employees
Utilizing PUT
Set up the JSON body just like Create new employees, even if you only change one element. I couldn't figure out how to keep the object's values as default so that you only needed to put the name of the property and what you wanted to change instead of the whole thing.

Delete employees
Utilizing PATCH
Attach the UUID at the end of the url and hit send.

Get all employees
Utilizing GET
It should be the default as long as you're in the "localhost:8080/api/v1/employees" path, just hit send. 
