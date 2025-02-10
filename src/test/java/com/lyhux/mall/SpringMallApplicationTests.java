package com.lyhux.mall;

import com.lyhux.mall.dto.LoginDTO;
import com.lyhux.mall.mapper.UserMapper;
import com.lyhux.mall.model.UserVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class SpringMallApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void mybatisTest() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
				new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println("Test starting...");

		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne(
					"com.lyhux.mall.UserMapper.selectUser", 1);

			System.out.println("id:" + user.getId());
			System.out.println("username:" + user.name());
		}

		System.out.println("Test ending...");

	}


	@Test
	void testSelectOrders() {
		List<UserVO> userVOS = userMapper.selectUsers();

		for (UserVO userVO : userVOS) {
			System.out.printf("id:%d, username:%s\n", userVO.getId(), userVO.getName());
		}
	}

	@Test
	void testDto() {
		var dto = new LoginDTO();
//		dto.setAge(123);
//		dto.setName("nnn");
//		dto.setPassword("123456");
//		System.out.printf("dto:%s=%s\n", dto.getUsername(), dto.getPassword());
		System.out.printf("dto:%s\n", dto);
	}
}
