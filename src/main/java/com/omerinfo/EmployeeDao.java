package com.omerinfo;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles data access operations related to employees.
 * by OmerAKBEN
 */
public class EmployeeDao {

    // SQL query to get the full names of all employees.
    private static final String GET_FULL_NAMES_SQL = "SELECT concat(first_name, ' ', last_name) AS Full_Name from employees;";

    // SQL query to get the full names and birth years of employees born between 1960 and 1961.
    private static final String GET_EMPLOYEES_BETWEEN_1960_AND_1961_SQL = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, DATE_FORMAT(birth_date, '%Y') AS BD FROM employees WHERE DATE_FORMAT(birth_date, '%Y') BETWEEN 1960 AND 1961;";

    // SQL query to get salary from salaries less than 100 000.
    private static final String GET_SALARIES_LESS_THAN_100000_SQL = "SELECT salary from salaries WHERE salary < 100000";

    // SQL query to get salaries greater than 100 000 after 1999 year.
    private static final String GET_SALARIES_GREATER_THAN_100000_AND_YEAR_GREATER_THAN_1999_SQL = "SELECT * from salaries WHERE salary > 100000 AND DATE_FORMAT(to_date, '%Y') > 1999";

    // SQL query to get employees table with men whose first name start with "Z".
    private static final String GET_EMPLOYEES_MALE_FIRST_NAME_STARTS_WITH_Z_SQL = "SELECT * from employees WHERE first_name > 'z%' AND gender = 'M'";

    /**
     * Retrieves the full names of all employees from the database.
     *
     * @return A list of employee full names.
     * @throws SQLException If there is an issue executing the SQL query.
     */
    public List<String> getEmployeeFullNames() throws SQLException {
        List<String> employeeFullNames = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FULL_NAMES_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    employeeFullNames.add(resultSet.getString("Full_Name"));
                }
            }
        }
        return employeeFullNames;
    }

    /**
     * Retrieves employees born between 1960 and 1961, including their full names and birth years.
     *
     * @return A list of maps containing the full name and birth year of each employee.
     * @throws SQLException If there is an issue executing the SQL query.
     */
    public List<Map<String, String>> getEmployeesBornBetween1960And1961() throws SQLException {
        List<Map<String, String>> employees = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEES_BETWEEN_1960_AND_1961_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, String> employee = new HashMap<>();
                    employee.put("full_name", resultSet.getString("full_name"));
                    employee.put("BD", resultSet.getString("BD"));
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    /**
     * Retrieves salaries that are less than 100.000 from the Salaries table.
     *
     * @return A list of salaries
     * @throws SQLException upon an issue with execution of the query.
     */
    public List<String> getSalariesBelow100K() throws SQLException {
        List<String> salaries = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SALARIES_LESS_THAN_100000_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    salaries.add(resultSet.getString("salary"));
                }
            }
        }
        return salaries;
    }

    /**
     * Retrieves salary info of employees with salary greater than 100.000 and year greater than 1999
     *
     * @return a list of maps with employee number, salary and dates of each employee that has salary
     * above 100.000 with year greater than 1999.
     * @throws SQLException when execution of the query is not successful
     *
     */
    public List<Map<String, String>> getEmployeesWSalaryAbove100KAfter99() throws SQLException {
        List<Map<String, String>> employees = new ArrayList<>();
        try(Connection connection = MySqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_SALARIES_GREATER_THAN_100000_AND_YEAR_GREATER_THAN_1999_SQL)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    Map<String, String> salary = new HashMap<>();
                    salary.put("emp_no", resultSet.getString("emp_no"));
                    salary.put("salary", resultSet.getString("salary"));
                    salary.put("from_date", resultSet.getString("from_date"));
                    salary.put("to_date", resultSet.getString("to_date"));
                    employees.add(salary);
                }
            }
        }
        return employees;
    }

    /**
     * Retrieves all Male employees with names starting with letter 'Z'
     *
     * @return employee no, first name, last name, birth date, gender and hire date
     * of those male employees with names starting with Z.
     * @throws SQLException when the query is not executed successfully.
     *
     */
    public List<Map<String, String>> getMaleEmployeesWithInitialsZ() throws SQLException{
        List<Map<String, String>> employees = new ArrayList<>();
        try(Connection connection = MySqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEES_MALE_FIRST_NAME_STARTS_WITH_Z_SQL)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    Map<String, String> employee = new HashMap<>();
                    employee.put("emp_no", resultSet.getString("emp_no"));
                    employee.put("birth_date", resultSet.getString("birth_date"));
                    employee.put("first_name", resultSet.getString("first_name"));
                    employee.put("last_name", resultSet.getString("last_name"));
                    employee.put("gender", resultSet.getString("gender"));
                    employee.put("hire_date", resultSet.getString("hire_date"));
                    employees.add(employee);
                }
            }
        }
        return employees;
    }
}






