package in.sp.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.User;
import in.sp.main.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository  userRepository;

	@Override
	public User CreateUser(User user) {
		
		return userRepository.save(user);
	}
	
	

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}



	@Override
	public Optional<User> getUserDeatils(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}



	@Override
	public User updateUserDetails(int id, User newUser) {
		User userData=userRepository.findById(id).orElse(null);
		if(userData !=null) {
		return	userRepository.save(newUser);
		}
		else 
		{ throw new RuntimeException ("user not found with it :"+ id);
		
		}
	}



	@Override
	public void deleteUser(int id) {
		
		userRepository.deleteById(id);
	}
	

}
