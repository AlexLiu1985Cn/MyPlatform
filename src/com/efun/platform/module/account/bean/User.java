package com.efun.platform.module.account.bean;
/**
 * 用户信息
 * @author Jesse
 *
 */
public class User extends ResultBean{
	private long createdTime;//账号建立时间
	private long modifiedTime;//账号修改时间
	private long uid;//用户id
	private String username;//用户登陆账号
	private String accountName;//用户名
	private String rango;//军衔
	private String rangoLevel;//军衔等级
	private int memberLevel;//会员等级
	private int experienceValue;//经验值
	private int goldValue;//积金数量
	private String address;//地址
	private String sex;//性别
	private String icon;//头像
	private String areaDesc;//地域描述
	private int expPercentage;//经验百分比
	private int levelupExp;//下一级经验值
	private int currentExp;//现在经验值
	private String isVip;//是否是vip
	private String isAccept;//是否同意
	private String sign;//Sign值
	private String privilege;//特权
	private String password;//密码
	private String accessToken;//登陆标识
	private String ip;//登陆ip
	private String loginType;//登陆类型
	private String thirdId;//第三方登陆id
	private String email;//用户邮箱
	private String authed;//授权
	private String auth_code;//授权代码
	private String gameCode;//游戏代码
	private String gotGold;//获得积金
	private String urlParamString;//链接的参数集
	private String timestamp;//时间戳
	private String phone;//手机绑定 为空为未绑定,不为空为已绑定
	private String isAuthPhone;//
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(long uid, String username) {
		super();
		this.uid = uid;
		this.username = username;
	}
	
	public int getExperienceValue() {
		return experienceValue;
	}
	public void setExperienceValue(int experienceValue) {
		this.experienceValue = experienceValue;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUid() {
		return uid+"";
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public long getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getRangoLevel() {
		return rangoLevel;
	}
	public void setRangoLevel(String rangoLevel) {
		this.rangoLevel = rangoLevel;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public int getGoldValue() {
		return goldValue;
	}
	public void setGoldValue(int goldValue) {
		this.goldValue = goldValue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	public int getExpPercentage() {
		return expPercentage;
	}
	public void setExpPercentage(int expPercentage) {
		this.expPercentage = expPercentage;
	}
	public int getLevelupExp() {
		return levelupExp;
	}
	public void setLevelupExp(int levelupExp) {
		this.levelupExp = levelupExp;
	}
	public int getCurrentExp() {
		return currentExp;
	}
	public void setCurrentExp(int currentExp) {
		this.currentExp = currentExp;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public String getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthed() {
		return authed;
	}
	public void setAuthed(String authed) {
		this.authed = authed;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getGotGold() {
		return gotGold;
	}
	public void setGotGold(String gotGold) {
		this.gotGold = gotGold;
	}
	public String getUrlParamString() {
		return urlParamString;
	}
	public void setUrlParamString(String urlParamString) {
		this.urlParamString = urlParamString;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIsAuthPhone() {
		return isAuthPhone;
	}
	public void setIsAuthPhone(String isAuthPhone) {
		this.isAuthPhone = isAuthPhone;
	}
	@Override
	public String toString() {
		return "User [createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", uid=" + uid + ", username=" + username
				+ ", accountName=" + accountName + ", rango=" + rango
				+ ", rangoLevel=" + rangoLevel + ", memberLevel=" + memberLevel
				+ ", experienceValue=" + experienceValue + ", goldValue="
				+ goldValue + ", address=" + address + ", sex=" + sex
				+ ", icon=" + icon + ", areaDesc=" + areaDesc
				+ ", expPercentage=" + expPercentage + ", levelupExp="
				+ levelupExp + ", currentExp=" + currentExp + ", isVip="
				+ isVip + ", isAccept=" + isAccept + ", sign=" + sign
				+ ", privilege=" + privilege + ", password=" + password
				+ ", accessToken=" + accessToken + ", ip=" + ip
				+ ", loginType=" + loginType + ", thirdId=" + thirdId
				+ ", email=" + email + ", authed=" + authed + ", auth_code="
				+ auth_code + ", gameCode=" + gameCode + ", gotGold=" + gotGold
				+ ", urlParamString=" + urlParamString + ", timestamp="
				+ timestamp + ", phone=" + phone + ", isAuthPhone="
				+ isAuthPhone + "]";
	}
	
}
