package com.hesine.common.utils.pn;

/**
 * base class for notification
 * @author wanghua
 *
 */
public class BaseNotif implements Cloneable{
	/**
	 * notification id
	 */
	private int notifId;

	/**
	 * registration id, default is empty.
	 */
	private String regId = "";

	/**
	 * 0：unicast  1：broadcast by groupID  2: broadcast by application ID
	 */
	private int notifyType;
	
	/**
	 * Start to receive messages from the PN, PN over this time have not push
	 * this message, just not push. Time in minutes
	 */
	private int expiry = 999999;

	/**
	 * push content send to PN, default empty.
	 */
	private String payload = "";

	public int getNotifId() {
		return notifId;
	}

	public void setNotifId(int notifId) {
		this.notifId = notifId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public int getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(int notifyType) {
		this.notifyType = notifyType;
	}

	public int getExpiry() {
		return expiry;
	}

	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public BaseNotif clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (BaseNotif) super.clone();
	}
	
	
	
}
