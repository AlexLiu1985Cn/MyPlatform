package com.efun.platform.module.game.bean;

import com.efun.platform.module.BaseDataBean;
/**
 * 单条游戏
 * @author Jesse
 *
 */
public class GameItemBean extends BaseDataBean{
	private String gameCode;
	private String gameType	;
	private String gameName;
	private int score;
	private String bigpic;
	private String smallpic;
	private String url;
	private String lang;
	private String androidDownload;
	private String iosDownload;
	private String androidVersion;
	private String gameDesc;
	private String videoUrl;
	private int like;
	private String version;
	private String packageName;
	private String packageSize;
	private String pic_display;
	
	private int status;
	public GameItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameItemBean(String gameCode, String gameType, String gameName,
			int score, String bigpic, String smallpic, String url, String lang,
			String androidDownload, String iosDownload, String androidVersion,
			String gameDesc, String videoUrl, int like, String version,
			String packageName, String packageSize, String pic_display) {
		super();
		this.gameCode = gameCode;
		this.gameType = gameType;
		this.gameName = gameName;
		this.score = score;
		this.bigpic = bigpic;
		this.smallpic = smallpic;
		this.url = url;
		this.lang = lang;
		this.androidDownload = androidDownload;
		this.iosDownload = iosDownload;
		this.androidVersion = androidVersion;
		this.gameDesc = gameDesc;
		this.videoUrl = videoUrl;
		this.like = like;
		this.version = version;
		this.packageName = packageName;
		this.packageSize = packageSize;
		this.pic_display = pic_display;
	}

	public String getPic_display() {
		return pic_display;
	}

	public void setPic_display(String pic_display) {
		this.pic_display = pic_display;
	}

	public String getBigpic() {
		return bigpic;
	}
	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getSmallpic() {
		return smallpic;
	}
	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getAndroidDownload() {
		return androidDownload;
	}
	public void setAndroidDownload(String androidDownload) {
		this.androidDownload = androidDownload;
	}
	public String getIosDownload() {
		return iosDownload;
	}
	public void setIosDownload(String iosDownload) {
		this.iosDownload = iosDownload;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getGameDesc() {
		return gameDesc;
	}
	public void setGameDesc(String gameDesc) {
		this.gameDesc = gameDesc;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
