package com.mintae.dating.service;

import com.mintae.dating.dto.NotificationDTO;
import com.mintae.dating.mapper.NotificationMapper;
import com.mintae.dating.vo.Notification;
import com.mintae.dating.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationMapper notificationMapper;

    public void insertNotification(NotificationDTO notificationDTO){
        notificationMapper.insertNotification(notificationDTO);
    }

    @Transactional
    public List<Notification> getNotification(User user) {

        List<Notification> notification = notificationMapper.getNotification(user);
        notificationMapper.readNotification(user);
        return notification;
    }
}
