package ch07.template_method;

import javax.naming.ldap.LdapContext;

public class LdapAuthenticator {

    /**
     * DbAuthenticator랑 실행 과정이 동일하고 일부 구현만 다르다
     * 상위 클래스에 공통 실행 과정을 두고 세부 사항은 하위 클래스에 구현해 보자 -> 템플릿 메서드 패턴
     */
    public Auth authenticate(String id, String pw) {
        boolean auth = ldapClient.authenticate(id, pw);
        if (!auth) {
            throw createException();
        }

        LdapContext ctx = ldapClient.find(id);

        return new Auth(id, ctx.getAttribute("name"));
    }

    private RuntimeException createException() {
        return new RuntimeException();
    }
}
