package ru.tretyakov.service;

import ru.tretyakov.models.User;

import java.util.List;

/**
 * To implement users service.
 *
 * @author Rooter
 * @since 27.09.2018
 */


public interface UserInterface {
    /** To implement write message method.
     * @param user to add
     */
    void add(User user);

    /**
     * To implement verify user method.
     * @param user to search
     * @return List
     */
    boolean isUserExist(User user);

    /**
     * To implement method get users range .
     * @param index start index
     * @return List
     */
    List<User> getUsers(Integer index);

    /**
     * To implement method of searching user by name.
     * @param username to search
     * @return User entity
     */
    User getUserByName(String username);

    /**
     * To impelent remove by name method.
     * @param username to search
     * @return result
     */
    boolean removeByUsername(String username);

}
