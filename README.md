# JetsProject

#### Description

- This is the 3rd week homework assignment for Skill Distillery, Full Stack Development.

- This application will be a menu based command line interface to use a Jets entity/class inheritance structure and access information about them.

#### Technologies Used
	- JAVA
	- Eclipse
	- Git/GitHub
	- Sublime Text Editor
	- Zsh

#### Lessons Learned
    - Fully understand and Practice OOP
    - A P I E
    - Extending classes from super classes
    - Implementing interfaces
    - Using ArrayList and other Data Structures
    - instanceof practical use
    - Tons of OOP practice, overrides, etc
    - OOP reference returning clone importance
    - Improve commit messages for better history
    - Commit message should be feature related if possible
    
#### Considerations
	- I implemented most the UI/UX in the JetsApplication.
	- Presentation Layer, Business Layer, Data Layer ideas.
    
#### Object references using List<Jet>

- NOTE: Additional effort was placed on the return of this List<Jet> method to ensure that only a cloned copy of the Jet entities is passed to the calling application.

- This will ensure the stored data in the AirField class is not mutated from the outside, but by providing a full clone of the List<Jet>, some utility is provided to the calling application.

- Strict attention to detail and evaluation of these returns and their exposure will be needed for real-world applications.

- Paying special attention to the return of objects in public methods is an important best practice.

#### tldr;

- While creating the AirField class, I was intent on providing additional functionality around the return of the different methods.  Perhaps this was because I was focusing on separating the Business Logic with the presentation logic.  This led to me implementing more than just System out messages from the variety of methods and considering returning the sub-set collections of data certain methods inherently would yeild.
- For example, if the AirField should find the fastest Jet's, more than just displaying them in a strict System out fashion, I decided to return the List<Jet> fastestJets which would be a new list of cloned versions from the underlying AirField fleet List.  In doing so, it identified the need to be safe in returning references to the underlying AirField data.  This led me to create a simple cloneJet method that would be helpful.

#### Beyond the Call of Duty

- I created a separate branch (webView) with extra features.
	- Generates an HTML file to view appropriate Bootstrap cards, appropriately rendering the lists of Jet data.
- Views results in web browser.
	- Spawns web-browser to view the generated HTML documents.
- Menu choices are integrated for this behavior.
	- Reports for web view are auto-generated by Menu choices and reflect custom selected data.  Load Cargo - Will generate web view of just Cargo planes, etc.
- Advanced GIT techniques, Separate branch with Draft Pull Request.
- Merged this feature branch to main, and added flag to disable these extra .HTML file creations.

You can view additional branch README file and code at this link: [Web View Example](https://github.com/pasciaks/JetsProject/tree/webView)

```JAVA

		// NOTE: A cloned list is returned, and NOT an original reference
		List<Jet> jets = airField.loadJetsFromFile("jets.txt");
```
	
	
#### Beyond the call of duty

- A separate branch is created that holds additional modifications such as the ability to generate an HTML view of the various filtering of the Jets.

- By simply invoking the List, Fastest, Longest Range, Load Cargo, or Fight methods, an additional .html file is generated with bootstrap styling and a card list rendered view of the appropriate results.

![Example usage](web-view-reports.png)
	
#### How to run this program : This is a .java source code, command line project.  

You could open the project in an IDE and execute the main method using the IDE tools.

You can compile the project (.java) file on the command line, and then execute the compiled code.

The compilation and execution in the command line is subject to the proper working directory and installed JAVA Compiler and specific platform.



<hr>

[About The Developer](https://github.com/pasciaks/)