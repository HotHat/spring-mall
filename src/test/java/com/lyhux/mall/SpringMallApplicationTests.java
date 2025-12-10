package com.lyhux.mall;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyhux.mall.dto.LoginDTO;
import com.lyhux.mall.mapper.UserMapper;
import com.lyhux.mall.model.UserVO;
import com.lyhux.mall.service.UserService;
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

	@Autowired
	private UserService userService;

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
			UserVO user = session.selectOne(
					"com.lyhux.mall.mapper.UserMapper.selectUser", 1);

			System.out.println("id:" + user.getId());
			System.out.println("username:" + user.getUsername());
		}

		System.out.println("Test ending...");

	}


	@Test
	void testSelectOrders() {
		List<UserVO> userVOS = userMapper.selectUsers();

		for (UserVO userVO : userVOS) {
			System.out.printf("id:%d, username:%s\n", userVO.getId(), userVO.getUsername());
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

	@Test
	void testPlusService() {
		User user = userService.getById(1) ;
		System.out.printf("admin:%s=%s\n", user.getId(), user.getUsername());
	}

	@Test
	void testPageQuery() {
		PageHelper.startPage(2, 1);
		List<UserVO> userVOS = userMapper.selectUsers();
		long total = ((Page<UserVO>) userVOS).getTotal();
		System.out.printf("size=%d\n", userVOS.size());
		System.out.printf("total=%d\n", total);

		for (UserVO userVO : userVOS) {
			System.out.printf("id:%d, username:%s\n", userVO.getId(), userVO.getUsername());
		}
	}
}
