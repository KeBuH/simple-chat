package ru.tretyakov.service;

import org.springframework.stereotype.Component;
import ru.tretyakov.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private List<User> users = new CopyOnWriteArrayList<User>();

    /**
     * This method add new User entity into container.
     * @param user new entity
     */
    public void add(final User user) {
        synchronized (this) {
            users.add(user);
        }
    }

    /**
     * This method verify user exist.
     * @param user to verify
     * @return result
     */
    public boolean isUserExist(final User user) {
        boolean result = false;
        synchronized (this) {
            for (User current : users) {
                if (current.getUsername().equals(user.getUsername())) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * This method return list of range.
     * @param index start index
     * @return List
     */
    @Override
    public List<User> getUsers(final Integer index) {
        List<User> result;
        synchronized (this) {
            result = this.users.subList(index, users.size());
        }
        return result;
    }

    /**
     * This method search user by name.
     * @param username to search
     * @return User entity
     */
    @Override
    public User getUserByName(final String username) {
        User result = null;
        synchronized (this) {
            for (int index = 0; index < users.size(); index++) {
                if (this.users.get(index).getUsername()
                        .equals(username)) {
                    result = this.users.get(index);
                }
            }
        }
        return result;
    }

    /**
     * This method remove user by name.
     * @param username to search
     * @return result
     */
    @Override
    public boolean removeByUsername(final String username) {
        boolean result = false;
        synchronized (this) {
            for (int index = 0; index < users.size(); index++) {
                if (this.users.get(index).getUsername()
                        .equals(username)) {
                    users.remove(index);
                    result = true;
                }
            }
        }
        return result;
    }

}
