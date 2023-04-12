package cn.nean.boss;

import cn.nean.boss.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BossApplicationTests {

	@Autowired
	EmailUtil emailUtil;

	@Test
	void contextLoads() {
		emailUtil.sendEmail("2522754658@qq.com","test","test");
	}

}
