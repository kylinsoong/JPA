package org.jboss.demo.jpa.model.util;

import java.util.Arrays;
import java.util.Random;

import javax.persistence.EntityManager;

import org.jboss.demo.jpa.model.Address;
import org.jboss.demo.jpa.model.Employee;
import org.jboss.demo.jpa.model.EmploymentPeriod;
import org.jboss.demo.jpa.model.Gender;

/**
 * Examples illustrating the use of JPA with the employee domain
 * 
 */
public class SamplePopulation {
	
	public static SamplePopulation getInstance() {
		return new SamplePopulation();
	}

    /**
     * Create the specified number of random sample employees.  
     */
    public void createNewEmployees(EntityManager em, int quantity) {
        for (int index = 0; index < quantity; index++) {
            em.persist(createRandomEmployee());
        }
    }
    
    public void createNewAddress(EntityManager em, int quantity) {
    	for (int index = 0; index < quantity; index++) {
            em.persist(new Address("BJ", "CNA", "BJ", "100020", "DDQ"));
        }
    }

    private static final String[] MALE_FIRST_NAMES = { "Jacob", "Ethan", "Michael", "Alexander", "William", "Joshua", "Daniel", "Jayden", "Noah", "Anthony" };
    private static final String[] FEMALE_FIRST_NAMES = { "Isabella", "Emma", "Olivia", "Sophia", "Ava", "Emily", "Madison", "Abigail", "Chloe", "Mia" };
    private static final String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson" };

    public Employee createRandomEmployee() {
        Random r = new Random();

        Employee emp = new Employee();
        emp.setGender(Gender.values()[r.nextInt(2)]);
        if (Gender.Male.equals(emp.getGender())) {
            emp.setFirstName(MALE_FIRST_NAMES[r.nextInt(MALE_FIRST_NAMES.length)]);
        } else {
            emp.setFirstName(FEMALE_FIRST_NAMES[r.nextInt(FEMALE_FIRST_NAMES.length)]);
        }
        emp.setLastName(LAST_NAMES[r.nextInt(LAST_NAMES.length)]);
        emp.addPhoneNumber("HOME", "111", "5552222");
        emp.addPhoneNumber("WORK", "222", "5552222");
        emp.setAddress(new Address("BJ", "CNA", "BJ", "100020", "DDQ"));
        emp.setResponsibilities(Arrays.asList(new String[]{"JBoss Data Virtualization Dev", "Blog"}));
        emp.setSalary(300000);
        EmploymentPeriod peroid = new EmploymentPeriod();
        peroid.setStartDate(2008, 3, 5);
        peroid.setEndDate(2018, 3, 6);
        emp.setPeriod(peroid);

        return emp;
    }
}
