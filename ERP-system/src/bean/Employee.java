package bean;

import java.util.Date;

/*
 * Ա����Ϣ��
 */
public class Employee {
	private int employeeID;// ���
	private String employeeName;// ����
	private boolean employeeGender;// �Ա�
	private Date employeeBirth;// ����
	private String employeePhone;// �ֻ���
	private String password;// ����

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public boolean isEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(boolean employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Date getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(Date employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
