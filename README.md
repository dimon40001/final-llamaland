# Swiss Re Code Exercise I
Llamaland is a new country. It is a monarchy with King Tom I as its head.

When a citizen of Llamaland turns 100 years old the King would like to send them a personal email. He would like to be notified who should be sent an
email at least five weekdays in advance (the King never works weekends). If there are "a lot" of people turning 100 years old on a particular day (a term
we now understand to mean more than 20 people) the King would like 10 weekdays notice.

The civil servants have a file which is updated every year. It contains the names, dates of birth and email addresses of every citizen. An extract is below:
```
Brown,Bobby,10-11-1950,bobby.brown@ilovellamaland.com
O'Rourke,Betsy,28-02-1900,betsy@heyitsme.com
Von Tappo,Alfredo,01-01-1920,alfie@vontappo.llama.land
```
Some citizens do not support the monarchy and have opted out of receiving the mail. Their email address appears in another file. An extract of the file is
below:
```
betsy@heyitsme.com
randomperson@llama.land
```
An e-mail from the King is extremely valued in Llamaland. To prevent abuse each citizen should have a unique email address. Unfortunately the IT
system to capture citizen's details was built by the King's son. It lacks a duplicate check on the e-mail address. Rather than tackle this problem at the
source, the King's Courtiers have suggested duplicate addresses should be filtered out, such that no-one using a duplicate mail should receive an e-mail
from the King.

Your task is to write a simple program that the King‘s chief of staff will execute every day. The output should be a list of people the King should prepare an
e-mail for.

**Key Points**
- You should use Maven to for dependency management and to enforce a common structure
- Write the solution using only Java SE (any version) and Junit (any version)
- Test Driven Development (TDD) is the recommended approach
- Code will be assessed based on accuracy, simplicity, readability and cleanliness
- If you have any doubts make a sensible assumption and document it
- Nobody reads until here (hopefully)
- No Graphical User Interface is required, infact no User Interface is required at all. This is a simple program which takes in two files and produces
a result. Writing the result to standard out is perfectly acceptable
- We prefer to receive the code with a link to a github or Bitbucket repository

## Further improvements
- introduce marshaller class. So far the repository incorporates the parsing knowledge and takes care of reading the data (we don't save to the repository)
- implement EmailValidator in a way to support email standards of Llamaland
- introduce Citizens validator
- consider replacing System.out.println() calls with the logger.

## Notes and Assumptions
- Citizens are loaded even with invalid emails, they are just filtered out later when distribution list is prepared

## How to use
Application is mainly intended for command-line use:

`java -jar llamaland-0.0.1.jar <citizens_filename> <optout_emails_filename>`

Example: `java -jar llamaland-0.0.1.jar ..\src\test\resources\citizens.txt ..\src\test\resources\optout.txt`

Start program without parameters to see the brief instructions.
