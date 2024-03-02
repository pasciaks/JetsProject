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