package com.yfedyna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleImplTest {

    @Mock
    private UniversityService universityService;

    private ConsoleImpl console;

    @BeforeEach
    void setUp() {
        console = new ConsoleImpl(universityService);
    }

    @Test
    void testRun() {
        String input = "Who is head of department test\nShow test statistics\nGlobal search by test\ninvalid command\nexit\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
        console.run();
        assertEquals("""
                        Please enter command or 'exit' to quit:
                        Please enter command or 'exit' to quit:
                        Please enter command or 'exit' to quit:
                        Please enter command or 'exit' to quit:
                        Unknown command

                        Please enter command or 'exit' to quit:
                        """,
                outContent.toString());
    }

    @Test
    @DisplayName("Test handleWhoCommand() with valid input")
    void testHandleWhoCommand() {
        console.handleWhoCommand("Who is head of department test");
        verify(universityService).getHeadOfDepartment("test");
    }

    @Test
    @DisplayName("Test handleWhoCommand() with invalid input")
    void testHandleWhoCommandInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        console.handleWhoCommand("Who is head of invalid");
        assertEquals("Unknown command\n\n", outContent.toString());
    }

    @Test
    @DisplayName("Test handleShowCommand() with valid input for statistics")
    void testHandleShowCommandStatistics() {
        console.handleShowCommand("Show test statistics");
        verify(universityService).getDepartmentStatistics("test");
    }

    @Test
    @DisplayName("Test handleShowCommand() with valid input for average salary")
    void testHandleShowCommandForAverageSalary() {
        console.handleShowCommand("Show the average salary for the department CS");
        verify(universityService).getAverageSalaryByDepartment("CS");
    }

    @Test
    @DisplayName("Test handleShowCommand() with valid input for count of employee")
    void testHandleShowCommandForCountOfEmployee() {
        console.handleShowCommand("Show count of employee for test");
        verify(universityService).getEmployeeCountByDepartment("test");
    }

    @Test
    @DisplayName("Test handleShowCommand() with invalid input")
    void testHandleShowCommandInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        console.handleShowCommand("Show the average salary for the invalid");
        assertEquals("Unknown command\n\n", outContent.toString());
    }

    @Test
    @DisplayName("Test handleGlobalSearch() with valid input")
    void testHandleGlobalSearch() {
        console.handleGlobalSearch("Global search by test");
        verify(universityService).globalSearchByTemplate("test");
    }

    @Test
    @DisplayName("Test handleGlobalSearch() with invalid input")
    void testHandleGlobalSearchInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        console.handleGlobalSearch("Global invalid");
        assertEquals("Unknown command\n\n", outContent.toString());
    }

    @Test
    @DisplayName("Test run() with help command")
    void testRunHelp() {
        String input = "help\nexit\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
        console.run();
        String expectedOutput = """
                Please enter command or 'exit' to quit:
                Who is head of department {department_name}
                Show {department_name} statistics
                Show the average salary for the department {department_name}
                Show count of employee for {department_name}
                Global search by {template}

                Please enter command or 'exit' to quit:
                """;
        assertEquals(expectedOutput, outContent.toString());
    }
}