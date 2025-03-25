package at.spengergasse.library.service;

import at.spengergasse.library.dto.EmployeeDTO;
import at.spengergasse.library.model.Employee;
import at.spengergasse.library.model.EmployeeRankComparator;
import at.spengergasse.library.persistance.repositories.EmployeeRepo;
import at.spengergasse.library.persistance.repositories.UUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void updateValues(Employee entity, EmployeeDTO dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getR);
    }

    public Employee findHighestRankedEmployee(List<Employee> employees) {
        employees.sort(EmployeeRankComparator.INSTANCE);
        return employees.getFirst();
    }
}
