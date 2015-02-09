package com.hesine.common.utils.pn;

public class EnumConstants {

	/**
	 * when send notification to HCPS, notificationId plus this number. 
	 */
	public final static int ADD_FOR_HCPS_NOTIFYID = 1700000000;
	
	/**
	 * notification send status
	 */
	public final static byte NOT_TO_PN = 0;
	public final static byte TO_PN = 1;
	public final static byte INVALID_PUSH = 2;
	public final static byte DELIVER_MA = 3;

	/**
	 * notify erroCode
	 */
	public final static int SUCESS_PN = 0;
	public final static int INVALID_SENDER = 401;
	public final static int INVALID_REGISTRATION = 2401;
	public final static int MESSAGE_TOO_BIG = 401;
	public final static int APP_SUSPENDED = 423;
	
	/**
	 * PN TYPE
	 */
	public final static byte HCPS = 0;
	public final static byte APNS = 1;
	public final static byte GCM = 2;
	
	/**
	 * PN TYPE String
	 */
	public final static String HCPSSTRING = "0";
	public final static String APNSSTRING = "1";
	public final static String GCMSTRING = "2";
	
	
	/**
	 * notify type
	 */
	public final static byte UNICAST = 0;
	public final static byte BROADCAST = 1;
	public final static byte BROAD_APP = 2;
	
	
	public final static byte BASIC = 1;
	public final static byte DIGEST = 2;
	public final static byte CONTROL_IP = 4;
	
	
	public final static int REGID_MAX_LOG_SIZE = 10;
}
