package com.yfedyna.service;

public interface UniversityService {
    void getHeadOfDepartment(String departmentName);

    void getDepartmentStatistics(String departmentName);

    void getAverageSalaryByDepartment(String departmentName);

    void getEmployeeCountByDepartment(String departmentName);

    void globalSearchByTemplate(String template);
}
