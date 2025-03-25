package at.spengergasse.library.persistance.repositories;

import at.spengergasse.library.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends UUIDRepository<Employee> {
}
