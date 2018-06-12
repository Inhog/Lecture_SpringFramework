package com.clustering.project.util;

import java.util.UUID;

import org.springframework.stereotype.Component;


/***
 * �ߺ� Primary Key ���
 * Unique Primary Key ����
 * �ŤŤ���
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
