package com.stouduo.qcb;

import com.stouduo.qcb.domain.User;
import com.stouduo.qcb.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QcbApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testInsUser() {
        User user = new User();
        user.setUsername("15629807887");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        userRepository.save(user);
    }
}
