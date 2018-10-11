package com.stylefeng.guns.modular.club.service;

import com.stylefeng.guns.modular.mongoModel.Club;

/**
 * 俱乐部
 * @author admin
 *
 */
public interface IClubService {

	/**
	 * 创建俱乐部
	 * @param club
	 * @param ownerId 俱乐部所有者
	 */
	void createClub(Club club, int ownerId);
	
}
