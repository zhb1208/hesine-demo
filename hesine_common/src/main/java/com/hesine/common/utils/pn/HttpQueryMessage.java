/**
 * 
 */
package com.hesine.common.utils.pn;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


/**
 * 
 * @author wanghua
 *
 */
public class HttpQueryMessage {
	private int notifId;
	private List<String> regIdList;
	public int getNotifId() {
		return notifId;
	}
	public void setNotifId(int notifId) {
		this.notifId = notifId;
	}
	public List<String> getRegIdList() {
		return regIdList;
	}
	public void setRegIdList(List<String> regIdList) {
		this.regIdList = regIdList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("notifId:");
		sb.append(notifId);
		if(CollectionUtils.isNotEmpty(regIdList)){
			sb.append(",");
			sb.append("regIdSize:");
			sb.append(regIdList.size());
			sb.append(",");
			sb.append("regIdList:");
			sb.append("[");
			
			int cnt = 0;
			Iterator<String> it = regIdList.iterator();
			
			while(it.hasNext()&&cnt++<EnumConstants.REGID_MAX_LOG_SIZE){
				sb.append(it.next());
				sb.append(",");
			}
			if(EnumConstants.REGID_MAX_LOG_SIZE < regIdList.size()){
				sb.append("...");
			}else{
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]");
			sb.append("}");
		}
		return sb.toString();
	}
	
	
	
}
