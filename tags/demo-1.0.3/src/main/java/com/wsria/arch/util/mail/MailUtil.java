package com.wsria.arch.util.mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil extends JavaMailSenderImpl {
	
	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

	/**
	 * Send simple message
	 * @param from
	 * @param to
	 * @param cc
	 * @param subject
	 * @param text
	 * @return
	 */
	public boolean send(String from, String to, String cc, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();

		try {
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			if (cc != null) {
				message.setCc(cc);
			}
			message.setText(text);
			this.send(message);
			logger.info("成功发送邮件，从：{}，到：{}，标题：{}， 内容：{}", new Object[] {
					from, to, subject, text
			});
		} catch (Exception e) {
			logger.error("发送邮件失败，从：{}，到：{}，标题：{}， 内容：{}", new Object[] {
					from, to, subject, text
			});
			logger.error("发送邮件失败：", e);
		}

		return true;

	}

	public boolean sendFromFile(String from, String to, String cc, String subject, String filePath) {
		MimeMessage mailMessage = this.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setFrom(from);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			if (cc != null) {
				messageHelper.setCc(cc);
			}
			messageHelper.setText(getMailContent(filePath), true);
			this.send(mailMessage);
			logger.info("成功发送邮件，从：{}，到：{}，标题：{}， 文件路径：{}", new Object[] {
					from, to, subject, filePath
			});
		} catch (Exception e) {
			logger.info("发送邮件失败，从：{}，到：{}，标题：{}， 文件路径：{}", new Object[] {
					from, to, subject, filePath
			});
			logger.error("发送邮件失败：", e);
		}
		return true;
	}

	private static String getMailContent(String filePath) {
		StringBuffer content = new StringBuffer();
		FileReader fr = null;
		try {
			fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				content.append(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (Exception e) {
			}
		}

		return content.toString();
	}

	public static void main(String[] args) {
		System.out.println(getMailContent("d:/dn-pb-test.log"));
	}
}
