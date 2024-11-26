package com.crm.controller;

import com.crm.payLoad.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeservice;
 @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
         @Valid @RequestBody EmployeeDto dto, BindingResult result){
     if(result.hasErrors()){
         return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
     }
     EmployeeDto employeeDto = employeeservice.addEmployee(dto);
     return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteEmp(@RequestParam Long id){
     employeeservice.deleteEmp(id);
     return new ResponseEntity<>("record is deleted successfully",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmp(@RequestParam Long id,@RequestBody EmployeeDto dto){
        EmployeeDto employeeDto = employeeservice.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam (name="pagesize",required = false,defaultValue = "5")int pagesize,
            @RequestParam(name="pageno",required = false,defaultValue = "0")int pageno,
            @RequestParam(name="sortby",required=false,defaultValue="name")String sortby,
            @RequestParam(name = "sortdir",required = false,defaultValue = "asc")String sortdir
    ){
        List<EmployeeDto> dto = employeeservice.getEmp(pageno,pagesize,sortby,sortdir);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable long empId){
     EmployeeDto dto=employeeservice.getEmployee(empId);
     return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
