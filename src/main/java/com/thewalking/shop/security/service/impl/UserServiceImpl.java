package com.thewalking.shop.security.service.impl;

import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.security.dao.UserDao;
import com.thewalking.shop.security.model.Address;
import com.thewalking.shop.security.model.Roles;
import com.thewalking.shop.security.model.User;
import com.thewalking.shop.security.model.UserDto;
import com.thewalking.shop.security.service.UserService;
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
	private UserDao userDao;

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
			authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
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
	public User makeUserInActiveById(Long id) {
		Optional<User> changed = userDao.findById(id).flatMap(user -> {
			user.setActive(false);
			return Optional.of(userDao.save(user));
		});
		return changed.orElseThrow(() -> {
			throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		});
	}

	@Override
	public User changeUserRole(Long id, Roles role) {
		Optional<User> changed = userDao.findById(id).flatMap(user -> {
			user.setRole(role.getRole());
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
		Address newAddress = new Address();
		newAddress.setAddressLine1(user.getAddress().getAddressLine1());
		newAddress.setAddressLine2(user.getAddress().getAddressLine2());
		newAddress.setCity(user.getAddress().getCity());
		newAddress.setState(user.getAddress().getState());
		newAddress.setZipCode(user.getAddress().getZipCode());
		newAddress.setCountry(user.getAddress().getCountry());
		newUser.setAddress(newAddress);
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
