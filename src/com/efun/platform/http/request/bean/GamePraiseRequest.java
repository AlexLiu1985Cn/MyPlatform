package com.efun.platform.http.request.bean;

/**
 * 游戏 - 点赞
 * @author Jesse
 *
 */
public class GamePraiseRequest extends BaseRequestBean{
	private String uid;
	private String gameCode;
	
	public GamePraiseRequest(String gameCode) {
		super();
		this.gameCode = gameCode;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	
}
