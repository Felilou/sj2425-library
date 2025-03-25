package at.spengergasse.library.model;

import java.util.Comparator;

public class EmployeeRankComparator implements Comparator<Employee> {

    public static final EmployeeRankComparator INSTANCE = new EmployeeRankComparator();

    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getRole().getImportanceLevel(), o2.getRole().getImportanceLevel());
    }

}
