package oodpdmm.ch07.template_method.garbage;

public interface LdapClient {
    boolean authenticate(String id, String pw);
    LdapContext find(String id);
}
