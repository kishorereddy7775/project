package com.flm.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.flm.model.User;

@Component
public class UserDAOImpl implements UserDAO {
	
	String getAllUser="select * from user";
	String getOneUser="select * from user where email_id=?";
	String activateUser="update user set account_status='A',failed_count='0' where email_id=?";
	String inactivateUser="update user set account_status='I' where email_id=?";
	String updatefailedcount="update user set failed_count=? where email_id=?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserRowMapper userMapper;
	
//	public void jdbcTemplate(DataSource ds) {
//		jdbcTemplate=new JdbcTemplate(ds);
//	}

	@Override
	public List<User> getUserDetails() {
		List<User> users=jdbcTemplate.query(getAllUser, userMapper);
		return users;
	}

	@Override
	public User getOneUser(String email) {
		List<User> users=jdbcTemplate.query(getOneUser, userMapper,email);
		return users.get(0);
	}

	@Override
	public void activateUser(String email) {
		jdbcTemplate.update(activateUser,email);
	}

	@Override
	public void inActivateUser(String email) {
		jdbcTemplate.update(inactivateUser,email);
	}

	@Override
	public void updateFailedCount(String email, int count) {
		jdbcTemplate.update(updatefailedcount,count,email);
		
	}

}
