package ru.tretyakov.service;

import org.springframework.stereotype.Component;
import ru.tretyakov.models.Message;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class provides messages service feature.
 *
 * author Rooter
 * since 26.09.18
 **/

@Component
public class MessagesRepository implements MessageInterface {
    /** Messages container. */
    private List<Message> messages = new CopyOnWriteArrayList<Message>();

    /**
     * This method write new message into container.
     * @param message new
     */
    public void writeMessage(final Message message) {
        synchronized (this) {
            message.setDate(new Timestamp(new Date().getTime()));
            messages.add(message);
        }
    }

    /**
     * This method return list of messages by range.
     * @param index first
     * @return List
     */
    public List getMessages(final int index) {
        List<Message> result;
        synchronized (this) {
            result = this.messages.subList(index, messages.size());

        }
        return result;
    }
}
