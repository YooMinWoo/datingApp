package com.mintae.dating.mapper;

import com.mintae.dating.dto.NotificationDTO;
import com.mintae.dating.vo.Notification;
import com.mintae.dating.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    void insertNotification(NotificationDTO notificationDTO);

    List<Notification> getNotification(User user);

    void readNotification(User user);
}
