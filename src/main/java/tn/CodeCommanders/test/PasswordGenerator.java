package tn.CodeCommanders.test;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordGenerator {
    
    public static String generatePassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }
    
    public static boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}


