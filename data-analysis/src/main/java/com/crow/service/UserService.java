package com.crow.service;

import com.crow.domain.User;
import com.crow.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/21.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getAddresses(Integer selectLimitNum) {
        return userMapper.selectAllAddressesSort(selectLimitNum);
    }

    public List<User> getGender() {
        return userMapper.selectAllGender();
    }

    public List<User> getAllUsers(Integer selectLimitNum) {
        return userMapper.selectAllUsersSortedByViews(selectLimitNum);
    }
}
