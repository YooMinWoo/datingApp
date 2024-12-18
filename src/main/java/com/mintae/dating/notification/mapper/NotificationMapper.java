package com.mintae.dating.notification.mapper;

import com.mintae.dating.notification.dto.NotificationDTO;
import com.mintae.dating.notification.vo.Notification;
import com.mintae.dating.user.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    void insertNotification(NotificationDTO notificationDTO);

    List<Notification> getNotification(User user);

    void readNotification(User user);
}
