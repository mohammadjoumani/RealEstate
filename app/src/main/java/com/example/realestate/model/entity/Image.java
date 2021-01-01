package com.example.realestate.model.entity;import androidx.room.ColumnInfo;import androidx.room.Entity;import androidx.room.ForeignKey;import androidx.room.PrimaryKey;import static androidx.room.ForeignKey.CASCADE;@Entity(tableName = "Image", foreignKeys = {        @ForeignKey(entity = Estate.class, parentColumns = "ID", childColumns = "EstateID", onDelete = CASCADE)})public class Image {    @PrimaryKey(autoGenerate = true)    @ColumnInfo(name = "ID")    private int id;    @ColumnInfo(name = "Value")    private String value;    @ColumnInfo(name = "EstateID")    private int estateID;    public Image(int id, String values, int estateID) {        this.id = id;        this.value = value;        this.estateID = estateID;    }    public Image() {    }    public int getId() {        return id;    }    public void setId(int id) {        this.id = id;    }    public String getValue() {        return value;    }    public void setValue(String value) {        this.value = value;    }    public int getEstateID() {        return estateID;    }    public void setEstateID(int estateID) {        this.estateID = estateID;    }}