package com.example.jpa.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.jpa.entity.Employee;

@Repository
public class EmployeeDalImpl {

	private final String UPDATE_EMPLOYEE_QUERY = "UPDATE Employee SET name=?1,gender=?2,experienceInYears=?3,experienceInMonths=?4,experienceInDays=?5 WHERE id=?6";
	private final String DELETE_EMPLOYEE_QUERY = "DELETE FROM Employee WHERE id=?1";
	private final String GET_EMPLOYEE_QUERY = "SELECT employee FROM Employee";
	private final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT employee FROM Employee employee WHERE id =?1";
	private final String GET_EMPLOYEE_BY_YEARS_OF_EXPERIENCE = "SELECT employee FROM Employee employee WHERE experienceInYears=?1";
	private final String GET_EMPLOYEE_BY_DAYS_OF_EXPERIENCE = "SELECT * FROM employee WHERE experience_in_days=?1";
	private final String GET_EMPLOYEE_BETWEEN_YEARS_OF_EXPERIENCE = "SELECT employee FROM Employee employee WHERE experience_in_years BETWEEN ?1 and ?2";
	private final String GET_EMPLOYEE_COUNT = "SELECT COUNT(employee) FROM Employee employee";
	private final String GET_MAXIMUM_EXPERIENCE_IN_YEARS = "SELECT MAX(experienceInYears) FROM Employee";
	private final String GET_MINIMUM_EXPERIENCE_IN_YEARS = "SELECT MIN(experienceInYears) FROM Employee";
	private final String GET_EMPLOYEE_SORTED_BY_YEAR = "SELECT employee FROM Employee employee ORDER BY experienceInYears DESC";

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void createEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	@Transactional
	public void updateEmployee(Employee employee, int id) {

		Query updateQuery = entityManager.createQuery(UPDATE_EMPLOYEE_QUERY).setParameter(1, employee.getName())
				.setParameter(2, employee.getGender()).setParameter(3, employee.getExperienceInYears())
				.setParameter(4, employee.getExperienceInMonths()).setParameter(5, employee.getExperienceInDays())
				.setParameter(6, id);
		updateQuery.executeUpdate();
	}

	@Transactional
	public void deleteEmployee(int id) {

		Query deleteQuery = entityManager.createQuery(DELETE_EMPLOYEE_QUERY).setParameter(1, id);
		deleteQuery.executeUpdate();
	}

	@Transactional
	public List<Employee> getAllEmployees() {

		List<Employee> employees = new ArrayList<>();
		Query getAllQuery = entityManager.createQuery(GET_EMPLOYEE_QUERY);
		employees = (List<Employee>) getAllQuery.getResultList();
		return employees;
	}

	@Transactional
	public Employee getEmployeeById(int id) {

		Employee employee = new Employee();
		Query getQueryById = entityManager.createQuery(GET_EMPLOYEE_BY_ID_QUERY).setParameter(1, id);
		employee = (Employee) getQueryById.getSingleResult();
		return employee;
	}

	@Transactional
	public List<Employee> getEmployeeByYearsOfExperience(int years) {

		List<Employee> employees = new ArrayList<>();
		TypedQuery<Employee> getQueryByYear = entityManager
				.createQuery(GET_EMPLOYEE_BY_YEARS_OF_EXPERIENCE, Employee.class).setParameter(1, years);
		employees = getQueryByYear.getResultList();
		return employees;
	}

	@Transactional
	public List<Employee> getEmployeeByMonthsOfExperience(int months) {

		List<Employee> employees = new ArrayList<>();
		Query getQueryByMonth = entityManager.createNamedQuery("findEmployeeByMonthsOfExperience").setParameter(1,
				months);
		employees = (List<Employee>) getQueryByMonth.getResultList();
		return employees;
	}

	@Transactional
	public List<Employee> getEmployeeByDaysOfExperience(int days) {

		List<Employee> employees = new ArrayList<>();
		Query getQueryByDay = entityManager.createNativeQuery(GET_EMPLOYEE_BY_DAYS_OF_EXPERIENCE).setParameter(1, days);
		employees = (List<Employee>) getQueryByDay.getResultList();
		return employees;
	}

	@Transactional
	public List<Employee> getEmployeeBetweenYearsOfExperience(int start, int end) {

		List<Employee> employees = new ArrayList<>();
		Query getQueryBetweenYear = entityManager.createQuery(GET_EMPLOYEE_BETWEEN_YEARS_OF_EXPERIENCE)
				.setParameter(1, start).setParameter(2, end);
		employees = (List<Employee>) getQueryBetweenYear.getResultList();
		return employees;
	}

	@Transactional
	public Map<String, Object> getEmployeeStats() {

		Map<String, Object> employeeStats = new HashMap<>();
		Query employeeCount = entityManager.createQuery(GET_EMPLOYEE_COUNT);
		Query maximumExperienceInYear = entityManager.createQuery(GET_MAXIMUM_EXPERIENCE_IN_YEARS);
		Query minimumExperienceInYear = entityManager.createQuery(GET_MINIMUM_EXPERIENCE_IN_YEARS);
		employeeStats.put("employeeCount", (Long) employeeCount.getSingleResult());
		employeeStats.put("maximumExperienceInYear", (Integer) maximumExperienceInYear.getSingleResult());
		employeeStats.put("minimumExperienceInYear", (Integer) minimumExperienceInYear.getSingleResult());
		return employeeStats;
	}

	@Transactional
	public List<Employee> getEmployeeSortedByYearExperience() {

		List<Employee> employees = new ArrayList<>();
		Query getQuerySortedByYear = entityManager.createQuery(GET_EMPLOYEE_SORTED_BY_YEAR);
		employees = (List<Employee>) getQuerySortedByYear.getResultList();
		return employees;
	}

}
