package net.biren.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.biren.springboot.model.Employee;
import net.biren.springboot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//Build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	//Build get all employee REST API
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();

	}

	//Build get employee by id REST API
	//http://localhost:8080/api/employees/id
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);

	}

	//Build update employee REST API
	//http://localhost:8080/api/employees/id
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
	}

	//Build delete employee REST API
	//http://localhost:8080/api/employees/id
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		//delete employee from DB
		employeeService.deleteEmployee(id);

		return new ResponseEntity<String>("Employee deleted Successfully..",HttpStatus.OK);

	}

}
