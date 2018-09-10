package com.stylefeng.guns.modular.mongoModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author pinkman
 * @since 2018-08-29
 */
public class Mj_players {
	private int _id;
	private String nickname;
	private int sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String unionid;
	private long last_login_time;
	private boolean is_offline;
	private long create_time;
	private String createDateTime;
	private String lastLoginTime;
	
	private int numof_cards_1;
	private int numof_cards_2;
	private int numof_cards_3;
	private int referrer_uid;
	private int parent_id;
	private String ip;
	private int lv;
	private long last_share_time;
	private int hongbao;
	private int jinbi;

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
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
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public long getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(long last_login_time) {
		this.last_login_time = last_login_time;
	}
	public boolean isIs_offline() {
		return is_offline;
	}
	public void setIs_offline(boolean is_offline) {
		this.is_offline = is_offline;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getNumof_cards_1() {
		return numof_cards_1;
	}
	public void setNumof_cards_1(int numof_cards_1) {
		this.numof_cards_1 = numof_cards_1;
	}
	public int getNumof_cards_2() {
		return numof_cards_2;
	}
	public void setNumof_cards_2(int numof_cards_2) {
		this.numof_cards_2 = numof_cards_2;
	}
	public int getNumof_cards_3() {
		return numof_cards_3;
	}
	public void setNumof_cards_3(int numof_cards_3) {
		this.numof_cards_3 = numof_cards_3;
	}
	public int getReferrer_uid() {
		return referrer_uid;
	}
	public void setReferrer_uid(int referrer_uid) {
		this.referrer_uid = referrer_uid;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public long getLast_share_time() {
		return last_share_time;
	}
	public void setLast_share_time(long last_share_time) {
		this.last_share_time = last_share_time;
	}
	public int getHongbao() {
		return hongbao;
	}
	public void setHongbao(int hongbao) {
		this.hongbao = hongbao;
	}
	public int getJinbi() {
		return jinbi;
	}
	public void setJinbi(int jinbi) {
		this.jinbi = jinbi;
	}
}
