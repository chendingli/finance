package com.chinasoft.util.login.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @DESC:微信登录详细信息: vo
 */
public class WxUserDetailVo {

	// 成功返回
	@JsonProperty("openid")
	private String openid;	

	@JsonProperty("nickname")
	private String nickname;

	@JsonProperty("sex")
	private Integer sex;

	@JsonProperty("province")
	private String province;

	@JsonProperty("city")
	private String city;

	@JsonProperty("country")
	private String country;

	@JsonProperty("headimgurl")
	private String headimgurl;

	@JsonProperty("privilege")
	private String[] privilege;

	@JsonProperty("unionid")
	private String unionid;

	// 错误返回
	@JsonProperty("errcode")
	private String errCode;

	@JsonProperty("errmsg")
	private String errMsg;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
