package ATM.BankIdentities;

import javax.sound.sampled.Line;
import java.io.Serializable;
import java.util.Map;
import java.util.Observable;

/** A class manage operations on password of a particular user */
public class PasswordManager implements Serializable {

    /** The ID of the user that this password manager is managing for */
    private final String userID;

    /** Whether the user has passed the authorization */
    private boolean isAuthorized;

    /**
     * Create a new PasswordManager that handles password of the user with given user ID
     * @param userID the ID of the user
     */
    public PasswordManager(String userID) {
        this.userID = userID;
    }

    /** Get the id of the user */
    public String getUserID() {
        return userID;
    }

    /** Return whether the user is log in, ie, has passed the authorization */
    public boolean isLogin() {
        return isAuthorized;
    }

    /**
     * Encrypt the given password to a more secure String
     * @param password
     * @return encrypted password
     */
    private String encrypt(String password){
        Integer raw = Integer.valueOf(password);
        Integer id = Integer.valueOf(getUserID());
        return String.valueOf((raw + id) * 2 + 207);
    }

    /**
     * Decrypt the given encrypted password to its original form
     * @param encryptedPassword
     * @return password
     */
    private String decrypt(String encryptedPassword){
        Integer encrypted = Integer.valueOf(encryptedPassword);
        Integer id = Integer.valueOf(getUserID());
        return String.valueOf((encrypted - 207)/2 - id);
    }

    /** Get the password of the user */
    private String getPassword(Map<String, String> passwordMap) {
        return decrypt(passwordMap.get(userID));
    }

    /**
     * Set the password of the user
     * @param newPass the new password to assign
     * */
    public void setPassword(String newPass, Map<String, String> passwordMap) {
            passwordMap.put(getUserID(), encrypt(newPass));
    }

    /**
     * Compare the input password with the user's password
     * The user is authorized or logged in if they are the same
     * Print out a warming otherwise
     * @param inputPass the entering string from user
     * */
    public void login(String inputPass, Map<String, String> passwordMap) {
        if (getPassword(passwordMap).equals(inputPass)) {
            isAuthorized = true;
        }else{
            System.out.println("Password is wrong!");
        }
    }

    /** Represent the process of a user logging out of the system
     * Notify the observes of this process
     */
    public void logout(){
        isAuthorized = false;
    }
}
