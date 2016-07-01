package dao;

import java.util.List;

import bean.Message;
import util.Page;

public interface MessageDAO {
	// �����Ϣ
	public void addMessage(Message message);

	// ��ҳ��ȡ��Ϣ�б�
	public List<Message> getMessageList(Page page);

	// ����Ż�ȡ��Ϣ
	public Message getMessage(int messageID);

	// ��ȡ��Ϣ����
	public int getMessagesCount();
}
