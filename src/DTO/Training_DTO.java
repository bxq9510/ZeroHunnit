package DTO;

public class Training_DTO {
	private String t_name = null; // 운동부위 이름

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	
	/**
		CREATE TABLE training(
		t_name varchar2(10) PRIMARY KEY);
	 */
}

