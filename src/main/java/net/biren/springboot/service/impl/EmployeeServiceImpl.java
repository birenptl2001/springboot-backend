package net.biren.springboot.service.impl;

//import org.springframework.beans.factory.annotation.Autowired;
import net.biren.springboot.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import net.biren.springboot.model.Employee;
import net.biren.springboot.repository.EmployeeRepository;
import net.biren.springboot.service.EmployeeService;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    
	private EmployeeRepository employeeRepository;
	
	
	//@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//
//		if (((Optional<?>) employee).isPresent()) {
//			return employee.get();
//
//		}else{
//			throw new ResourceNotFoundException("Employee","Id",id);
//		}

		return employeeRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Employee","Id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		//we need to check whether employee with given id is exist in DB or not
		Employee existingemployee = employeeRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Employee", "Id",id));

		existingemployee.setFirstName(employee.getFirstName());
		existingemployee.setLastName(employee.getLastName());
		existingemployee.setEmail(employee.getEmail());
		//save existing employee to DB
		employeeRepository.save(existingemployee);
		return existingemployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Employee","Id",id));
		employeeRepository.deleteById(id);
	}


}
