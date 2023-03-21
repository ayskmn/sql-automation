import com.omerinfo.EmployeeDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public void testGetSalariesBelow100K() throws SQLException{
        List<String> salaries = employeeDao.getSalariesBelow100K();
        for(String salary: salaries){
            int intSalary = Integer.parseInt(salary);
            assertTrue(intSalary < 100000, "salary is not correct, it's not below 100K");
        }
        assertFalse(salaries.isEmpty(), "salaries list should not be empty");
    }

    @Test
    public void testGetEmployeesWSalaryAbove100KAfter1999() throws SQLException{
        List<Map<String, String>> employees = employeeDao.getEmployeesWSalaryAbove100KAfter99();
        assertFalse(employees.isEmpty(), "employees list should not be empty");
        for(Map<String, String> row: employees){
            String employeeNum = row.get("emp_no");
            String salary= row.get("salary");
            String fromDate = row.get("from_date").substring(0,4);
            String toDate = row.get("to_date").substring(0,4);
            assertFalse(employeeNum.isEmpty(), "employee number should not be empty");
            assertTrue(Integer.parseInt(salary) > 100000, "salary should be above 100000");
            assertFalse(fromDate.isEmpty(), "from date should not be empty");
            assertTrue(Integer.parseInt(fromDate) >= 1999, "from date should be greater or equals to 1999");
            assertFalse(toDate.isEmpty(), "to date should not be empty");
        }
    }
    @Test
    public void testGetMaleEmployeesWithInitialsZ() throws SQLException{
        List<Map<String, String>> employees = employeeDao.getMaleEmployeesWithInitialsZ();
        assertFalse(employees.isEmpty(), "employees list should not be empty");
        for(Map<String, String> employee: employees){
            String firstName = employee.get("first_name");
            String lastName = employee.get("last_name");
            String gender = employee.get("gender");
            String employeeNum = employee.get("emp_no");
            String birthDate = employee.get("birth_date");
            String hireDate = employee.get("hire_date");
            assertFalse(firstName.isEmpty(), "first name should not be empty");
            assertTrue(String.valueOf(firstName).startsWith("Z"), "first name does not start with 'Z'");
            assertFalse(lastName.isEmpty(), "last name should not be empty");
            assertFalse(gender.isEmpty(), "gender should not be empty");
            assertTrue(String.valueOf(gender).equals("M"), "gender is not equal to 'M'");
            assertFalse(employeeNum.isEmpty(), "employee num should not be empty");
            assertFalse(birthDate.isEmpty(), "birthdate should not be empty");
            assertFalse(hireDate.isEmpty(), "hire date should not be empty");
        }
    }

}
