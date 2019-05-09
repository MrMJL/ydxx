package org.ydxx.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "jxzy")
public class Jxzy implements java.io.Serializable {

	// Fields
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String kcmc;
	private String lsid;
	private String lsxm;
	private String ext1;
	private String ext2;
	private String ext3;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	public String getLsid() {
		return lsid;
	}

	public void setLsid(String lsid) {
		this.lsid = lsid;
	}

	public String getLsxm() {
		return lsxm;
	}

	public void setLsxm(String lsxm) {
		this.lsxm = lsxm;
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

	@Override public String toString() {
		return "Jxzy{" +
			"id='" + id + '\'' +
			", kcmc='" + kcmc + '\'' +
			", lsid='" + lsid + '\'' +
			", lsxm='" + lsxm + '\'' +
			", ext1='" + ext1 + '\'' +
			", ext2='" + ext2 + '\'' +
			", ext3='" + ext3 + '\'' +
			'}';
	}
}