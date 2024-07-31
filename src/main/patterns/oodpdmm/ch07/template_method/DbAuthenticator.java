package oodpdmm.ch07.template_method;

import oodpdmm.ch07.template_method.garbage.User;
import oodpdmm.ch07.template_method.garbage.UserDao;

public class DbAuthenticator extends Authenticator {

    private UserDao userDao;

    @Override
    protected boolean doAuthenticate(String id, String pw) {
        User user = userDao.selectById(id);
        return user.equalPassword(pw);
    }

    @Override
    protected Auth createAuth(String id) {
        User user = userDao.selectById(id);
        return new Auth(id, user.getName());
    }

}
