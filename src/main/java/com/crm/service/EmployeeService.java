package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payLoad.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

   private ModelMapper modelMapper;

    public EmployeeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    private EmployeeRepository employeerepository;
   //create add employee for employee entity
    public EmployeeDto addEmployee(EmployeeDto dto){
        Employee employee = mapToEntity(dto);
        Employee emp = employeerepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        return employeeDto;
    }

public void deleteEmp(Long id){
  employeerepository.deleteById(id);
}

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = employeerepository.findById(id).get();
         employee = mapToEntity(dto);
         employee.setId(id);
        Employee saved = employeerepository.save(employee);
        EmployeeDto employeeDto = mapToDto(saved);
        return employeeDto;
    }

    public List<EmployeeDto> getEmp(int pageno, int pagesize, String sortby, String sortdir) {
        Sort sort = sortdir.equalsIgnoreCase("asc") ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();

        Pageable page = PageRequest.of(pageno, pagesize, sort);
        Page<Employee> all = employeerepository.findAll(page);
        List<Employee> emp = all.getContent();
        List<EmployeeDto> dto = emp.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
return dto;
    }
    public EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;
    }
    public Employee mapToEntity(EmployeeDto dto){
        Employee employee = modelMapper.map(dto, Employee.class);
        return employee;
    }

    public EmployeeDto getEmployee(long empId) {
        Employee employee = employeerepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("no data found in id:" + empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto;

    }
}
