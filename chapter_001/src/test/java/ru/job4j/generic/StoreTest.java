package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoreTest {

    UserStore userStore;
    RoleStore roleStore;

    @Before
    public void setUp() {
        userStore = new UserStore(10);
        roleStore = new RoleStore(10);
        for (int i = 0; i < 10; i++) {
            userStore.add(new User("User id " + i));
            roleStore.add(new Role("Role id " + i));
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void readAllUsersAndRolesAndAddExtraUser() {
        for (int i = 0; i < 10; i++) {
            assertThat(userStore.findById("User id " + i), is(new User("User id " + i)));
            assertThat(roleStore.findById("Role id " + i), is(new Role("Role id " + i)));
        }
        userStore.add(new User("Extra user"));
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteUserAndRoleAndAddExtraUserAndRoleAndDeleteAbsentedUserAndFindAbsentedUser() {
        assertThat(userStore.delete("User id 5"), is(true));
        assertThat(userStore.delete("User id 5"), is(false));
        assertThat(roleStore.delete("Role id 5"), is(true));
        assertThat(roleStore.delete("Role id 5"), is(false));
        userStore.add(new User("Extra user"));
        roleStore.add(new Role("Extra role"));
        userStore.findById("User id 5");
    }

    @Test(expected = NoSuchElementException.class)
    public void updateUserAndRoleAndFindNewUserAndRoleAndUpdateAbsentedUserAndRoleAndFindPreviosUser() {
        assertThat(userStore.replace("User id 5", new User("New User")), is(true));
        assertThat(userStore.replace("User id 5", new User("NewNew User")), is(false));
        assertThat(userStore.findById("New User"), is(new User("New User")));
        assertThat(roleStore.replace("Role id 5", new Role("New Role")), is(true));
        assertThat(roleStore.replace("Role id 5", new Role("NewNew Role")), is(false));
        assertThat(roleStore.findById("New Role"), is(new Role("New Role")));
        userStore.findById("User id 5");
    }



}
