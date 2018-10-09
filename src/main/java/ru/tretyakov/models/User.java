package ru.tretyakov.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

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

    /**
     * Override equals by username.
     * @param o username
     * @return result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    /**
     * Override hashcode by username.
     * @return result
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
