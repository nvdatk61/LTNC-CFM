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
public class TableFood {
    private int IDTable;
    private String NameTable;
    private int Status;

    public TableFood(int IDTable, String NameTable, int Status) {
        this.IDTable = IDTable;
        this.NameTable = NameTable;
        this.Status = Status;
    }
    
    

    public int getIDTable() {
        return IDTable;
    }

    public void setIDTable(int IDTable) {
        this.IDTable = IDTable;
    }

    public String getNameTable() {
        return NameTable;
    }

    public void setNameTable(String NameTable) {
        this.NameTable = NameTable;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
}
