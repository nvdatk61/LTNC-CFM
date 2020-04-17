/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Datnv
 */
public class Account {
    private String FullName;
    private String UserName;
    private String PassWord;
    private int Type;
    
    public Account(String UserName, String PassWord, String FullName, int Type) {
        this.FullName = FullName;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.Type = Type;
    }
    
    

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }
   
    
}
