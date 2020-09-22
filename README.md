# KidozChallenge


This challenge is solved using **Java8 EJB Project** .
                              

The given typescript code is converted simple **BonusVacation.java**<br/>
Optimized class is **VacationDays.java**


Problems to fix in the given code:
=================================
1.Send emails asynchronouly to all employees<br/>
  Given problem sends email synchronously but sending emails asynchronously <br/>
 Advantages to asynchronous messaging:<br/>
  They enable flexibility and offer higher availability – There’s less pressure on the system to act on the information or immediately respond in some way. Also, one system being down does not impact the other system. In our case – you send thousands of emails without having employee to revert back to you. so we can also ignore ack a sense of immediacy in this situation.<br/>
  
2.Use of Java LocalDate for calculation of employee experience in years<br/>
Advantages of LocalDate<br/>
Readability:<br/>
Calendar.getInstance() is not very well named: it is hard to tell which instance you are getting without reading the Javadoc. LocalTime.now() is quite self-describing: you get a time and it is now.
To offset a date, you call an offsetting method (plus) whereas with the Calendar API, you have to manually change the fields of the object (in this example, the hour) which is error prone.<br/>
Ease of use (see for example the table towards the bottom of this page for a comparison):<br/>
the Calendar API is complicated to use because it mixes concepts, such as a simple date (June 26th 2015) and an instant in time (June 26th 2015 at 10am UTC) - there isn't a class for the former concept
The new Time API has a clear separation between the various date/time concepts<br/>
Safety:<br/>
The Calendar API is not safe: nothing prevents you from writing cal.set(123, 2) which would throw a not-so-helpful ArrayOutOfBoundsException. The new API uses enums which solves that problem.<br/>
The new API uses immutable objects, which makes it thread safe.

3.Creating a HashMap of employees and their addresses based on employee id<br/>
Advantage of HashMap <br/>
HashMap provides expected constant-time performance O(1) for most operations like add(), remove() and contains().<br/>
Streams are traversable only once. If you traverse the stream once, it is said to be consumed. To traverse it again, you have to get new stream from the source again. But, collections can be traversed multiple times.
 Given problem code is iterating 'n' times everytime in the for getting details employee email,name for each payroll  transaction calculation in grantvacation() method.
 i.e we are iterating 2 * O(n) for each address and employee details  for each employee. Complexity  O(n2) for N employees
 
Output
 ===
After optimizing the code with above changes the execution time reduced by about  ~ 60ms on an average for sample of 6 employees
Attaching screenshot of the execution time for both classes.



Assumptions
====
1. No implementation for sending  real emails. eg., like using smtp server<br/>
2. No null values passed in employee data
3. No validations are performed on the input data.

Requirements:
==========
1.Java <br/>
2.Eclipse IDE <br/>
3.Dependency jar(javax.ejb jar)<br/>
4.EJB project <br/>



 
 




