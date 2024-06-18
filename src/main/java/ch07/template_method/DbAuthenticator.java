package ch07.template_method;

public class DbAuthenticator {

    public Auth authenticate(String id, String pw) {
        User user userDao.selectById(id);
        boolean auth = user.equalPassword(pw);
        if (!auth) {
            throw createException();
        }

        return new Auth(id, user.getName());
    }

    private RuntimeException createException() {
        return new RuntimeException();
    }
}
