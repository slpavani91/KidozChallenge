package com.kidoz.vacationbonus;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

class AddressBook {

	String emp_id;
	String first;
	String last;
	String email;

	public AddressBook(String empID, String firstName, String lastName, String emailId) {
		// TODO Auto-generated constructor stub
		emp_id = empID;
		first = firstName;
		last = lastName;
		email = emailId;
	}
}

class Payroll {
	String emp_id;
	int vacationDays;

	public Payroll(String id, int days) {
		// TODO Auto-generated constructor stub
		emp_id = id;
		vacationDays = days;
	}
}

class Employee {
	public Employee(String empId, String name, Date start, Date end) {
		// TODO Auto-generated constructor stub
		id = empId;
		this.name=name;
		startDate =start;
		endDate = end;
	}

	String id;
	String name;
	Date startDate;
	Date endDate;
}

class EmailApiSync{
	
	static void sendEmails(String email, String subject, String body) {
		System.out.printf("%s\n%s\n%s%n",email,subject,body);
	}

	
}
public class BonusVacation {

	int yearsSince(Date startDate, Date today) {	
		long millisecondsPerYear = 365 * 24 * 60 * 60 * 1000l;
		return (int) ((today.getTime() - startDate.getTime()) / millisecondsPerYear);
	}

	/**
	 * We have decided to grant bonus vacation to every employee, 1 day per year of
	 * experience we need to email them a notice.
	 */
	void grantVacation(List<Payroll> payrollList, List<AddressBook> addresses, List<Employee> employees) {
		for (Payroll payroll : payrollList) {
			// Payroll payrollInfo = payroll;
			AddressBook addressInfo = addresses.stream().filter(x -> x.emp_id.equals(payroll.emp_id)).findAny()
					.orElse(null); // findfirst  hashmap
			Employee empInfo = employees.stream().filter(x -> x.id.equals(payroll.emp_id)).findAny().orElse(null); // hashmap
			Date today = new Date();
			int yearsEmployed = yearsSince(empInfo.startDate, today); // it should be start date
			int newVacationBalance = yearsEmployed + payroll.vacationDays;
			EmailApiSync.sendEmails(addressInfo.email, "Good news!",
					"Dear " + empInfo.name + " based on your  " + yearsEmployed
							+ " years of employment, you have been granted " + yearsEmployed
							+ " days of vacation, bringing your total to " + newVacationBalance);
		}
	}
	

	public static void main(String[] args) throws ParseException {
		BonusVacation bonusVacation= new BonusVacation();
		final long startTime = System.currentTimeMillis();
		// TODO Auto-generated method stub
		List<Payroll> payrollList = new ArrayList<>();
		List<AddressBook> addressList = new ArrayList<>();
		List<Employee> employeeList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		payrollList.add(new Payroll("1", 2));
		payrollList.add(new Payroll("2", 3));
		payrollList.add(new Payroll("3", 4));
		payrollList.add(new Payroll("4", 5));
		payrollList.add(new Payroll("5", 6));

		addressList.add(new AddressBook("1", "ben", "kidoz", "ben@kidoz.com"));
		addressList.add(new AddressBook("2", "ken", "kidoz", "ken@kidoz.com"));
		addressList.add(new AddressBook("3", "yen", "kidoz", "yen@kidoz.com"));
		addressList.add(new AddressBook("4", "chen", "kidoz", "ben@kidoz.com"));
		addressList.add(new AddressBook("5", "pat", "kidoz", "ben@kidoz.com"));

		employeeList.add(new Employee("1", "ben", simpleDateFormat.parse("2015-02-20"), new Date(0)));
		employeeList.add(new Employee("2", "ken", simpleDateFormat.parse("2018-02-21"), new Date(0)));
		employeeList.add(new Employee("3", "yen", simpleDateFormat.parse("2007-02-22"), new Date(0)));
		employeeList.add(new Employee("4", "chen", simpleDateFormat.parse("2005-02-23"), new Date(0)));
		employeeList.add(new Employee("5", "pat", simpleDateFormat.parse("2007-02-25"), new Date(0)));

		final long endTime = System.currentTimeMillis();
		bonusVacation.grantVacation(payrollList, addressList, employeeList);
		System.out.println("execution Time in ms: " + (endTime - startTime));
	}

}
