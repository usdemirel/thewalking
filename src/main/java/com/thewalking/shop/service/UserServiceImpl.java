package com.thewalking.shop.service;

import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.repository.EmployeeRepository;
import com.thewalking.shop.repository.ManagerRepository;
import com.thewalking.shop.repository.UserRepository;
import com.thewalking.shop.model.Roles;
import com.thewalking.shop.entity.User;
import com.thewalking.shop.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + ((user.isActive()) ? user.getRole() : "UNAUTHORIZED")));
			if(user.getRole().equals("EMPLOYEE")){
				final Long branchId = employeeRepository.findById(user.getId()).get().getBranch().getId();
				authorities.add(new SimpleGrantedAuthority("ROLE_" + branchId));
			}else if(user.getRole().equals("MANAGER")){
				final Long branchId = managerRepository.findById(user.getId()).get().getBranch().getId();
				authorities.add(new SimpleGrantedAuthority("ROLE_" + branchId));
			}
			authorities.forEach(each -> System.out.println("* " + each.getAuthority()));
		return authorities;
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByEmail(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
	public User makeUserInActive(User user) {
		user.setActive(false);
		return userDao.save(user);
	}

	@Override
	public User toggleUserActivenessById(Long id) {
		Optional<User> changed = userDao.findById(id).flatMap(user -> {
			user.setActive(!user.isActive());
			return Optional.of(userDao.save(user));
		});
		return changed.orElseThrow(() -> {
			throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		});
	}

	@Override
	public User changeUserRole(Long id, String role) {
		Optional<User> changed = userDao.findById(id).flatMap(user -> {
			user.setRole(role);
			return Optional.of(userDao.save(user));
		});
		return changed.orElseThrow(() -> {
			throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		});
	}

	@Override
    public User save(UserDto user) throws Exception {
	    User newUser = new User();
	    newUser.setEmail(user.getEmail());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhone(user.getPhone());
		newUser.setActive(user.isActive());
		newUser.setAddress(user.getAddress());
		User saved;
//		try {
			saved = userDao.save(newUser);
//		}catch(DataIntegrityViolationException dive){
//			throw new DataIntegrityViolationException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//		}catch (Exception e){
//			throw new Exception("not saved!");
//		}
		return saved;
    }
}
