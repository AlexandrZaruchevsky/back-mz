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
    String accountName;
    String principalName;
    String description;
    String wsName;
    Long depId;
    String depName;
    Long posId;
    String posName;
    Long pofId;
    String pofName;

    private static EmployeeDtoV1 getEmployeeDtoV1Default(
            Employee employee,
            NestedEntities nestedEntities
    ) {
        EmployeeDtoV1 employeeDtoV1;
        switch (nestedEntities) {
            case EMPTY:
                employeeDtoV1 = new EmployeeDtoV1(
                        employee.getId(),
                        employee.getLastName(),
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getKspd(),
                        employee.getAccountName(),
                        employee.getPrincipalName(),
                        employee.getDescription(),
                        employee.getInfo(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                break;
            case WITH_ONE:
                employeeDtoV1 = new EmployeeDtoV1(
                        employee.getId(),
                        employee.getLastName(),
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getKspd(),
                        employee.getAccountName(),
                        employee.getPrincipalName(),
                        employee.getDescription(),
                        employee.getInfo(),
                        employee.getDepartment() != null
                                ? employee.getDepartment().getId()
                                : -1,
                        employee.getDepartment() != null
                                ? employee.getDepartment().getName()
                                : "",
                        null,
                        null,
                        null,
                        null
                );
                break;
            case WITH_TWO:
                employeeDtoV1 = new EmployeeDtoV1(
                        employee.getId(),
                        employee.getLastName(),
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getKspd(),
                        employee.getAccountName(),
                        employee.getPrincipalName(),
                        employee.getDescription(),
                        employee.getInfo(),
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
                        null,
                        null
                );
                break;
            default:
                employeeDtoV1 = new EmployeeDtoV1(
                        employee.getId(),
                        employee.getLastName(),
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getKspd(),
                        employee.getAccountName(),
                        employee.getPrincipalName(),
                        employee.getDescription(),
                        employee.getInfo(),
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
        return employeeDtoV1;
    }

    public static EmployeeDtoV1 create(Employee employee) {
        return getEmployeeDtoV1Default(employee, NestedEntities.EMPTY);
    }

    public static EmployeeDtoV1 createWithDepartment(Employee employee) {
        return getEmployeeDtoV1Default(employee, NestedEntities.WITH_ONE);
    }

    public static EmployeeDtoV1 createWithPositionAndDepartment(Employee employee) {
        return getEmployeeDtoV1Default(employee, NestedEntities.WITH_TWO);
    }

    public static EmployeeDtoV1 createWithAll(Employee employee) {
        return getEmployeeDtoV1Default(employee, NestedEntities.WITH_ALL);
    }

}
