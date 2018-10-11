package com.stylefeng.guns.modular.mongoModel;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBObject;

/**
 * Created by BQN on 2017/11/20.
 */
public class Club {
    /**俱乐部ID*/
   private Integer clubId;
    /**俱乐部名称*/
     private String clubName;
    /**俱乐部logo*/
     private String clubLogo;
    /**俱乐部公告文字*/
   private String noticeText;
    /**俱乐部公告图片*/
     private String noticeImgUrl;
    /**俱乐部简介*/
    private String synopsis;
    /**俱乐部群主玩家ID*/
    private Integer ownerId;
    /**俱乐部群主代理ID*/
   private Integer agentId;
    /**俱乐部管理人员*/
     private List<Integer> admins;
    /**俱乐部成员信息*/
   private List<ClubPlayer> players;

    private List<ClubPlayer> adPlayers;

    private ClubPlayer owner;

    /**俱乐部成员id*/
     private List<Integer> playerUids;

    /**俱乐部等级*/
     private Integer clubLv;
    /**俱乐部可创建最大房间数量*/
     private Integer maxRoomCnt;
    /**俱乐部可拥有成员的最大数量*/
   private Integer maxPlayerCnt;
    /**俱乐部最后缴费日期*/
    private Long lastPayTime;
    /**俱乐部官方设置比例*/
    private Double officialProp;
    /**是否有效*/
    private boolean disable;
    /**是否允许搜索俱乐部ID加入*/
     private boolean canSearch;
    /**对外分享地址*/
     private String shareUrl;
    /**创建时间*/
    private Long createTime;
    /**俱乐部定制的游戏id*/
    private List<ClubWanFa> games;
    public Club() {
    }

    /**
     * player中控制Club列表，Club存放id，name，logo
     * @param clubId
     * @param clubName
     * @param clubLogo
     */
    public Club(Integer clubId, String clubName, String clubLogo) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubLogo = clubLogo;
    }

    public Club( String clubName, String clubLogo, String noticeText, String noticeImgUrl, String synopsis, Integer agentId, Integer clubLv, Integer maxRoomCnt, Integer maxPlayerCnt, boolean disable, boolean canSearch, String shareUrl, Long createTime) {
        this.clubName = clubName;
        this.clubLogo = clubLogo;
        this.noticeText = noticeText;
        this.noticeImgUrl = noticeImgUrl;
        this.synopsis = synopsis;
        this.agentId = agentId;
        this.clubLv = clubLv;
        this.maxRoomCnt = maxRoomCnt;
        this.maxPlayerCnt = maxPlayerCnt;
        this.disable = disable;
        this.canSearch = canSearch;
        this.shareUrl = shareUrl;
        this.createTime = createTime;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }

    public String getNoticeImgUrl() {
        return noticeImgUrl;
    }

    public void setNoticeImgUrl(String noticeImgUrl) {
        this.noticeImgUrl = noticeImgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public List<Integer> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Integer> admins) {
        this.admins = admins;
    }

    public List<ClubPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<ClubPlayer> players) {
        this.players = players;
    }


    public List<ClubPlayer> getAdPlayers() {
        return adPlayers;
    }

    public void setAdPlayers(List<ClubPlayer> adPlayers) {
        this.adPlayers = adPlayers;
    }

    public ClubPlayer getOwner() {
        return owner;
    }

    public void setOwner(ClubPlayer owner) {
        this.owner = owner;
    }

    public void setPlayersByObject(List<DBObject> players) {
        List res = new ArrayList();
        for (DBObject tmpObject : players) {
            ClubPlayer player = new ClubPlayer(tmpObject);
            res.add(player);
        }
        this.setPlayers(res);
    }

    public List<Integer> getPlayerUids() {
        return playerUids;
    }

    public void setPlayerUids(List<Integer> playerUids) {
        this.playerUids = playerUids;
    }

    public Integer getClubLv() {
        return clubLv;
    }

    public void setClubLv(Integer clubLv) {
        this.clubLv = clubLv;
    }

    public Integer getMaxRoomCnt() {
        return maxRoomCnt;
    }

    public void setMaxRoomCnt(Integer maxRoomCnt) {
        this.maxRoomCnt = maxRoomCnt;
    }

    public Integer getMaxPlayerCnt() {
        return maxPlayerCnt;
    }

    public void setMaxPlayerCnt(Integer maxPlayerCnt) {
        this.maxPlayerCnt = maxPlayerCnt;
    }

    public Long getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(Long lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    public Double getOfficialProp() {
        return officialProp;
    }

    public void setOfficialProp(Double officialProp) {
        this.officialProp = officialProp;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isCanSearch() {
        return canSearch;
    }

    public void setCanSearch(boolean canSearch) {
        this.canSearch = canSearch;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<ClubWanFa> getGames() {
        return games;
    }

    public void setGames(List<ClubWanFa> games) {
        this.games = games;
    }
}
