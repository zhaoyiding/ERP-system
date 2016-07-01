package dao;

import java.util.List;

import bean.Reply;
import util.Page;

public interface ReplyDAO {
	//��ӻظ�
	public void addReply(Reply reply);
	
	//������Ϣ��š�ҳ����Ϣ��ȡ��Ϣ�б�
	public List<Reply> getReplyList(int messageID, Page page);

	//��ȡ��Ӧͬһ��Ϣ�Ļظ�
	public int getRepliesCount(int messageID);
}
