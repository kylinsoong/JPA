package org.jboss.demo.jpa.model;

import static javax.persistence.TemporalType.DATE;

import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@Embeddable
public class EmploymentPeriod {
        
        @Temporal(DATE)
	private Calendar startDate;
        @Temporal(DATE)
	private Calendar endDate;

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public void setStartDate(int year, int month, int date) {
		if (this.startDate == null) {
			setStartDate(Calendar.getInstance());
		}
		getStartDate().set(year, month, date);
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public void setEndDate(int year, int month, int date) {
		if (this.endDate == null) {
			setEndDate(Calendar.getInstance());
		}
		getEndDate().set(year, month, date);
	}
}
