package com.hesine.common.utils.pn;


/**
 * push message request object.
 * 
 * @author wanghua
 * 
 */
public class HttpPushMessage extends BaseNotif {
	
	/**
	 * 0：HPNS 1：APNS 2：GCM
	 */
	private int pnType;

	/**
	 * If notifyType is 2, this parameter is required, created by CS
	 */
	private int groupId;

	/**
	 * Application ID, If notifyType is 3, this parameter is required
	 */
	private int appId;

	public int getPnType() {
		return pnType;
	}

	public void setPnType(int pnType) {
		this.pnType = pnType;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public boolean isValidPnType(){
		if(pnType == EnumConstants.HCPS || pnType == EnumConstants.APNS || pnType == EnumConstants.GCM)
			return true;
		return false;
			
	}

}
