package com.hesine.common.utils.pn;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;


public class RegGroup {
	/**
	 * group Id
	 */
	private int groupId;

	/**
	 * group name, default ""
	 */
	private String name = "";

	/**
	 * group description, default ""
	 */
	private String desc = "";

	/**
	 * PN type
	 */
	private byte pnType;

	private Set<String> regIdList;
	

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte getPnType() {
		return pnType;
	}

	public void setPnType(byte pnType) {
		this.pnType = pnType;
	}

	public Set<String> getRegIdList() {
		return regIdList;
	}

	public void setRegIdList(Set<String> regIdList) {
		this.regIdList = regIdList;
	}
	

	/**
	 * lastIndex must greater than 3.
	 * 
	 * @param lastIndex
	 */
	public void truncateName(int lastIndex) {
		if (lastIndex < 3)
			return;
		if (StringUtils.isEmpty(name))
			return;
		if (lastIndex > name.length())
			return;
		this.name = this.name.substring(0, lastIndex - 3) + "...";
	}
	
	/**
	 * 
	 * @param lastIndex max length keep.
	 */
	public void truncateDesc(int lastIndex) {
		if (lastIndex < 3)
			return;
		if (StringUtils.isEmpty(desc))
			return;
		if (lastIndex > desc.length())
			return;
		this.desc = this.desc.substring(0, lastIndex - 3) + "...";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("name:");
		sb.append(name);
		sb.append(",");
		sb.append("desc:");
		sb.append(desc);
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
