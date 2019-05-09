package org.ydxx.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "mess")
public class Mess implements java.io.Serializable {

	// Fields

	@PrimaryKey(autoGenerate = true)
	private int id;
	private String fuserid;
	private String fusername;
	private String fmessage;
	private String tuserid;
	private String tusername;
	private String tmessage;
	private String ext1;
	private String ext2;
	private String ext3;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFuserid() {
		return fuserid;
	}

	public void setFuserid(String fuserid) {
		this.fuserid = fuserid;
	}

	public String getFusername() {
		return fusername;
	}

	public void setFusername(String fusername) {
		this.fusername = fusername;
	}

	public String getFmessage() {
		return fmessage;
	}

	public void setFmessage(String fmessage) {
		this.fmessage = fmessage;
	}

	public String getTuserid() {
		return tuserid;
	}

	public void setTuserid(String tuserid) {
		this.tuserid = tuserid;
	}

	public String getTusername() {
		return tusername;
	}

	public void setTusername(String tusername) {
		this.tusername = tusername;
	}

	public String getTmessage() {
		return tmessage;
	}

	public void setTmessage(String tmessage) {
		this.tmessage = tmessage;
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