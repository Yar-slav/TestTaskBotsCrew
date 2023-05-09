package com.yfedyna.service;

import com.yfedyna.model.Lector;
import com.yfedyna.model.LectorsDegree;
import com.yfedyna.repository.DepartmentRepo;
import com.yfedyna.repository.LectorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private static final String DEPARTMENT_NOT_EXIST = "Department not exist\n";

    private final DepartmentRepo departmentRepo;
    private final LectorRepo lectorRepo;

    @Override
    public void getHeadOfDepartment(String departmentName) {
        if (departmentRepo.existsByName(departmentName)) {
            String headOfDepartment = departmentRepo.findHeadOfDepartment(departmentName);
            System.out.printf("Head of %s department is %s\n\n", departmentName, headOfDepartment);
        } else {
            System.out.println(DEPARTMENT_NOT_EXIST);
        }
    }

    @Override
    public void getDepartmentStatistics(String departmentName) {
        if (departmentRepo.existsByName(departmentName)) {
            Map<LectorsDegree, Long> lectorsDegreeCount = getLectorsDegreeLongMap(departmentName);
            Long assistantsCount = lectorsDegreeCount.get(LectorsDegree.ASSISTANT) == null
                    ? 0 : lectorsDegreeCount.get(LectorsDegree.ASSISTANT);
            Long associateProfessorsCount = lectorsDegreeCount.get(LectorsDegree.ASSOCIATE_PROFESSOR) == null
                    ? 0 : lectorsDegreeCount.get(LectorsDegree.ASSOCIATE_PROFESSOR);
            Long professorsCount = lectorsDegreeCount.get(LectorsDegree.PROFESSOR) == null
                    ? 0 : lectorsDegreeCount.get(LectorsDegree.PROFESSOR);

            System.out.printf("assistants - %d,\nassociate professors - %d,\nprofessors - %d\n\n",
                    assistantsCount, associateProfessorsCount, professorsCount);
        } else {
            System.out.println(DEPARTMENT_NOT_EXIST);
        }
    }

    @Override
    public void getAverageSalaryByDepartment(String departmentName) {
        if (departmentRepo.existsByName(departmentName)) {
            BigDecimal averageSalaryOfDepartment = departmentRepo.findSalaryByDepartmentName(departmentName);
            System.out.printf("The average salary of %s is %.2f\n\n", departmentName, averageSalaryOfDepartment);
        } else {
            System.out.println(DEPARTMENT_NOT_EXIST);
        }
    }

    @Override
    public void getEmployeeCountByDepartment(String departmentName) {
        if (departmentRepo.existsByName(departmentName)) {
            Long countOfEmployees = departmentRepo.countOfEmployeesByDepartmentName(departmentName);
            System.out.println(countOfEmployees + "\n");
        } else {
            System.out.println(DEPARTMENT_NOT_EXIST);
        }
    }

    @Override
    public void globalSearchByTemplate(String template) {
        String byFirstNameOrLastName = String.join(", ", lectorRepo.findByFirstNameOrLastName(template));
        System.out.println(byFirstNameOrLastName + "\n");
    }

    private Map<LectorsDegree, Long> getLectorsDegreeLongMap(String departmentName) {
        List<Lector> lectors = departmentRepo.getLectors(departmentName);
        return lectors.stream()
                .collect(Collectors.groupingBy(
                        Lector::getDegree,
                        Collectors.counting()));
    }
}
