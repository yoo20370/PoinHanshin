package com.project.poinhanshin.mapper.login;

import com.project.poinhanshin.domain.member.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final UserDao userDao;

    @Autowired
    public UserDaoImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByLoginId(String loginId) {
        return userDao.findByLoginId(loginId);
    }

    @Override
    public User login(String loginId, String password) {
        return userDao.login(loginId, password);
    }
}
