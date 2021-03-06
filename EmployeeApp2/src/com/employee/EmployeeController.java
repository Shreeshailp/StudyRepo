package com.employee;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.employee.dto.EmployeeDTO;
import com.employee.exception.classes.EmployeeAppException;
import com.employee.service.EmployeeService;
import com.employee.util.EmployeeAppConstants;
import com.employee.util.ModelMapperUtil;

@RestController
@RequestMapping(value = "/employees")
@EnableWebMvc
public class EmployeeController implements EmployeeAppConstants{
	
	@Autowired
	EmployeeService empService;
	
	@Value("${employee.security.authheader}")
	String authHeader;
	
	@GetMapping(value = "/getAllEmployees" , produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeDTO> getAllEmployees(@RequestParam("user") String user) throws Exception{
		return ModelMapperUtil.mapEmpModelToDTOList(empService.getAllEmployees(user));
	}
	
	@PostMapping(value = "/saveEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveEmployee(@RequestBody EmployeeDTO emp) throws EmployeeAppException {
		
		String sts = EMP_APP_SER_FAIL;
		Integer id = 0;
		if(EMP_APP_ID_0.equalsIgnoreCase(emp.getId()) &&  (id = empService.addEmployee(ModelMapperUtil.mapEmpDTOToModel(emp))) != 0){
			sts = String.valueOf(id);
		}
		else
		{
			empService.updateEmployee(Integer.valueOf(emp.getId()),ModelMapperUtil.mapEmpDTOToModel(emp));
			sts = emp.getId();
		}
		return sts;
	}
	
	@PostMapping(value = "/saveEmployeePhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveEmployeePhoto(@RequestParam("uploadfile") MultipartFile file , 
			@RequestParam("empId") String empId) throws Exception{
		
		byte[] photo = file.getBytes();
		empService.addEmployeePhoto(empId, photo);
		
		return "ok";
	}
	
	@GetMapping(value = "/getEmployeePhoto", produces = MediaType.APPLICATION_JSON_VALUE)
	public byte[] getEmployeePhoto(	@RequestParam("empId") String empId) throws Exception{
		
		byte[] photo = empService.getEmployeePhoto(empId);
		
		return photo;
	}
	@RequestMapping(value = "/getEmployee/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO getEmployee(@PathVariable("id") Integer empId){
		return ModelMapperUtil.mapEmpModelToDTO(empService.getEmployee(empId));
	}
	
	@RequestMapping(value = "/deleteEmployee" , method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteEmployee(@RequestParam("empid") Integer empid) throws EmployeeAppException{
		
		if(empService.deleteEmployee(empid)){
			return EMP_APP_SER_SUCESS;
		}
		return EMP_APP_SER_FAIL;
	}
}
