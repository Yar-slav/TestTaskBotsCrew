package com.yfedyna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleImpl implements CommandLineRunner, Console {

    private static final String UNKNOWN_COMMAND = "Unknown command\n";
    private final UniversityService universityService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter command or 'exit' to quit:");
            String command = scanner.nextLine();

            if ("exit".equals(command)) {
                break;
            }
            if("help".equals(command)) {
                printHelpCommand();
                continue;
            }

            commandStartWith(command);
        }
    }

    @Override
    public void handleWhoCommand(String command) {
        if(command.startsWith("Who is head of department ")) {
            String departmentName = command.split("Who is head of department ")[1];
            universityService.getHeadOfDepartment(departmentName);
        } else {
            System.out.println(UNKNOWN_COMMAND);
        }
    }

    @Override
    public void handleShowCommand(String command) {
        if(command.endsWith("statistics")){
            String departmentName = command.replaceAll("^Show | statistics$", "");
            universityService.getDepartmentStatistics(departmentName);
        } else if (command.startsWith("Show the average salary for the department ")) {
            String departmentName = command.split("Show the average salary for the department ")[1];
            universityService.getAverageSalaryByDepartment(departmentName);
        } else if (command.startsWith("Show count of employee for ")) {
            String departmentName = command.split("Show count of employee for ")[1];
            universityService.getEmployeeCountByDepartment(departmentName);
        } else {
            System.out.println(UNKNOWN_COMMAND);
        }
    }

    @Override
    public void handleGlobalSearch(String command) {
        if(command.startsWith("Global search by ")) {
            String template = command.split("Global search by ")[1];
            universityService.globalSearchByTemplate(template);
        } else {
            System.out.println(UNKNOWN_COMMAND);
        }
    }

    private static void printHelpCommand() {
        System.out.println("Who is head of department {department_name}");
        System.out.println("Show {department_name} statistics");
        System.out.println("Show the average salary for the department {department_name}");
        System.out.println("Show count of employee for {department_name}");
        System.out.println("Global search by {template}");
        System.out.println();
    }

    private void commandStartWith(String command) {
        if(command.startsWith("Who")){
            handleWhoCommand(command);
        } else if (command.startsWith("Show")) {
            handleShowCommand(command);
        } else if (command.startsWith("Global")) {
            handleGlobalSearch(command);
        } else {
            System.out.println(UNKNOWN_COMMAND);
        }
    }

}
