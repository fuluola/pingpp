package com.pingpp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.pingpp.api.util.MatrixToImageWriter;
import com.pingxx.web.dao.PingxxOrderDao;
import com.pingxx.web.entity.PingxxOrderEntity;

@Controller
public class TestController {

	public static Log logger = LogFactory.getLog(TestController.class);
	
	@Autowired
	private PingxxOrderDao pingxxOrderDao;
	
	@RequestMapping(value="qrcode")
	@ResponseBody
	public List<PingxxOrderEntity> test(HttpServletRequest request, HttpServletResponse response){
	
		try {
            
		     String content = "https://my.oschina.net/fuluola";
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		   //  MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
		     List<PingxxOrderEntity> list = pingxxOrderDao.findOrderPage(0, 0);
		     return list;
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		return null;
	}
}
