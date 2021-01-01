package com.example.realestate.model.entity;import androidx.room.ColumnInfo;import androidx.room.Entity;import androidx.room.ForeignKey;import androidx.room.PrimaryKey;import static androidx.room.ForeignKey.CASCADE;@Entity(tableName = "Favourite", foreignKeys = @ForeignKey(entity = Favourite.class, childColumns = "EstateID", parentColumns = "ID", onDelete = CASCADE))public class Favourite {    @PrimaryKey(autoGenerate = true)    @ColumnInfo(name = "ID")    private int ID;    @ColumnInfo(name = "EstateID")    private int estateID;    public Favourite(int ID, int estateID) {        this.ID = ID;        this.estateID = estateID;    }    public Favourite() {    }    public int getID() {        return ID;    }    public void setID(int ID) {        this.ID = ID;    }    public int getEstateID() {        return estateID;    }    public void setEstateID(int estateID) {        this.estateID = estateID;    }}