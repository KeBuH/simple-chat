package ru.tretyakov.service;

import ru.tretyakov.models.Message;

import java.util.List;

/**
 * To implement message service.
 *
 * @author Rooter
 * @since 26.09.2018
 */


public interface MessageInterface {
    /** To implement write message method.
     * @param message new message
     */
    void writeMessage(Message message);
    /** To implement get messages method.
     * @param index start index
     * @return List
     */
    List getMessages(int index);
}
