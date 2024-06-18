package ch07.template_method;

import javax.naming.ldap.LdapContext;

public class LdapAuthenticator extends Authenticator {

    @Override
    protected boolean doAuthenticate(String id, String pw) {
        return ldapClient.authenticate(id, pw);
    }

    @Override
    protected Auth createAuth(String id) {
        LdapContext ctx = ldapClient.find(id);

        return new Auth(id, ctx.getAttribute("name"));
    }

}
