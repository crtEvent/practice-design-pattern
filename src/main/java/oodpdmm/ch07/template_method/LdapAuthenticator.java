package oodpdmm.ch07.template_method;

import oodpdmm.ch07.template_method.garbage.LdapClient;
import oodpdmm.ch07.template_method.garbage.LdapContext;

public class LdapAuthenticator extends Authenticator {

    private LdapClient ldapClient;

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
