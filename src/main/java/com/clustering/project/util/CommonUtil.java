package com.clustering.project.util;

import java.util.UUID;

import org.springframework.stereotype.Component;


/***
 * 掻差 Primary Key 号持
 * Unique Primary Key 持失
 * づづちし
 * @author Inhog
 *
 */
@Component
public class CommonUtil {
	public String getUniqueSequence() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-","");
	}

}
