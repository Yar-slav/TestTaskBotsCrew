package com.yfedyna.service;

import com.yfedyna.model.Lector;
import com.yfedyna.model.LectorsDegree;
import com.yfedyna.repository.DepartmentRepo;
import com.yfedyna.repository.LectorRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UniversityServiceImplTest {

    @Mock
    private DepartmentRepo departmentRepo;
    @Mock
    private LectorRepo lectorRepo;

    @InjectMocks
    private UniversityServiceImpl universityService;

    @Test
    @DisplayName("Test getHeadOfDepartment when department exists")
    public void testGetHeadOfDepartmentWhenDepartmentExists() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "Mathematics";
        String headOfDepartment = "John Lennon";
        when(departmentRepo.existsByName(departmentName)).thenReturn(true);
        when(departmentRepo.findHeadOfDepartment(departmentName)).thenReturn(headOfDepartment);

        UniversityServiceImpl universityService = new UniversityServiceImpl(departmentRepo, lectorRepo);

        universityService.getHeadOfDepartment(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        verify(departmentRepo).findHeadOfDepartment(departmentName);
        String expectedOutput = "Head of Mathematics department is John Lennon\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Test getHeadOfDepartment when department does not exist")
    public void testGetHeadOfDepartmentWhenDepartmentDoesNotExist() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String departmentName = "English";
        when(departmentRepo.existsByName(departmentName)).thenReturn(false);
        UniversityServiceImpl universityService = new UniversityServiceImpl(departmentRepo, lectorRepo);

        universityService.getHeadOfDepartment(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        String expectedOutput = "Department not exist\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Test getDepartmentStatistics when department exists")
    public void testGetDepartmentStatisticsWhenDepartmentExists() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "Mathematics";
        Lector assistant1 = new Lector(1L, "John", "Doe", LectorsDegree.ASSISTANT, null, null, null);
        Lector assistant2 = new Lector(2L, "John", "Doe", LectorsDegree.ASSISTANT, null, null, null);
        Lector professor1 = new Lector(3L, "David", "Smith", LectorsDegree.PROFESSOR, null, null, null);
        List<Lector> lectors = Arrays.asList(assistant1, assistant2, professor1);
        when(departmentRepo.existsByName(departmentName)).thenReturn(true);
        when(departmentRepo.getLectors(departmentName)).thenReturn(lectors);

        universityService.getDepartmentStatistics(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        verify(departmentRepo).getLectors(departmentName);

        String expectedOutput = "assistants - 2,\nassociate professors - 0,\nprofessors - 1\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Test getDepartmentStatistics when department doesn't have lectors")
    public void testGetDepartmentStatisticsWhenDepartmentExistsDoesntHaveLectors() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "Mathematics";
        List<Lector> lectors = Collections.emptyList();
        when(departmentRepo.existsByName(departmentName)).thenReturn(true);
        when(departmentRepo.getLectors(departmentName)).thenReturn(lectors);

        universityService.getDepartmentStatistics(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        verify(departmentRepo).getLectors(departmentName);

        String expectedOutput = "assistants - 0,\nassociate professors - 0,\nprofessors - 0\n\n";
        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    @DisplayName("Test getDepartmentStatistics when department does not exist")
    public void testGetDepartmentStatisticsWhenDepartmentDoesNotExist() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "English";
        when(departmentRepo.existsByName(departmentName)).thenReturn(false);
        UniversityServiceImpl universityService = new UniversityServiceImpl(departmentRepo, lectorRepo);

        universityService.getDepartmentStatistics(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        String expectedOutput = "Department not exist\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("getAverageSalaryByDepartment with existing department")
    public void testGetAverageSalaryByDepartmentWithExistingDepartment() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "English";
        BigDecimal expectedAverageSalary = new BigDecimal("5000.00");
        when(departmentRepo.existsByName(departmentName)).thenReturn(true);
        when(departmentRepo.findSalaryByDepartmentName(departmentName)).thenReturn(expectedAverageSalary);

        universityService.getAverageSalaryByDepartment(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        verify(departmentRepo).findSalaryByDepartmentName(departmentName);
        String expectedOutput = "The average salary of English is 5000.00\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("getAverageSalaryByDepartment with non-existent department")
    public void testGetAverageSalaryByDepartmentWithNonExistentDepartment() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "Non-existent Department";
        when(departmentRepo.existsByName(departmentName)).thenReturn(false);

        universityService.getAverageSalaryByDepartment(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        String expectedOutput = "Department not exist\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("getEmployeeCountByDepartment with existing department")
    public void testGetEmployeeCountByDepartmentWithExistingDepartment() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "English";
        Long expectedEmployeeCount = 10L;
        when(departmentRepo.existsByName(departmentName)).thenReturn(true);
        when(departmentRepo.countOfEmployeesByDepartmentName(departmentName)).thenReturn(expectedEmployeeCount);

        universityService.getEmployeeCountByDepartment(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        verify(departmentRepo).countOfEmployeesByDepartmentName(departmentName);
        String expectedOutput = "10\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("getEmployeeCountByDepartment with non-existent department")
    public void testGetEmployeeCountByDepartmentWithNonExistentDepartment() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String departmentName = "English";
        when(departmentRepo.existsByName(departmentName)).thenReturn(false);

        universityService.getEmployeeCountByDepartment(departmentName);

        verify(departmentRepo).existsByName(departmentName);
        String expectedOutput = "Department not exist\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Test globalSearchByTemplate method with matching template")
    public void testGlobalSearchByTemplateWithMatchingTemplate() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String template = "John";
        List<String> lectors = Arrays.asList("John Smith", "John Doe");
        when(lectorRepo.findByFirstNameOrLastName(template)).thenReturn(lectors);

        universityService.globalSearchByTemplate(template);

        String expectedOutput = "John Smith, John Doe\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Test globalSearchByTemplate method with non-matching template")
    public void testGlobalSearchByTemplateWithNonMatchingTemplate() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String template = "Jane";
        List<String> lectors = Collections.emptyList();
        when(lectorRepo.findByFirstNameOrLastName(template)).thenReturn(lectors);

        universityService.globalSearchByTemplate(template);

        String expectedOutput = "\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
