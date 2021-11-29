

# ZeroHunnit

> ZeroHunnit은 사용자의 개인 운동(운동부위, 날짜, 총 무게)을 기록할 수 있고, 누적된 기록을 확인할 수 있으며, 사용자의 든 무게의 총합을 통해 Ranking으로 보여주어 사용자 간 운동에 대한 동기부여를 유발하는 프로그램입니다.

## 소개

🙋‍♂️ 참가인원 : 백승한 윤백선

⏳ 소요기간 : 2021.11.11 13:00 ~ 2021.11.15 17:00 - 1차 버전 끝

🛠 사용 Tool

*  JavaSE-1.8
*  Eclipse jee 2021-06
*  Git
*  Oracle 11.2.0.1.0

💻 DevOps

*  GitHub



## ERD

![image-20211111213833988](https://user-images.githubusercontent.com/84169773/141306844-8d2dcdbf-7564-49f7-b2c0-531a0d66de16.png)

> ### ERD 관계도 입니다.
>
> 다수의 Customer(고객)는 Training(운동종목)을 여러개 가질 수 있고, Training 또한 여러 Customer의 운동기록을 가질 수 있는 M:N의 관계를 가집니다.
>
> 테이블 간의 관계는 Do Train(운동기록)을 통해 Info 테이블에 담기게 됩니다. 아래는 테이블의 상세 명세입니다.

## 테이블 명세

![image-20211111214648279](https://user-images.githubusercontent.com/84169773/141306903-d3d1acf4-9b17-4ad3-a91e-07a803e302fd.png)

- Customer TABLE

  CREATE TABLE customer(
  	id varchar2(10),
  	name varchar2(10) PRIMARY KEY,
  	height number, weight number
  );

- Training TABLE

  CREATE TABLE training(
  	t_name varchar2(10) PRIMARY KEY
  );

  - 운동 목록 추가 

  INSERT INTO training VALUES('가슴삼두');

  INSERT INTO training VALUES('등이두');

  INSERT INTO training VALUES('하체어깨');

  **엇 잠깐, 왜 직접 insert 했을까요 ❓**
  
  
  ![Sorry](https://user-images.githubusercontent.com/84169773/141306925-bc7511d4-56f6-4c4c-a1df-fcfc6e16c541.jpg)


  > 사실 따로 구현할 수는 있는데 3개밖에 안되기도 하고,,,,ㅎ 죄송합니다

- 회원 기록 TABLE

  CREATE TABLE info(
  	c_name varchar2(10),
  	t_name varchar2(10),
  	tdate date default sysdate,
  	liftweight number,
  	FOREIGN KEY(c_name) REFERENCES customer(name) ON DELETE CASCADE,
  	FOREIGN KEY(t_name) REFERENCES training(t_name) ON DELETE SET NULL
  );

> Name을 Primary Key로 받는 Customer 테이블과 t_name을 Primary Key로 받는 Training 테이블의 다대다 관계는 Info에서 c_name, t_name이라는 Foreign Key로 만나 Info 테이블에서 운동기록을 저장하게 됩니다.

## Flow Chart 

![image-20211115114622853](https://user-images.githubusercontent.com/84169773/141745294-0ea436c8-8460-41f2-a930-77293bdc99b7.png)

## 프로젝트 Todo's

https://github.com/seunghan-baek/ZeroHunnit/projects/2

![image-20211115170531909](https://user-images.githubusercontent.com/84169773/141745726-af050ef4-dbcf-4d68-a47a-8d5a9189b31e.png)

> ### github의 project 탭을 응용하여 각자의 TodoList를 서로에게 공유하며 협업하였습니다.
>
> To do에는 예정된 할 일 및 당일 해결과제 등을 정리하여 적어두었고 Todo를 진행시에는 In progress 탭으로, 작업이 마무리 되면 Pull & Request와 더불어 Done 탭에 옮기며 말해주는 것으로 각자의 작업이 어느 정도 마무리 되었는지 확인이 가능했습니다.
>
> 특히 In progress에서 서로 필요시에 Project를 봐달라는 요청에 따라 발생한 문제점을 즉각 확인하고 토의하여 오류를 개선하는것에서 Project 탭을 활용하는 것은 협업에 있어 매우 편리하였습니다.



## 프로젝트 Pull & Request, Networks

<img src="https://user-images.githubusercontent.com/84169773/141745792-7d1c04b1-58db-4cb1-8ac0-56340b676287.png" alt="image-20211115122056890" style="zoom:67%;" /><img src="https://user-images.githubusercontent.com/84169773/141745794-e641f4d9-f03e-464d-bd16-2af20c76463b.png" alt="image-20211115122842797" style="zoom:67%;" />

>  11일부터 현재까지 각자의 소스코드를 보내주고(Push), 충돌(Conflict)을 해결하고 받아오기(Pull) 및 병합(Merge)하여 동기화된 코드를 유지하며 협업을 하였습니다.
>  <img src="https://user-images.githubusercontent.com/84169773/141745855-865e094f-11d3-471d-8832-a3d2ba301446.png" alt="image-20211115123029839" style="zoom:80%;" />
>
>  #### 각자 Addition한 Code및 양 또한 비슷합니다.
>
>  🧑 윤백선은 DAO 및 DTO를 설계 및 작업하였습니다.
>
>  👦 백승한은 GUI 각 패널 설계 및 DAO 연결을 작업하였습니다.
>
>  🤝 SQL 관련 작업은 공통된 작업으로 두어 함께 설계하였습니다.



## 각 클래스간 메서드 관계

#### ☑ Manager GUI & Manager DAO

> 화살표 관계 = DAO ↔ GUI 

1. userAdd() ↔ Register_Panel()

   * INSERT INTO customer VALUES (?, ?, ?, ?)

   * 회원가입 기능
   * Customer 테이블의 각 Attribute(id, name, height, weight)를 입력받아 Insert함.

   

2. login() ↔ Login_Panel()

   * User 로그인 기능

   * Customer 테이블의 id, weight를 아이디, 패스워드로 입력받아 입력값과 일치할 때 유저 개인의 Panel을 실행함.

     

3. admin ↔ Admin_Panel()

   * admin은 Admin_DTO에서 id, pw변수에 직접 literal을 입력하고 저장하여 고유한 값을 유지하였음.

   * 각 TextField에 입력한 값이 DTO의 ID와 PW 일치할 때 관리자의 Panel을 실행함.

     

#### ☑ Admin GUI & Admin DAO

> 화살표 관계 = DAO ↔ GUI 

1. usrEdit() ↔ mod_Panel()

   * UPDATE customer SET weight = ? WHERE name = ?

   * 유저 몸무게 수정 기능 

   * User의 이름을 검색하여 몸무게를 Update함.

     

2. usrDel() ↔ del_Panel()

   * DELETE FROM customer WHERE name = ?

   * 유저 삭제 기능 (블랙리스트)

   * User의 이름을 검색하여 해당 유저를 삭제함

     

3. usrAll() ↔ view_Panel()  + detailView()

   * SELECT * FROM customer

   * 유저 전체보기 기능

   * 각 튜플을 ArrayList로 받아 return하여 detailview()를 통해 각 Attribute의 상세정보를 확인할 수 있음.

     

4. GUI's logout

   * 로그아웃 기능

   * logout 버튼 클릭시 ```this.setVisable(false)```를 통해 해당 Panel을 종료함

     

#### ☑ User GUI & User DAO

> * 화살표 관계 = DAO ↔ GUI 
> * 각 관계의 대한 method의 자세한 내용은 아래에 첨부했습니다!

1. infoOne() ↔ record_Panel() + loadRecord()

   * SELECT * FROM info WHERE c_name = ?

   * 유저 개인 기록 조회 기능

   * record_Btn 클릭시 loadRecord() 메서드 실행되며 누적된 유저의 개인 기록을 볼 수 있음.

     

2.  infoAdd() ↔ insert_Panel()

   * INSERT INTO info VALUES (?, ?, DEFAULT, ?)

   * 유저 개인 기록 추가 기능

   * Info 테이블의 각 Attribute(c_name, t_name, tdate, liftweight)를 입력받아 insert함.

     

3. rank() ↔ rank_Panel() + loadRank()

   1. 전체 랭킹보기

      * SELECT c_name, SUM(liftweight) FROM info GROUP BY c_name ORDER BY SUM(liftweight) DESC
      * 랭킹보기 기능
      * 각 유저의 총 liftweight 합을 기준으로 등수를 정함.

   2. 체급별 랭킹보기

      * SELECT c_name, sum(liftweight) FROM (SELECT customer.name AS c_name, info.liftweight AS liftweight FROM info JOIN customer ON c_name = name AND customer.weight = ?) GROUP BY c_name ORDER BY sum(liftweight) DESC;
      * 체급별 랭킹보기
      * 각 User는 회원가입시 자신의 체급을 적게 되어있음. 프로그램의 분류된 체급에 따라 체급별 liftweight 합을 기준으로 등수를 정함.

      

4. GUI's logout

   * 로그아웃 기능
   * logout 버튼 클릭시 ```this.setVisable(false)```를 통해 해당 Panel을 종료함/



## DAO 메서드 처리 과정

#### Manager_DAO

```java
	// 회원가입
	public void usrAdd(User_DTO usr) {
		String sql = "insert into customer values (?,?,?,?)";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getId());
			psmt.setString(2, usr.getName());
			psmt.setInt(3, usr.getHeight());
			psmt.setInt(4, usr.getWeight());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 등록완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
```

**usrAdd(User_DTO usr)**

> 1. 회원가입 기능을 수행할 ```usrAdd(User_DTO usr)```로 Manager_GUI 클래스의 매개변수 User_DTO usr 를 참조하면서 각 Argument값을 받아왔습니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```insert into customer values (?,?,?,?)```
> 3. 예외를 처리하기 위한 try carch문 으로 들어서고, 미완성된 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 4. DB에 연결이 된다면 ```PreparedStatement 타입의 psmt```가 ```Connection 타입의 conn```의 ```prepareStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```insert into customer values (?,?,?,?)``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 5. 미완성된 쿼리문을 바인딩하기 위해 변수 ```usr```을 통해 ```User_DTO```의 getter로 쿼리문 완성시킵니다.
> 6. ?에는 순서대로 id, name, height, weight값이 입력되었고, 이는 Manager_GUI 클래스에서 사용자의 입력값을 매개변수를 통해 가져온 값입니다.
> 7. ```executeUpdate()```를 통해 쿼리문을 완성하고 반영된 레코드의 DB를 갱신합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 Manager_GUI 클래스로 복귀합니다.



```java
	// 로그인
	public ArrayList<User_DTO> login() {
		String sql = "select * from customer";
		ResultSet rs = null;
		User_DTO returnDTO = null;
		ArrayList<User_DTO> ulist = new ArrayList<>();
		try {
			getConnection();
			Statement p = conn.createStatement();
			rs = p.executeQuery(sql);
			while (rs.next()) {
				returnDTO = new User_DTO();
				returnDTO.setId(rs.getString("id"));
				returnDTO.setName(rs.getString("name"));
				returnDTO.setHeight(rs.getInt("height"));
				returnDTO.setWeight(rs.getInt("weight"));
				ulist.add(returnDTO);
			}
			return ulist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
```

**login()**

> 1. 로그인 기능을 수행할 ```login()```로 Manager_GUI 클래스에서 로그인하기 위해 필요한 사용자의 정보를 리턴값을 통해 전달해주는 메서드입니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```select * from customer```
> 3. 리턴값을 위해 쿼리의 결과를 ResultSet타입으로 반환해주는 ```ResultSet 타입의 rs```, ResultSet타입으로 반환된 값을 저장하기 위한 ```User_DTO 타입의 returnDTO```, 마지막으로 리턴값인 ```User_DTO 타입의 ArrayList ulist```를 선언합니다.
> 4. 예외를 처리하기 위한 try carch문 으로 들어서고, 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 5. DB에 연결이 된다면 ```Statement 타입의 p```가 ```Connection 타입의 conn```의 ```createStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```select * from customer``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 6. User_DTO 객채를 생성하고 쿼리결과가 다중 튜플이기 때문에 순회를 위해 while문을 통해서 rs의 내용을 메서드를 호출한 위치로 전달하기 위한 User_DTO 객채인 returnDTO에 저장합니다.
> 7. 결과가 모두 저장되면 while문이 종료되고, return ulist 로 결과값이 넘어 갈 수 있도록 합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 Manager_GUI 클래스로 복귀합니다.



#### Admin_DAO

```java
	// 회원수정
	public void usrEdit(User_DTO usr) {
		String sql = "update customer set weight=? where name=?";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, usr.getWeight());
			psmt.setString(2, usr.getName());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 수정완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
```

**usrEdit(User_DTO usr)**

> 1. 회원 수정 기능을 수행할 ```usrEdit(User_DTO usr)```로 Admin_GUI 클래스의 매개변수 User_DTO usr 를 참조하면서각 Argument값을 받아왔습니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```update customer set weight=? where name=?```
> 3. 예외를 처리하기 위한 try carch문 으로 들어서고, 미완성된 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 4. DB에 연결이 된다면 ```PreparedStatement 타입의 psmt```가 ```Connection 타입의 conn```의 ```prepareStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```update customer set weight=? where name=?``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 5. 미완성된 쿼리문을 바인딩하기 위해 변수 ```usr```을 통해 ```User_DTO```의 getter로 쿼리문 완성시킵니다.
> 6. ?에는 순서대로 weight, name값이 입력되었고, 이는 Manager_GUI 클래스에서 사용자의 입력값을 매개변수를 통해 가져온 값입니다.
> 7. ```executeUpdate()```를 통해 쿼리문을 완성하고 반영된 레코드의 DB를 갱신합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 Admin_GUI 클래스로 복귀합니다.



```java
	// 회원삭제
	public void usrDel(User_DTO usr) {
		String sql = "delete from customer where name=?";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getName());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 삭제완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
```

**usrDel(User_DTO usr)**

> 1. 회원 삭제 기능을 수행할 ```usrDel(User_DTO usr)```로 Admin_GUI 클래스의 매개변수 User_DTO usr 를 참조하면서 Argument값을 받아왔습니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```delete from customer where name=?```
> 3. 예외를 처리하기 위한 try carch문 으로 들어서고, 미완성된 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 4. DB에 연결이 된다면 ```PreparedStatement 타입의 psmt```가 ```Connection 타입의 conn```의 ```prepareStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```delete from customer where name=?``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 5. 미완성된 쿼리문을 바인딩하기 위해 변수 ```usr```을 통해 ```User_DTO```의 getter로 쿼리문 완성시킵니다.
> 6. ?에는 name값이 입력되었고, 이는 Manager_GUI 클래스에서 사용자의 입력값을 매개변수를 통해 가져온 값입니다.
> 7. ```executeUpdate()```를 통해 쿼리문을 완성하고 반영된 레코드의 DB를 갱신합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 Admin_GUI 클래스로 복귀합니다.



```java
	// 회원전체보기
	public ArrayList<User_DTO> usrAll() {
		String sql = "select * from customer";
		ResultSet rs = null;
		User_DTO returnDTO = null;
		ArrayList<User_DTO> ulist = new ArrayList<>();
		try {
			getConnection();
			Statement p = conn.createStatement();
			rs = p.executeQuery(sql);
			while (rs.next()) {
				returnDTO = new User_DTO();
				returnDTO.setId(rs.getString("id"));
				returnDTO.setName(rs.getString("name"));
				returnDTO.setHeight(rs.getInt("height"));
				returnDTO.setWeight(rs.getInt("weight"));
				ulist.add(returnDTO);
			}
			return ulist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
```

**usrAll()**

> 1. 회원전체보기 기능을 수행할 ```usrAll()```로 Admin_GUI 클래스에서 관리자가 사용자 관리를 위해 필요한 정보를 리턴값을 통해 전달해주는 메서드로 위의 User_GUI의 login() 메서드와 일치합니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```select * from customer```
> 3. 리턴값을 위해 쿼리의 결과를 ResultSet타입으로 반환해주는 ```ResultSet 타입의 rs```, ResultSet타입으로 반환된 값을 저장하기 위한 ```User_DTO 타입의 returnDTO```, 마지막으로 리턴값인 ```User_DTO 타입의 ArrayList ulist```를 선언합니다.
> 4. 예외를 처리하기 위한 try carch문 으로 들어서고, 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 5. DB에 연결이 된다면 ```Statement 타입의 p```가 ```Connection 타입의 conn```의 ```createStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```select * from customer``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 6. User_DTO 객채를 생성하고 쿼리결과가 다중 튜플이기 때문에 순회를 위해 while문을 통해서 rs의 내용을 메서드를 호출한 위치로 전달하기 위한 User_DTO 객채인 returnDTO에 저장합니다.
> 7. 결과가 모두 저장되면 while문이 종료되고, return ulist 로 결과값이 넘어 갈 수 있도록 합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 Admin_GUI 클래스로 복귀합니다.



#### User_DAO

```java
	// 운동기록 등록
	public void infoAdd(Info_DTO usr) {
		String sql = "insert into info values (?,?,default,?)";
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getC_name());
			psmt.setString(2, usr.getT_name());
			psmt.setInt(3, usr.getLiftWeight());
			int k = psmt.executeUpdate();
			System.out.println(k + "건 등록완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
```

**infoAdd(Info_DTO usr)**

> 1. 운동기록 등록 기능을 수행할 ```infoAdd(Info_DTO usr)```로 User_GUI 클래스의 매개변수 Info_DTO usr 를 참조하면서 각 Argument값을 받아왔습니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```insert into info values (?,?,default,?)```
> 3. 예외를 처리하기 위한 try carch문 으로 들어서고, 미완성된 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 4. DB에 연결이 된다면 ```PreparedStatement 타입의 psmt```가 ```Connection 타입의 conn```의 ```prepareStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```insert into info values (?,?,default,?)``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 5. 미완성된 쿼리문을 바인딩하기 위해 변수 ```usr```을 통해 ```User_DTO```의 getter로 쿼리문 완성시킵니다.
> 6. ?에는 순서대로 c_name, t_name, liftWeight값이 입력되었고, 이는 User_GUI 클래스에서 사용자의 입력값을 매개변수를 통해 가져온 값입니다.
> 7. ```executeUpdate()```를 통해 쿼리문을 완성하고 반영된 레코드의 DB를 갱신합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 User_GUI 클래스로 복귀합니다.



```java
	// 누적기록 확인
	public ArrayList<Info_DTO> infoOne(User_DTO usr) {
		String sql = "select * from info where c_name=?";
		ResultSet rs = null;
		Info_DTO returnDTO = null;
		ArrayList<Info_DTO> tlist = new ArrayList<>();
		try {
			getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, usr.getName());
			rs = psmt.executeQuery();
			while (rs.next()) {
				returnDTO = new Info_DTO();
				returnDTO.setC_name(rs.getString("c_name"));
				returnDTO.setT_name(rs.getString("t_name"));
				returnDTO.setLiftWeight(rs.getInt("liftWeight"));
				returnDTO.setT_date(rs.getString("tdate"));
				tlist.add(returnDTO);
			}
			return tlist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
```

**infoOne(User_DTO usr)**

> 1. 누적기록 확인 기능을 수행할 ```infoOne(User_DTO usr)```로 User_GUI 클래스에서 로그인한 사용자의 기록 확인을 위해 필요한 정보를 리턴값을 통해 전달해주는 메서드입니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```select * from info where c_name=?```
> 3. 리턴값을 위해 쿼리의 결과를 ResultSet타입으로 반환해주는 ```ResultSet 타입의 rs```, ResultSet타입으로 반환된 값을 저장하기 위한 ```Info_DTO 타입의 returnDTO```, 마지막으로 리턴값인 ```Info_DTO 타입의 ArrayList tlist```를 선언합니다.
> 4. 예외를 처리하기 위한 try carch문 으로 들어서고, 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 5. DB에 연결이 된다면 ```Statement 타입의 p```가 ```Connection 타입의 conn```의 ```createStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```select * from info where c_name=?``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 6. 미완성된 쿼리문을 바인딩하기 위해 변수 ```usr```을 통해 ```User_DTO```의 getter로 쿼리문 완성시킵니다.
> 7. ?에는 name값이 입력되었고, 이는 Manager_GUI 클래스에서 사용자가 로그인을 하면서 넘겨준 객체의 주소값으로 Manager_GUI 클래스때와 마찬가지로 매개변수를 통해 가져온 값입니다.
> 8. Info_DTO 객채를 생성하고 쿼리결과가 다중 튜플이기 때문에 순회를 위해 while문을 통해서 rs의 내용을 메서드를 호출한 위치로 전달하기 위한 User_DTO 객채인 returnDTO에 저장합니다.
> 9. 결과가 모두 저장되면 while문이 종료되고, return ulist 로 결과값이 넘어 갈 수 있도록 합니다.
> 10. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 User_GUI 클래스로 복귀합니다.



```java
	// 랭킹
	public ArrayList<Info_DTO> rank() {
		String sql = "select c_name, sum(liftweight) from info group by c_name order by sum(liftweight) desc";
		ResultSet rs = null;
		Info_DTO returnDTO = null;
		ArrayList<Info_DTO> tlist = new ArrayList<>();
		try {
			getConnection();
			Statement p = conn.createStatement();
			rs = p.executeQuery(sql);
			while (rs.next()) {
				returnDTO = new Info_DTO();
				returnDTO.setC_name(rs.getString("c_name"));
				returnDTO.setLiftWeight(rs.getInt("sum(liftweight)"));
				tlist.add(returnDTO);
			}
			return tlist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
```

**rank()**

> 1. 랭킹 기능을 수행할 ```rank()```로 User_GUI 클래스에서 사용자가 필요한 정보를 리턴값을 통해 전달해주는 메서드입니다.
> 2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```select c_name, sum(liftweight) from info group by c_name order by sum(liftweight) desc```
> 3. 리턴값을 위해 쿼리의 결과를 ResultSet타입으로 반환해주는 ```ResultSet 타입의 rs```, ResultSet타입으로 반환된 값을 저장하기 위한 ```Info_DTO 타입의 returnDTO```, 마지막으로 리턴값인 ```Info_DTO 타입의 ArrayList tlist```를 선언합니다.
> 4. 예외를 처리하기 위한 try carch문 으로 들어서고, 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
> 5. DB에 연결이 된다면 ```Statement 타입의 p```가 ```Connection 타입의 conn```의 ```createStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```select c_name, sum(liftweight) from info group by c_name order by sum(liftweight) desc``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
> 6. Info_DTO 객채를 생성하고 쿼리결과가 다중 튜플이기 때문에 순회를 위해 while문을 통해서 rs의 내용을 메서드를 호출한 위치로 전달하기 위한 Info_DTO 객채인 returnDTO에 저장합니다.
> 7. 결과가 모두 저장되면 while문이 종료되고, return ulist 로 결과값이 넘어 갈 수 있도록 합니다.
> 8. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 User_GUI 클래스로 복귀합니다.



```java
	// 체급별 랭킹
		public ArrayList<Info_DTO> wRank(String wgt) {
			String sql = "select c_name, sum(liftweight) from (select customer.name as c_name, info.liftweight as liftweight from info join customer on c_name=name and customer.weight=?) group by c_name order by sum(liftweight) desc";
			ResultSet rs = null;
			Info_DTO returnDTO = null;
			ArrayList<Info_DTO> tlist = new ArrayList<>();
			try {
				getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, wgt);
				rs = psmt.executeQuery();
				while (rs.next()) {
					returnDTO = new Info_DTO();
					returnDTO.setC_name(rs.getString("c_name"));
					returnDTO.setLiftWeight(rs.getInt("sum(liftweight)"));
					tlist.add(returnDTO);
				}
				return tlist;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
```

**wRank(String wgt)**

과정

1. 누적기록 확인 기능을 수행할 ```wRank(String wgt)```로 User_GUI 클래스에서 사용자가 필요한 정보를 리턴값을 통해 전달해주는 메서드입니다.
2. 해당 쿼리를 수행하게 될 sql문을 미리 작성해 두었습니다.  ```select c_name, sum(liftweight) from (select customer.name as c_name, info.liftweight as liftweight from info join customer on c_name=name and customer.weight=?) group by c_name order by sum(liftweight) desc```
3. 리턴값을 위해 쿼리의 결과를 ResultSet타입으로 반환해주는 ```ResultSet 타입의 rs```, ResultSet타입으로 반환된 값을 저장하기 위한 ```Info_DTO 타입의 returnDTO```, 마지막으로 리턴값인 ```Info_DTO 타입의 ArrayList tlist```를 선언합니다.
4. 예외를 처리하기 위한 try carch문 으로 들어서고, 쿼리를 데이터 베이스에 전달하기 위해 DB와 연결하는 getConnection() 메서드가 실행됩니다.
5. DB에 연결이 된다면 ```Statement 타입의 p```가 ```Connection 타입의 conn```의 ```createStatement(sql)``` 메서드를 수행하기 위해 생성되는데 이는 SQL문 ```select c_name, sum(liftweight) from (select customer.name as c_name, info.liftweight as liftweight from info join customer on c_name=name and customer.weight=?) group by c_name order by sum(liftweight) desc``` 을 인자로 받아 해당 SQL구문을 실행시키기 위함입니다.
6. 미완성된 쿼리문을 바인딩하기 위해 변수 ```String wgt```을 통해 받아온 해당 체급명으로 쿼리문을 완성시킵니다.
7. ?에는 weight값이 입력되었고, 이는 Users_GUI 클래스의 ```@Override actionPerformed(ActionEvent e)```에서 받아온 값입니다.
8. Info_DTO 객채를 생성하고 쿼리결과가 다중 튜플이기 때문에 순회를 위해 while문을 통해서 rs의 내용을 메서드를 호출한 위치로 전달하기 위한 User_DTO 객채인 returnDTO에 저장합니다.
9. 결과가 모두 저장되면 while문이 종료되고, return ulist 로 결과값이 넘어 갈 수 있도록 합니다.
10. 예외가 없으면 ```finally```문으로 진입해 모든 기능을 수행한 ```Connection conn```은 네트워크 및 메모리같은 불필요한 자원을 낭비하지 않기 위해 ```close()``` 메서드를 통해 Connection을 닫아주고 메서드 종료 후 User_GUI 클래스로 복귀합니다.



## GUI 각 actionListener / actionPerformed 메서드

#### Manager_GUI

**addListener()**

```java
	private void addListener() {
		register_Btn.addActionListener(this);
		login_Btn.addActionListener(this);
		admin_Btn.addActionListener(this);
		logging_Btn.addActionListener(this);
		logging_Btn2.addActionListener(this);
		submit_Btn.addActionListener(this);
	}
```

**actionPerformed()**

```java
	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원가입 버튼 (메뉴)
		if (e.getSource().equals(register_Btn)) {
			this.remove(admin_P);
			this.remove(login_P);
			this.add(register_P);
			this.setVisible(false);
			this.setVisible(true);
			// 로그인 버튼 (메뉴)
		} else if (e.getSource().equals(login_Btn)) {
			this.remove(register_P);
			this.remove(admin_P);
			this.add(login_P);
			this.setVisible(false);
			this.setVisible(true);
			// 관리자 버튼 (메뉴)
		} else if (e.getSource().equals(admin_Btn)) {
			this.remove(login_P);
			this.remove(register_P);
			this.add(admin_P);
			this.setVisible(false);
			this.setVisible(true);
			// 관리자 로그인 버튼
		} else if (e.getSource().equals(logging_Btn2)) {
			if (A_id_tf.getText().equals(A_DTO.getAdm_id()) && A_pw_tf.getText().equals(A_DTO.getAdm_pw())) {
				JOptionPane.showMessageDialog(null, "관리자 모드로 진입합니다.");
				this.setVisible(false);
				new Admin_GUI();
			} else {
				JOptionPane.showMessageDialog(null, "다시 입력하십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}
			// 유저 로그인 버튼
		} else if (e.getSource().equals(logging_Btn)) { // 예외(NullPointerException 잡기
			ArrayList<User_DTO> uList = M_DAO.login();
			boolean chk = true;
			for (int i = 0; i < uList.size(); i++) {
				if (L_id_tf.getText().equals(uList.get(i).getId())
						&& Integer.parseInt(L_pw_tf.getText()) == uList.get(i).getWeight()) {
					chk = false;
					JOptionPane.showMessageDialog(null, "유저 모드로 진입합니다.");
					this.setVisible(false);
					U_DTO.setName(uList.get(i).getName());
					new Users_GUI(U_DTO);
				}
			}
			if (chk) {
				JOptionPane.showMessageDialog(null, "다시 입력하십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}
			// 회원가입 submit 버튼
		} else if (e.getSource().equals(submit_Btn)) {
			if (R_id_tf.getText().equals("") || R_name_tf.getText().equals("") || R_ht_tf.getText().equals("")
					|| R_wt_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 항목을 기입해 주십시오", "경고", JOptionPane.WARNING_MESSAGE);
			} else {
				User_DTO newU = new User_DTO();
				newU.setId(R_id_tf.getText());
				newU.setName(R_name_tf.getText());
				newU.setHeight(Integer.parseInt(R_ht_tf.getText()));
				newU.setWeight(Integer.parseInt(R_wt_tf.getText()));
				M_DAO.usrAdd(newU);
				JOptionPane.showMessageDialog(null, (newU.getName() + "님! 가입을 축하드립니다!"));
			}
		}
	}
```

> * 각 메뉴(회원가입, 로그인, 관리자 로그인)를 누를 때 Panel이 바뀌는 기능
> * 해당 Panel의 각 버튼을 누를 때 Manager_DAO의 ```usrAdd(), login()``` 실행하는 기능
> * GUI의 Textfield의 값과 Manager_DTO의 getter와 값을 비교하여 처리합니다.
> * 📌 Admin 로그인은 Admin_DTO에서 따로 정의 되어 있기 때문에 DAO에서 따로 정의를 해두지 않았습니다. 그 외 DTO에서 getter로 값을 비교하는 것은 동일합니다.



#### Admin_GUI

**addListener(), ItemListener()**

```java
	private void addListener() {
		mod_Btn.addActionListener(this);
		del_Btn.addActionListener(this);
		view_Btn.addActionListener(this);
		M_Mod_Btn.addActionListener(this);
		D_Del_Btn.addActionListener(this);
		V_UserList.addItemListener(this);
		logout_Btn.addActionListener(this);
	}
```

**actionPerfomed()**

```java
	@Override
	public void actionPerformed(ActionEvent e) {
		// 수정 버튼 (메뉴)
		if (e.getSource().equals(mod_Btn)) {
			this.remove(view_P);
			this.remove(del_P);
			this.add(mod_P);
			this.setVisible(false);
			this.setVisible(true);
			// 삭제 버튼 (메뉴)
		} else if (e.getSource().equals(del_Btn)) {
			this.remove(mod_P);
			this.remove(view_P);
			this.add(del_P);
			this.setVisible(false);
			this.setVisible(true);
			// 전체보기 버튼 (메뉴)
		} else if (e.getSource().equals(view_Btn)) {
			this.remove(mod_P);
			this.remove(del_P);
			this.add(view_P);
			this.setVisible(false);
			this.setVisible(true);
			// 수정 기능
		} else if (e.getSource().equals(M_Mod_Btn)) {
			if (M_Name_tf.getText().equals("") || M_Wt_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 항목을 기입해 주십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}else {
				User_DTO modU = new User_DTO();
				modU.setName(M_Name_tf.getText());
				modU.setWeight(Integer.parseInt(M_Wt_tf.getText()));
				A_DAO.usrEdit(modU);
				JOptionPane.showMessageDialog(null, "수정 완료");
				loadData();
				M_Wt_tf.setText("");
				M_Name_tf.setText("");
			}
			// 삭제 기능
		} else if (e.getSource().equals(D_Del_Btn)) {
			if (D_Name_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "삭제할 회원을 입력해 주십시오", "경고", JOptionPane.WARNING_MESSAGE);
			}else {
				User_DTO delU = new User_DTO();
				delU.setName(D_Name_tf.getText());
				A_DAO.usrDel(delU);
				JOptionPane.showMessageDialog(null, "삭제 완료");
				loadData();
			}
			// 로그아웃 기능
		} else if (e.getSource().equals(logout_Btn)) {
			int result = JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == 0) {
				new Manager_GUI();
				this.setVisible(false);
			}
		}
	}
```

**itemStateChanged()**

```java
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(V_UserList)) {
			int selIDX = V_UserList.getSelectedIndex();
			User_DTO u = uList.get(selIDX);
			detailView(u);
		}
	}

	// TextArea 전체보기 내용
	private void detailView(User_DTO u) {
		V_Detail.setText("");
		V_Detail.append("ID : " + u.getId() + "\n");
		V_Detail.append("NAME : " + u.getName() + "님\n");
		V_Detail.append("HEIGHT : " + Integer.toString(u.getHeight()) + "cm\n");
		V_Detail.append("WEIGHT : " + Integer.toString(u.getWeight()) + "kg\n");
	}
}
```

> * 각 메뉴(회원수정, 회원삭제, 회원전체보기, 로그아웃)를 누를 때 Panel이 바뀌는 기능
> * 해당 Panel의 각 버튼을 누를 때 Admin_DAO의 ```usrEdit(), usrDel(), usrAll()``` 실행하는 기능
> * GUI의 Textfield의 값과 Admin_DAO의 각 메서드에 정의된 SQL문을 통해 값을 불러와 이를 비교하여 처리합니다.
> * Admin 로그아웃은 GUI에서 ```this.setVisable(false)``` 를 통해 메인 Panel로 돌아가는 방법으로 구현하였습니다.



#### User_GUI

**addListener()**

```java
	private void addListener() {
		record_Btn.addActionListener(this);
		insert_Btn.addActionListener(this);
		rank_Btn.addActionListener(this);
		I_Submit_Btn.addActionListener(this);
		logout_Btn.addActionListener(this);
	}
```

**actionPerfomed()**

```
	@Override
	public void actionPerformed(ActionEvent e) {
		// 기록 조회 버튼 (메뉴)
		if (e.getSource().equals(record_Btn)) {
			this.remove(insert_P);
			this.remove(rank_P);
			this.add("Center", record_P);
			this.setVisible(false);
			this.setVisible(true);
			// 기록 추가 버튼 (메뉴)
		} else if (e.getSource().equals(insert_Btn)) {
			this.remove(record_P);
			this.remove(rank_P);
			this.add("Center", insert_P);
			this.setVisible(false);
			this.setVisible(true);
			I_Name_tf.setText("");
			I_Wt_tf.setText("");
			// 랭킹보기 버튼 (메뉴)
		} else if (e.getSource().equals(rank_Btn)) {
			this.remove(insert_P);
			this.remove(record_P);
			this.add("Center", rank_P);
			this.setVisible(false);
			this.setVisible(true);
			// 로그아웃 버튼
		} else if (e.getSource().equals(logout_Btn)) {
			int result = JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == 0) {
				new Manager_GUI();
				this.setVisible(false);
			}
			// 기록추가 버튼
		} else if (e.getSource().equals(I_Submit_Btn)) {
			if (I_Name_tf.getText().equals("") || I_Wt_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 항목을 기입해 주십시오", "경고", JOptionPane.WARNING_MESSAGE);
			} else {
				Info_DTO U_Rec = new Info_DTO();
				U_Rec.setC_name(this.myU.getName());
				U_Rec.setT_name(I_Name_tf.getText());
				U_Rec.setLiftWeight(Integer.parseInt(I_Wt_tf.getText()));
				U_DAO.infoAdd(U_Rec);
				JOptionPane.showMessageDialog(null, "기록 완료");
				I_Name_tf.setText("");
				I_Wt_tf.setText("");
				loadRecord();
				loadRank1();
			}
		} else if (e.getSource().equals(Rnk_Btn1)) {
			loadRank1();
		} else if (e.getSource().equals(Rnk_Btn2)) {
			String wgt = "라이트";
			loadRank2(wgt);
		} else if (e.getSource().equals(Rnk_Btn3)) {
			String wgt = "미들";
			loadRank2(wgt);
		} else if (e.getSource().equals(Rnk_Btn4)) {
			String wgt = "헤비";
			loadRank2(wgt);
		}
	}
}
```

> * 각 메뉴(누적기록조회, 기록추가, 랭킹보기, 로그아웃)를 누를 때 Panel이 바뀌는 기능
> * 해당 Panel의 각 버튼을 누를 때 User_DAO의 ```infoOne(), infoAdd(), rank()``` 실행하는 기능
> * GUI의 Textfield의 값과 User_DAO의 각 메서드에 정의된 SQL문을 통해 값을 불러와 이를 비교하여 처리합니다.
> * User 로그아웃 또한 GUI에서 ```this.setVisable(false)``` 를 통해 메인 Panel로 돌아가는 방법으로 구현하였습니다.



## 실행화면

#### Manager_GUI

<img src="https://user-images.githubusercontent.com/84169773/141746000-028fdfed-8f6e-4871-a2fe-e62eb567bd16.png" alt="image-20211115162350181" style="zoom: 50%;" /><img src="https://user-images.githubusercontent.com/84169773/141746004-6cff9a87-748f-4d24-8aa7-8cbc7f02156a.png" alt="image-20211115162246381" style="zoom:50%;" /><img src="https://user-images.githubusercontent.com/84169773/141746007-8dae7cf0-eded-4c09-854d-d3e5f3c6bf21.png" alt="image-20211115162310189" style="zoom:50%;" />

> 회원가입, 유저로그인, 관리자로그인 Panel.

#### 각 Panel 예시

<img src="https://user-images.githubusercontent.com/84169773/141746201-8d89a9dd-b033-449a-864a-7a87c219d2df.png" alt="image-20211115162635226" style="zoom:50%;" /><img src="https://user-images.githubusercontent.com/84169773/141746206-3bd3baf2-6212-4547-89f9-8da7ba440de7.png" alt="image-20211115162751780" style="zoom: 53%;" /><img src="https://user-images.githubusercontent.com/84169773/141746210-c9cf0f90-0e34-4cd4-86ec-78eaac644845.png" alt="image-20211115162610072" style="zoom:50%;" />

> 성공적으로 회원가입 및 로그인이 실행되면 ```JOptionPane().showMessageDialog() ```를 통해 처리됨을 알림.

#### Admin_GUI

<img src="https://user-images.githubusercontent.com/84169773/141746433-400ac34b-e8e4-4d97-8a75-b5ebcb2638a1.png" alt="image-20211115163142764" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141746434-b4709e4b-cf90-48b0-ac12-95dc5da90256.png" alt="image-20211115163154944" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141746430-4893dbf6-6b69-4033-8733-015633063690.png" alt="image-20211115163211671" style="zoom:33%;" />

> 수정하기, 삭제하기, 전체보기 Panel

#### 각 Panel 예시

<img src="https://user-images.githubusercontent.com/84169773/141746656-a1b8aa7d-0595-45b3-b736-d32312752f58.png" alt="image-20211115163004776" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141746657-32a2bc47-20b6-42f4-b80c-c63356030077.png" alt="image-20211115163024250" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141746658-4e00d47b-8d01-418b-bc26-772d338d0392.png" alt="image-20211115162909216" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141746659-e7f4085a-e0ae-4389-8161-582cdacf4713.png" alt="image-20211115162931293" style="zoom:33%;" />

> 전체보기를 제외하고 수정, 삭제 또는 로그아웃 버튼의 처리가 성공적으로 이루어질 때 ```JOptionPane().showMessageDialog() || JOptionPane().WARNING_MESSAGE ```를 통해 처리됨을 알림. (로그아웃의 경우예/아니오 선택을 통해 취소가 가능함.) 



#### User_GUI

<img src="https://user-images.githubusercontent.com/84169773/141747366-93a3b92d-62f8-4a58-8893-cd4bbd2e51c5.png" alt="image-20211115163403470" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141933824-aad22860-decd-4155-b250-3e020bb72178.png" alt="image" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141747372-2b78a057-9f89-4f87-aa53-c4e538e31111.png" alt="image-20211115163351357" style="zoom:33%;" />

> 개인기록 조회, 기록추가, 랭킹보기 기능

#### 각 Panel 예시

<img src="https://user-images.githubusercontent.com/84169773/141747510-e990dc02-fba9-4c01-9f00-2bad644703c5.png" alt="image-20211115163430918" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141747511-bac9dea1-b493-498b-8fc1-646a6cf2d614.png" alt="image-20211115163446012" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141933824-aad22860-decd-4155-b250-3e020bb72178.png" alt="image" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141747514-a3bdc449-201a-48eb-87f8-e4e4522ffabf.png" alt="image-20211115163520571" style="zoom:33%;" />

> 기록 또는 로그아웃 버튼의 처리가 성공적으로 이루어질 때 ```JOptionPane().showMessageDialog() || JOptionPane().WARNING_MESSAGE ```를 통해 처리됨을 알림. (로그아웃의 경우예/아니오 선택을 통해 취소가 가능함.) 

#### 개별 랭킹 Panel

<img src="https://user-images.githubusercontent.com/84169773/141933975-b12a770c-d170-47c8-8e3b-6e5205a8c566.png" alt="image" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141934066-1e16525b-b0f9-4b17-8066-ab275dfedf75.png" alt="image" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141934121-a447b08a-b85b-4872-8f61-bd0480e78c60.png" alt="image" style="zoom:33%;" /><img src="https://user-images.githubusercontent.com/84169773/141934160-7320a3e1-a27d-4201-8c26-2bafc5aa526d.png" alt="image" style="zoom:33%;" />

> 전체보기 - 라이트급 - 미들급 - 헤비급

## 🤼‍♂️ 후기

### 💬 Seung's 숙제

* **👀 하단에 뜨는 null을 고쳐야 합니다. - 2021/11/16 해결 완료! **
* **git & github을 조금 더 연마해야 합니다.**
* **추가하고 싶은 기능이 많았지만 추가하지 못한 기능들이 많습니다. (ex 사람들이 좋아하는 운동부위 Ranking, 기록 추가시 종목 입력이 아닌 리스트식으로 처리 등)**
* **사소한 부분이라도 추가된 것이 있다면 지체없이 말해주어야 후에 쓸데없는 Conflict가 안난다는 것을 가슴에 새겨야 합니다.**
* **이번에 새로운 IDE IntelliJ 라이센스를 받는 기회가 생겨 새로운 IDE를 익혀야합니다.**

### 💬 Baek's 숙제

* **이번 프로젝트를 진행하면서 팀으로 진행을 하였고, 협업 간에 Git Bash 프로그램을 사용하여 새로운 것을 배우는 것에 재미를 느꼈습니다**
* **자바 코드를 DB와 연동하면서 지난 시간동안 배웠던 것들이 정리가 되어 너무 좋았습니다.** 
* **Git Bash를 사용하면서 서로 간 소통의 중요성을 깊이 깨닫게되었습니다.**
* **기능이 많이 적어 계속 보완해서 좋은 프로그램으로 만들겠습니다.**
* **아직 Git Bash와 GUI에 익숙하지 않아 공부하는 시간이 더 많았지만 매우 즐거운 시간이었습니다.**

