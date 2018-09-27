package ru.tretyakov.models;

import org.springframework.stereotype.Component;

/**
 * Entity describe user data to persist into container.
 *
 * @author Rooter
 * @since 26.09.2018
 */

@Component
public class User {
    /** Name of user. */
    private String username;
    /** color of user. */
    private String color;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor to init.
     * @param color of user
     * @param username of user
     */
    public User(final String color, final String username) {
        this.color = color;
        this.username = username;
    }

    /**
     * Getter of user color.
     * @return color string
     */
    public String getColor() {
        return color;
    }

    /**
     * Setter of user color.
     * @param color of user
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * Getter name of user.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter name of user.
     * @param username of user
     */
    public void setUsername(final String username) {
        this.username = username;
    }
}
