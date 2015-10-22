package org.jboss.as.connector.example;

import javax.security.auth.Subject;

import org.jboss.security.SubjectFactory;
import org.jboss.security.plugins.JBossSecuritySubjectFactory;

public class SecurityDomainDebug {

    public static void main(String[] args) {
        
        String securityDomain = "oauth-security";

        SubjectFactory subjectFactory = new JBossSecuritySubjectFactory();
        
        Subject subject = subjectFactory.createSubject(securityDomain);
        
        System.out.println(subject);
    }

}
