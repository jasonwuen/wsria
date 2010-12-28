package com.wsria.demo.web.file;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.wsria.arch.util.web.BrowserUtils;

/**
 * 下载Action
 *
 * @author HenryYan
 *
 */
@Controller
@Namespace("/file")
@Result(type = "stream", params = { "contentType", "application/octet-stream;charset=ISO8859-1", "inputName",
		"inputStream", "contentDisposition", "attachment;filename=${downFileName}", "bufferSize", "4096" })
public class DownloadAction {
	
	private static Logger logger = LoggerFactory.getLogger(DownloadAction.class); 

	private String fileName;
	private String downFileName;

	public void setFileName(String fileName) throws UnsupportedEncodingException {
		logger.debug("得到原始文件名：{}", fileName);
		
		// 转码
		fileName = URLDecoder.decode(fileName, "UTF-8");
		
		logger.debug("转码后的文件名：{}", fileName);
		
		this.fileName = fileName;
	}

	/**
	 * 如果传过来的fileName参数有路径处理后只返回文件名<br/>
	 * @return	例如path/aaa.txt，返回aaa.txt
	 * @throws UnsupportedEncodingException 
	 */
	public String getDownFileName() throws UnsupportedEncodingException {
		fileName = StringUtils.defaultIfEmpty(fileName, "");

		// 判断path/aaa/bbb.txt和path\aaa\bbb.txt两种情况
		String tempFileName = StringUtils.substringAfterLast(fileName, "/");
		if (tempFileName.length() == 0) {
			tempFileName = StringUtils.substringAfterLast(fileName, "\\");
		}
		if (tempFileName.length() == 0) {
			tempFileName = fileName;
		}

		// 处理中文
		logger.debug("去除路径信息后得到文件名：{}", tempFileName);
		
		// 处理IE浏览器乱码问题
		if (BrowserUtils.isIE(Struts2Utils.getRequest())) {
			downFileName = java.net.URLEncoder.encode(tempFileName, "UTF-8"); 
		} else {
			downFileName = new String(tempFileName.getBytes(), "ISO8859-1");
		}

		return downFileName;
	}

	public InputStream getInputStream() throws UnsupportedEncodingException {
		return ServletActionContext.getServletContext().getResourceAsStream("/" + fileName);
	}

	public String execute() {
		return "success";
	}

}
