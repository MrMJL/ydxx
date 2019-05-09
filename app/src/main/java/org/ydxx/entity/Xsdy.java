package org.ydxx.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "xsdy")
public class Xsdy implements java.io.Serializable {

    // Fields
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String jxzyid;
    private String jxzymc;
    private String xsid;
    private String xsmc;
    private String ext1;
    private String ext2;
    private String ext3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJxzyid() {
        return jxzyid;
    }

    public void setJxzyid(String jxzyid) {
        this.jxzyid = jxzyid;
    }

    public String getJxzymc() {
        return jxzymc;
    }

    public void setJxzymc(String jxzymc) {
        this.jxzymc = jxzymc;
    }

    public String getXsid() {
        return xsid;
    }

    public void setXsid(String xsid) {
        this.xsid = xsid;
    }

    public String getXsmc() {
        return xsmc;
    }

    public void setXsmc(String xsmc) {
        this.xsmc = xsmc;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}