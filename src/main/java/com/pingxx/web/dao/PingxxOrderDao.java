package com.pingxx.web.dao;

import java.util.List;

import com.pingxx.web.entity.PingxxOrderEntity;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年10月10日 
 */
public interface PingxxOrderDao {

	public List<PingxxOrderEntity> findOrderPage(int pageSize,int pageIndex);
}
