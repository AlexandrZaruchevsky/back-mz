package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Employee;

@Value
public class EmployeeDtoV1 {

    Long id;
    String lastName;
    String firstName;
    String middleName;
    String email;
    String phone;
    String kspd;
    Long depId;
    String depName;
    Long posId;
    String posName;
    Long pofId;
    String pofName;

    private static EmployeeDtoV1 getEmployeeDtoV1Default(
            Employee employee
    ) {
        return new EmployeeDtoV1(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getKspd(),
                employee.getDepartment() != null
                        ? employee.getDepartment().getId()
                        : -1,
                employee.getDepartment() != null
                        ? employee.getDepartment().getName()
                        : "",
                employee.getPosition() != null
                        ? employee.getPosition().getId()
                        : -1,
                employee.getPosition() != null
                        ? employee.getPosition().getName()
                        : "",
                employee.getPointOfPresence() != null
                        ? employee.getPointOfPresence().getId()
                        : -1,
                employee.getPointOfPresence() != null
                        ? employee.getPointOfPresence().getShortName()
                        : ""
                );
    }

    public static EmployeeDtoV1 create(Employee employee) {
        return getEmployeeDtoV1Default(employee);
    }

    public static EmployeeDtoV1 createWithDepartment(Employee employee) {
        return getEmployeeDtoV1Default(employee);
    }

    public static EmployeeDtoV1 createWithPosition(Employee employee) {
        return getEmployeeDtoV1Default(employee);
    }

    public static EmployeeDtoV1 createWithPointOfPresence(Employee employee) {
        return getEmployeeDtoV1Default(employee);
    }

    public static EmployeeDtoV1 createWithAll(Employee employee) {
        return getEmployeeDtoV1Default(employee);
    }

}
