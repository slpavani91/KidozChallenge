package com.kidoz.vacationbonus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;



public class VacationDays {
    private final Map<String,AddressBook> empAddressLookUp;
    private final Map<String,Employee> empInfoLookUp;
    private final SimpleDateFormat simpleDateFormat;

    private final LocalDate todayDate;

    public VacationDays() {
        empAddressLookUp=new HashMap<String, AddressBook>();
        empInfoLookUp=new HashMap<String, Employee>();
        todayDate = LocalDate.now();
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    }


    	private Payroll getPayRoll(String id) {
    		Payroll payroll = new Payroll();
    		payroll.setEmp_id(id);
    		payroll.setVacationDays(13);
    		return payroll;
    	}

    	private AddressBook getAddressBook(String id) {
    		AddressBook addressBook = new AddressBook();
    		addressBook.setEmp_id(id);
    		addressBook.setEmail("abc@email.com");
    		return addressBook;
    	}

    	private Employee getEmployee(String id) throws ParseException {
    		Employee employee = new Employee();
    		employee.setId(id);
    		employee.setName("Employee "+ id);
    		employee.setStartDate(simpleDateFormat.parse("2000-12-01"));
    		return employee;
    	}

    public static void main(String[] args) {
        VacationDays bonusDays=new VacationDays();
        final long startTime = System.currentTimeMillis();
        List<Payroll> payrollList = new ArrayList<>();
        payrollList.add(bonusDays.getPayRoll("1"));
		payrollList.add(bonusDays.getPayRoll("2"));
		payrollList.add(bonusDays.getPayRoll("3"));
		payrollList.add(bonusDays.getPayRoll("4"));
		payrollList.add(bonusDays.getPayRoll("5"));
		payrollList.add(bonusDays.getPayRoll("6"));
		
		List<AddressBook> addressBookList = new ArrayList<>();
		addressBookList.add(bonusDays.getAddressBook("1"));
		addressBookList.add(bonusDays.getAddressBook("2"));
		addressBookList.add(bonusDays.getAddressBook("3"));
		addressBookList.add(bonusDays.getAddressBook("4"));
		addressBookList.add(bonusDays.getAddressBook("5"));
		addressBookList.add(bonusDays.getAddressBook("6"));
		
		List<Employee> employeeList = new ArrayList<>();
		try {
			employeeList.add(bonusDays.getEmployee("1"));
			employeeList.add(bonusDays.getEmployee("2"));
			employeeList.add(bonusDays.getEmployee("3"));
			employeeList.add(bonusDays.getEmployee("4"));
			employeeList.add(bonusDays.getEmployee("5"));
			employeeList.add(bonusDays.getEmployee("6"));
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        bonusDays.grantVacation(payrollList,addressBookList,employeeList);
        final long endTime = System.currentTimeMillis();
        System.out.println("execution Time in ms: " + (endTime - startTime));
    }

    private void grantVacation(List<Payroll> payroll,List<AddressBook> addressBookList,List<Employee> employeeList){
        generateMapForAddressAndEmployeeInfo(addressBookList,employeeList);
        for (Payroll payroll1: payroll) {
            String empId = payroll1.getEmp_id();
            Employee employee = empInfoLookUp.get(empId);
            AddressBook addressBook = empAddressLookUp.get(empId);
            int yearsEmployed = yearsSince(employee.getStartDate());
            int newVacationBalance = yearsEmployed + payroll1.getVacationDays();
            EmailApi.sendEmail(addressBook.getEmail(),"Good news!",formatEmailBody(employee.getName(),yearsEmployed,newVacationBalance));
        }
    }

    //Generating a map of employees and their address based on employee id
    private void generateMapForAddressAndEmployeeInfo(List<AddressBook> addressBookList,List<Employee> employeeList){
        //Assuming both address and employee same size,If the size is different for employees or address we need to iterate remaining indices in different loop
        for(int index=0;index < addressBookList.size();index++){
            empInfoLookUp.put(employeeList.get(index).getId(),employeeList.get(index));
            empAddressLookUp.put(addressBookList.get(index).getEmp_id(),addressBookList.get(index));
        }
    }

    private int yearsSince(Date startDate){
        LocalDate startDateVal = LocalDate.parse(simpleDateFormat.format(startDate));
        Period period = Period.between(startDateVal, todayDate);
        System.out.printf("%d years %d months %d days%n",period.getYears(), period.getMonths(), period.getDays());
        //If we need we can use period.getMonths() to calculate orphaned months period.getYears(), period.getMonths(), period.getDays()
        return period.getYears();
    }

    private String formatEmailBody(String name,int yearsEmployed,int newVacationBalance){
        return String.format("Dear %s\nbased on your %d years of employment, you have been granted %d days of vacation, bringing your total to %d",name,yearsEmployed,yearsEmployed,newVacationBalance);
    }

    class AddressBook {
        private String emp_id;
        private String first;
        private String last;
        private String email;

        public String getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(String emp_id) {
            this.emp_id = emp_id;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
    class Payroll {
        private String emp_id;
        private Integer vacationDays;

        public String getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(String emp_id) {
            this.emp_id = emp_id;
        }

        public Integer getVacationDays() {
            return vacationDays;
        }

        public void setVacationDays(Integer vacationDays) {
            this.vacationDays = vacationDays;
        }
    }
    class Employee {
        private String id;
        private String name;
        private Date startDate;
        private Date endDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

}
