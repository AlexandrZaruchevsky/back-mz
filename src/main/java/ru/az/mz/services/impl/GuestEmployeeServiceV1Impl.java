package ru.az.mz.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.services.EmployeeServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.util.UtilZ;

import java.util.List;
import java.util.Map;

@Service("guestEmployeeService")
public class GuestEmployeeServiceV1Impl implements EmployeeServiceV1 {

    private final EmployeeRepo employeeRepo;
    private final SetupParameters setupParameters;

    public GuestEmployeeServiceV1Impl(EmployeeRepo employeeRepo, SetupParameters setupParameters) {
        this.employeeRepo = employeeRepo;
        this.setupParameters = setupParameters;
    }

    @Override
    public Page<Employee> findByFIO(String fio, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Employee> findByKspd(String kspd, Pageable pageable) {
        return null;
    }

    @Override
    public List<EmployeeDtoV1> findAllByFioForChoice(String fio) {
        return null;
    }

    @Override
    @Cacheable(
            cacheNames = {"guest-employees"},
            key = "{#pageRequest.pageCurrent, #pageRequest.pageSize, #pageRequest.search}"
    )
    public Page<EmployeeDtoV1> findAll(PageRequestDtoV1 pageRequest) {
        PageRequest request;
        if (pageRequest == null) {
            request = setupParameters
                    .getPageRequestDefault()
                    .withSort(Sort.by("lastName", "firstName", "middleName"));
            return employeeRepo.findAllByStatus(EntityStatus.ACTIVE, request)
                    .map(EmployeeDtoV1::createWithPositionAndDepartment);
        }
        request = PageRequest.of(pageRequest.getPageCurrent(), pageRequest.getPageSize());
        try {
            Long.valueOf(pageRequest.getSearch());
            return employeeRepo.findAllByKspdStartingWithAndStatus(
                    pageRequest.getSearch().trim(),
                    EntityStatus.ACTIVE,
                    request.withSort(Sort.by("kspd")))
                    .map(EmployeeDtoV1::createWithPositionAndDepartment);
        } catch (NumberFormatException e) {
            Map<String, String> fio = UtilZ.getFio(pageRequest.getSearch());
            return employeeRepo.findAllByLastNameStartingWithAndFirstNameStartingWithAndMiddleNameStartingWithAndStatus(
                    fio.get("lastName"),
                    fio.get("firstName"),
                    fio.get("middleName"),
                    EntityStatus.ACTIVE,
                    request.withSort(Sort.by("lastName", "firstName", "middleName"))
            ).map(EmployeeDtoV1::createWithPositionAndDepartment);
        }
    }

    @Override
    public Employee add(EmployeeDtoV1 employeeDtoV1) throws MyException {
        return null;
    }

    @Override
    public Employee update(EmployeeDtoV1 employeeDtoV1) throws MyException {
        return null;
    }

    @Override
    public boolean delete(long id) throws MyException {
        return false;
    }

    @Override
    public Employee findById(Long id) throws MyException {
        return null;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return 0;
    }

    @Override
    public long countAll() {
        return 0;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
