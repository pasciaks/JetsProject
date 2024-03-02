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
    
#### Object references using List<Jet>

- NOTE: Additional effort was placed on the return of this List<Jet> method to ensure that only a cloned copy of the Jet entities is passed to the calling application.

- This will ensure the stored data in the AirField class is not mutated from the outside, but by providing a full clone of the List<Jet>, some utility is provided to the calling application.

- Strict attention to detail and evaluation of these returns and their exposure will be needed for real-world applications.

- Paying special attention to the return of objects in public methods is an important best practice.

#### tldr;

- While creating the AirField class, I was intent on providing additional functionality around the return of the different methods.  Perhaps this was because I was focusing on separating the Business Logic with the presentation logic.  This led to me implementing more than just System out messages from the variety of methods and considering returning the sub-set collections of data certain methods inherently would yeild.
- For example, if the AirField should find the fastest Jet's, more than just displaying them in a strict System out fashion, I decided to return the List<Jet> fastestJets which would be a new list of cloned versions from the underlying AirField fleet List.  In doing so, it identified the need to be safe in returning references to the underlying AirField data.  This led me to create a simple cloneJet method that would be helpful.


```JAVA

		// NOTE: A cloned list is returned, and NOT an original reference
		List<Jet> jets = airField.loadJetsFromFile("jets.txt");
```
	
#### How to run this program : This is a .java source code, command line project.  

You could open the project in an IDE and execute the main method using the IDE tools.

You can compile the project (.java) file on the command line, and then execute the compiled code.

The compilation and execution in the command line is subject to the proper working directory and installed JAVA Compiler and specific platform.



<hr>

[About The Developer](https://github.com/pasciaks/)