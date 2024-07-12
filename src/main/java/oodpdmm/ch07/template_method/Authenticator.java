package oodpdmm.ch07.template_method;

public abstract class Authenticator {

    public Auth authenticate(String id, String pw) {
        if (! doAuthenticate(id, pw)) {
            throw createException();
        }

        return createAuth(id);
    }

    protected abstract boolean doAuthenticate(String id, String pw);

    protected abstract Auth createAuth(String id);

    private RuntimeException createException() {
        return new RuntimeException();
    }

}
