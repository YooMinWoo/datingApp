package com.mintae.dating.controller;

import com.mintae.dating.dto.ApiResponse;
import com.mintae.dating.dto.NotificationDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.security.user.CustomUserDetails;
import com.mintae.dating.service.NotificationService;
import com.mintae.dating.vo.Notification;
import com.mintae.dating.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> like(@AuthenticationPrincipal CustomUserDetails customUserDetails, NotificationDTO notificationDTO){
        User user = customUserDetails.getUser();
        notificationDTO.setSender_id(user.getId());
        notificationService.insertNotification(notificationDTO);
        return ResponseEntity.ok(ApiResponse.success("성공", null));
    }

    // 알림 전체 조회
    @GetMapping("/notification")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getNotification(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        List<Notification> notification = notificationService.getNotification(user);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "성공", notification);
        return ResponseEntity.ok(ApiResponse.success("성공", notification));
    }

}
