import com.omerinfo.EmployeeDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/*
* by OmerAKBEN
* */
public class EmployeeDaoTest {

    private final EmployeeDao employeeDao = new EmployeeDao();

    @Test
    public void testGetEmployeeFullNames() throws SQLException {
        List<String> employeeFullNames = employeeDao.getEmployeeFullNames();
        assertFalse(employeeFullNames.isEmpty(), "The employee full names list should not be empty");
    }

    @Test
    public void testGetEmployeesBornBetween1960And1961() throws SQLException {
        List<Map<String, String>> employees = employeeDao.getEmployeesBornBetween1960And1961();
        assertFalse(employees.isEmpty(), "The employee list should not be empty");

        for (Map<String, String> employee : employees) {
            String fullName = employee.get("full_name");
            String birthYear = employee.get("BD");

            assertFalse(fullName.isEmpty(), "Full name should not be empty");
            assertFalse(birthYear.isEmpty(), "Birth year should not be empty");

            int birthYearInt = Integer.parseInt(birthYear);
            assertTrue(birthYearInt >= 1960 && birthYearInt <= 1961, "Birth year should be between 1960 and 1961");
        }
    }
    
    @Test
    public void testGetSalariesLessThan100000() throws SQLException {
        List<String> salaries = employeeDao.getSalariesLessThan100000();
        for (String salary : salaries) {
			int salaryInt = Integer.parseInt(salary);
        	assertTrue(salaryInt < 100000, "The salary should be less than 100 000");
		}
        assertFalse(salaries.isEmpty(), "The salaries list should not be empty");
    }

    @Test
    public void testGetSalariesGreaterThan100000AndYearGreaterThan1999() throws SQLException {
        List<Map<String, String>> salaries = employeeDao.getSalariesGreaterThan100000AndYearGreaterThan1999();
        assertFalse(salaries.isEmpty(), "The salariy list should not be empty");

        for (Map<String, String> row : salaries) {
            String employeeNumber = row.get("emp_no");
            String salaryEach = row.get("salary");
            String dateFrom = row.get("from_date");
            String dateTo = row.get("to_date");

            assertFalse(employeeNumber.isEmpty(), "Employee number should not be empty");
            assertFalse(salaryEach.isEmpty(), "Salary should not be empty");
            assertFalse(dateFrom.isEmpty(), "From date should not be empty");
            assertFalse(dateTo.isEmpty(), "To date should not be empty");

            int salaryInt = Integer.parseInt(salaryEach);
            int yearFromInt = Integer.parseInt(dateFrom.substring(0,4));
            int yearToInt = Integer.parseInt(dateTo.trim().substring(0,4));
            
            assertTrue(salaryInt > 100000, "Salary should be greater than 100 000");
            assertTrue(yearFromInt <= yearToInt, "From date should not be greater than to date");
            assertTrue(yearToInt > 1999, "To date should be greater than 1999");
        }
    }
    
    @Test
    public void testGetMaleEmployeesWithFirstNameStartFromZ() throws SQLException {
        List<Map<String, String>> employees = employeeDao.getMaleEmployeesWithFirstNameStartFromZ();
        assertFalse(employees.isEmpty(), "The employee list should not be empty");
        
        for (Map<String, String> employee : employees) {
            String firstName = employee.get("first_name");
            String gender = employee.get("gender");
            String employeeNumber = employee.get("emp_no");
            String birthDate = employee.get("birth_date");
            String lastName = employee.get("last_name");
            String hireDate = employee.get("hire_date");
            
            assertFalse(firstName.isEmpty(), "First name should not be empty");
            assertFalse(gender.isEmpty(), "Gender should not be empty");
            assertFalse(employeeNumber.isEmpty(), "Employee number should not be empty");
            assertFalse(birthDate.isEmpty(), "Birth date should not be empty");
            assertFalse(lastName.isEmpty(), "Last name should not be empty");
            assertFalse(hireDate.isEmpty(), "Hire date should not be empty");

            assertTrue(firstName.toLowerCase().charAt(0) == 'z', "First name should starts with letter 'z'");
            assertTrue(gender.trim().equals("M"), "Gender should be a male");
        }
    }
}
