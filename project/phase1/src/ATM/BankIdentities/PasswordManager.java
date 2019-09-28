package ATM.BankIdentities;

import java.io.Serializable;
import java.util.Observable;

/** The passWordManager class */
public class PasswordManager extends Observable implements Serializable {

    private final String ownerId;
    private String password;
    private boolean authority;

    /** Create a new PassWordManager
     *
     * @param ownerId the Id of the passwordManager
     * */
    public PasswordManager(String ownerId) {
        this.ownerId = ownerId;
    }

    /** get the password of the password manager */
    private String getPassword() {
        return password;
    }

    /** return the authority to confirm the login*/
    public boolean isLogin() {
        return authority;
    }

    /** Set the password of the password Manager
     * @param newPass the new password to assign
     * */
    public void setPassword(String newPass) {
        /*if (authority) {
            this.password = newPass;
        } else {
            System.out.println("You don't have authority to modify this.");
        }*/
        this.password = newPass;
    }

    /** Check the entering input of users with the password
     *
     * @param inputPass the entering string from user
     * */
    public void login(String inputPass) {
        if (getPassword().equals(inputPass)) {
            authority = true;
        }else{
            System.out.println("Password is wrong!");
        }
    }

    /** The password Manager log out the cycle */
    public void logout(){
        authority = false;
        setChanged();
        notifyObservers();
    }

    /** get the id of the owner */
    public String getOwnerId() {
        return ownerId;
    }
}
