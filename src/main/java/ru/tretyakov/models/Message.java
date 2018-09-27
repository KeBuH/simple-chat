package ru.tretyakov.models;

import java.sql.Timestamp;

/**
 * Entity describe message data to persist into container.
 *
 * @author Rooter
 * @since 26.09.2018
 */

public class Message {
    /** message text. */
    private String message;
    /** user. */
    private User user;
    /** message date. */
    private Timestamp date;

    /**
     * Default constructor.
     */
    public Message() {
    }

    /**
     * Constructor to init.
     * @param message of user
     * @param user entity
     */
    public Message(final String message, final User user) {
        this.message = message;
        this.user = user;
    }

    /**
     * Getter of user message.
     * @return message text
     */
    public String getMessage() {
        return message;
    }

    /**
     *  Setter of user message.
     * @param message new text
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Getter of message user.
     * @return user entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter of message user.
     * @param user of message
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Getter of message date.
     * @return date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Setter of message date.
     * @param date current
     */
    public void setDate(final Timestamp date) {
        this.date = date;
    }
}
