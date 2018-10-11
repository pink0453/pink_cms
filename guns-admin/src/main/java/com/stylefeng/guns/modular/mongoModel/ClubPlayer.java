package com.stylefeng.guns.modular.mongoModel;

import com.mongodb.DBObject;

/**
 * Created by BQN on 2017/11/20.
 * 俱乐部成员相关信息扩充表
 */
public class ClubPlayer {
    /**俱乐部ID*/
     private Integer clubId ;
    /**玩家ID*/
     private Integer playerId ;
    /**玩家昵称*/
     private String nickname ;
    /**玩家头像*/
     private String faceUrl ;
    /**玩家可透支金额*/
     private Integer overdraft ;
    /**玩家已透支金额*/
    private Integer hasOver ;
    /**玩家在俱乐部内最后活跃时间*/
     private Long lastPlayerTime ;
    /**玩家在俱乐部内连续活跃天数*/
     private int activeDays ;

    public ClubPlayer(DBObject obj) {
        playerId = (Integer) obj.get("player_id");
        nickname = (String) obj.get("nickname");
        faceUrl = (String) obj.get("header");
    }

    public ClubPlayer(Integer playerId, String nickname, String faceUrl) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.faceUrl = faceUrl;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public Integer getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Integer overdraft) {
        this.overdraft = overdraft;
    }

    public Integer getHasOver() {
        return hasOver;
    }

    public void setHasOver(Integer hasOver) {
        this.hasOver = hasOver;
    }

    public Long getLastPlayerTime() {
        return lastPlayerTime;
    }

    public void setLastPlayerTime(Long lastPlayerTime) {
        this.lastPlayerTime = lastPlayerTime;
    }

    public int getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(int activeDays) {
        this.activeDays = activeDays;
    }
}
