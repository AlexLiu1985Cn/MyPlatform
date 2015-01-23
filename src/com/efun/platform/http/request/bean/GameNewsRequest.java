package com.efun.platform.http.request.bean;


/**
 * 游戏-游戏资讯
 * @author Jesse
 *
 */
public class GameNewsRequest extends BaseRequestBean{
	private String gameCode;
	private String platform;
	public GameNewsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GameNewsRequest(String gameCode, String platform) {
		super();
		this.gameCode = gameCode;
		this.platform = platform;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
