package com.userdemo.test.user;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.userdemo.entity.User;
import com.userdemo.service.UserService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "classpath:dispatcher-config-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	private static String name = "Min-Tse Yu";

	private static String email = "nidotim@gmail.com";

	private static String updatedName = "Min-Tse Yu (updated)";

	private static String updatedEmail = "nidotim@gmail.com.updated";

	private static Integer id = 1;

	@Test
	public void stage1_create() throws Exception {
		User user = null;
		try {
			user = userService.createUser(name, null);
		} catch (Exception e) {
			// ignored
		}
		try {
			user = userService.createUser(null, email);
		} catch (Exception e) {
			// ignored
		}
		user = userService.createUser(name, email);
		checkUser(user);
	}

	@Test
	public void stage2_get() throws Exception {
		User user = userService.getUser(id);
		checkUser(user);
	}

	@Test
	public void stage3_listAll() throws Exception {
		List<User> users = userService.listUsers();
		
		Assert.assertNotNull(users);
		Assert.assertEquals(1, users.size());
		Assert.assertEquals(name, users.get(0).getName());
		Assert.assertEquals(email, users.get(0).getEmail());
		Assert.assertEquals(id, users.get(0).getId());
	}

	@Test
	public void stage4_update() throws Exception {
		User user = userService.getUser(id);
		checkUser(user);

		user.setEmail(null);
		user.setName(null);
		userService.updateUser(id, user);
		user = userService.getUser(id);
		checkUser(user);

		user.setEmail(updatedEmail);
		user.setName(updatedName);
		userService.updateUser(id, user);
		user = userService.getUser(id);

		checkUpdatedUser(user);
	}

	@Test
	public void stage5_delete() throws Exception {
		User user = userService.getUser(id);
		checkUpdatedUser(user);

		userService.deleteUser(id);

		user = userService.getUser(id);
		checkUserDeleted(user);
	}

	private void checkUser(User user) {
		Assert.assertNotNull(user);
		Assert.assertEquals(name, user.getName());
		Assert.assertEquals(email, user.getEmail());
		Assert.assertEquals(id, user.getId());
	}

	private void checkUpdatedUser(User user) {
		Assert.assertNotNull(user);
		Assert.assertEquals(updatedName, user.getName());
		Assert.assertEquals(updatedEmail, user.getEmail());
		Assert.assertEquals(id, user.getId());
	}

	private void checkUserDeleted(User user) {
		Assert.assertNull(user);
	}

}