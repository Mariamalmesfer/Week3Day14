package com.example.employeesmanagemen.Controller;

import com.example.employeesmanagemen.ApiRespoens.ApiRespoens;
import com.example.employeesmanagemen.Model.Employees;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeesController {


    ArrayList<Employees> employees = new ArrayList<>();


    @RequestMapping("/get")
    public ArrayList<Employees> getAllEmployees (){
        return employees;
    }


    @PostMapping("/add")
    public ResponseEntity addEmployees (@RequestBody @Valid Employees employee, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiRespoens("The employee added successfully"));

    }

    @PutMapping("/update/{index}")
    public  ResponseEntity UpdateEmployees (@PathVariable int index , @RequestBody @Valid Employees employee, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiRespoens("The employee Updated successfully"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiRespoens("The employee deleted successfully"));
    }


    @PutMapping ("/applyLeave/{id}")
    public ResponseEntity applyLeave (@PathVariable String id){

        for(Employees i : employees) {
            if(i.getId().equals(id)) {
                if (i.isOnLeave()) {
                    return ResponseEntity.status(200).body(new ApiRespoens("Employee is already on leave."));}
                if (i.getAnnualLeave() == 0) {
                    return ResponseEntity.status(200).body(new ApiRespoens("Employees has no AnnualLeave"));}
                i.setOnLeave(true);
                i.setAnnualLeave(i.getAnnualLeave() - 1);
                // employeeService.saveEmployee(employee);
                return ResponseEntity.status(200).body(new ApiRespoens("Annual Leave applied successfully."));

            }}

        return ResponseEntity.status(200).body(new ApiRespoens("id not found."));

        }





}
