package at.spengergasse.library.service;

import at.spengergasse.library.dto.EmployeeDTO;
import at.spengergasse.library.model.Employee;
import at.spengergasse.library.persistance.repositories.EmployeeRepo;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends GenericService<Employee, EmployeeDTO> {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UUIDRepository<Employee> getUUIDRepository() {
        return employeeRepo;
    }

    public Employee findHighestRankedEmployee() {
        //TODO: Implement this method
        return null;
    }
}
