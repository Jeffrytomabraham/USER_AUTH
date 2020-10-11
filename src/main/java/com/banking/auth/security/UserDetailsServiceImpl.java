package com.banking.auth.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.auth.entity.UserDetailsEntityDTO;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(username));
		List<UserDetailsEntityDTO> savedUserDetails= mongoTemplate.find(query, UserDetailsEntityDTO.class);
		if(savedUserDetails.size()==0)
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		return UserDetailsImpl.build(savedUserDetails.get(0));
	}
}
