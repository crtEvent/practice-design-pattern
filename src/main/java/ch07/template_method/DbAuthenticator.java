package ch07.template_method;

public class DbAuthenticator extends Authenticator {

    @Override
    protected boolean doAuthenticate(String id, String pw) {
        User user userDao.selectById(id);
        return user.equalPassword(pw);
    }

    @Override
    protected Auth createAuth(String id) {
        return new Auth(id, user.getName());
    }

}
