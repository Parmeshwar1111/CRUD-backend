package in.sp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entities.User;
import in.sp.main.services.UserService;

//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "*")

@RestController
public class myController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/user")
	public User addUserDetails(@RequestBody User user) 
	{
		return userService.CreateUser(user);
	}
	
	@GetMapping("/user")
	public List<User>getAllUserDetails()
	{
		return userService.getAllUsers();
		
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserDetails(@PathVariable int id)
	{
		User user= userService.getUserDeatils(id).orElse(null);
		if(user !=null) {
			return ResponseEntity.ok().body(user);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUserDetails(@PathVariable int id,@RequestBody User user) {
		User updateUser= userService.updateUserDetails(id,user);
		if(updateUser !=null) {
			return ResponseEntity.ok(updateUser);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
	    if(userService.getUserDeatils(id).isPresent()) {
	        userService.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }  
	    return ResponseEntity.notFound().build();
	}
	

}
