package bean;

import java.util.Date;

/*
 * �ظ�������
 */
public class Reply {
	private int replyID;// �ظ����
	private String replyContent;// �ظ�����
	private int employeeID;// �ظ�Ա�����
	private int messageID;// �ظ���Ϣ���
	private Date replyTime;// �ظ�ʱ��

	public int getReplyID() {
		return replyID;
	}

	public void setReplyID(int replyID) {
		this.replyID = replyID;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
}
