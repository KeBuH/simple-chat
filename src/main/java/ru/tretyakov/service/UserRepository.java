package ru.tretyakov.service;

import org.springframework.stereotype.Component;
import ru.tretyakov.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * TODO comment.
 *
 * @author Rooter
 * @since 26.09.2018
 */

@Component
public class UserRepository implements UserInterface {
    /**
     * Container of users.
     */
    private List<User> users =
            Collections.synchronizedList(new ArrayList<User>());

    /**
     * This method add new User entity into container.
     *
     * @param user new entity
     */
    public void add(final User user) {
        users.add(user);
    }

    /**
     * This method verify user exist.
     *
     * @param user to verify
     * @return result
     */
    public boolean isUserExist(final User user) {
        boolean result = false;
        for (User current : users) {
            if (current.getUsername().equals(user.getUsername())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * This method return list of range.
     *
     * @param index start index
     * @return List
     */
    @Override
    public List<User> getUsers(final Integer index) {
        List<User> result;
        result = this.users.subList(index, users.size());
        return result;
    }

    /**
     * This method search user by name.
     *
     * @param username to search
     * @return User entity
     */
    @Override
    public User getUserByName(final String username) {
        User result = null;
        for (User user : users) {
            if (user.getUsername()
                    .equals(username)) {
                result = user;
            }
        }
        return result;
    }

    /**
     * This method remove user by name.
     *
     * @param username to search
     * @return result
     */
    @Override
    public boolean removeByUsername(final String username) {
        boolean result = false;
        for (User user : users) {
            if (user.getUsername()
                    .equals(username)) {
                users.remove(user);
                result = true;
            }
        }
        return result;
    }
}
