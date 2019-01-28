package com.miaoshaproject.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import com.miaoshaproject.dataobject.UserPasswordDO;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDOMapper userDOMapper;
	
	@Autowired
	private UserPasswordDOMapper userPasswordDOMapper;

	@Override
	public UserModel getUserById(Integer id) {
		// TODO Auto-generated method stub
		UserDO userDo = userDOMapper.selectByPrimaryKey(id);
		if (null == userDo) {
			return null;
		}
		
		UserPasswordDO userPasswordDO =  userPasswordDOMapper.selectByUserId(id);
		
		UserModel user = convertFromDataObject(userDo, userPasswordDO);
		return user;
	}

	private UserModel convertFromDataObject(UserDO userDo, UserPasswordDO userPasswordDo) {
		if (userDo == null) {
			return null;
		}
		UserModel user = new UserModel();
		BeanUtils.copyProperties(userDo, user);
		
		if (userPasswordDo != null) {
			user.setEncrptPassword(userPasswordDo.getEncrptPassword());
		}
		return user;
	}
	
}
