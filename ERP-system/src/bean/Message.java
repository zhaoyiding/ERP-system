package bean;

import java.util.Date;

/*
 * ��Ϣ������
 */
public class Message {
	private int messageID;// ��Ϣ���
	private String messageTitle;// ��Ϣ����
	private String messageContent;// ��Ϣ����
	private int employeeID;// ������Ϣ��Ա�����
	private Date publishTime;// ��Ϣ����ʱ��

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
