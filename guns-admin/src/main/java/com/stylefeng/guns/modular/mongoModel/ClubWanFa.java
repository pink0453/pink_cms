package com.stylefeng.guns.modular.mongoModel;

import com.alibaba.fastjson.JSONObject;

public class ClubWanFa {
    private Integer id;
    private Integer clubId;
    private Integer mapId;
    private String ops;
    private String mapName;
    private String wanfa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getWanfa() {
        return wanfa;
    }

    public void setWanfa(String wanfa) {
        this.wanfa = wanfa;
    }

    public JSONObject toJson(){
        JSONObject ret = new JSONObject();
        ret.put("id", this.getId());
        ret.put("club_id", this.getClubId());
        ret.put("map_id", this.getMapId());
        ret.put("ops", this.getOps());
        ret.put("map_name", this.getMapName());
        ret.put("wanfa", this.getWanfa());
        return ret;
    }
}
