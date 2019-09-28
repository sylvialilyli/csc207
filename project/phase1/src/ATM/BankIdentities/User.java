package ATM.BankIdentities;

import ATM.InfoHandling.InfoManager;
/** Class User */
public class User extends BankIdentity {
    private final String id;
    private UserAccManager accManager;
    private PasswordManager passManager;

    /** Create a new user */
    public User() {
        this.id = "020" + (InfoManager.getInfoManager().getUserNum() + 1);
    }

    /** return the Id of the user */
    public String getId(){
        return id;
    }

    /** return the accManager of the user */
    public UserAccManager getAccManager() {
        return accManager;
    }

    /** get the pass Manager of the user */
    public PasswordManager getPassManager() {
        return passManager;
    }

    /** Set the account Manager
     * @param  accManager the new account manager
     * */
    public void setAccManager(UserAccManager accManager) {
        this.accManager = accManager;
    }

    /** Set the passManager
     * @param passManager the new passwordMager */
    public void setPassManager(PasswordManager passManager) {
        this.passManager = passManager;
    }

    /** the user send the request to the Manager to add the account
     * @param type the account type needed be added
     * */
    public void sendRequest(String type){
        InfoManager.getInfoManager().add(getId(), type);
    }
}
