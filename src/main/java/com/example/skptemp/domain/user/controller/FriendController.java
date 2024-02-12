package com.example.skptemp.domain.user.controller;

import com.example.skptemp.domain.user.dto.FriendCreateRequest;
import com.example.skptemp.domain.user.dto.FriendDeleteRequest;
import com.example.skptemp.domain.user.dto.FriendResponse;
import com.example.skptemp.domain.user.dto.FriendResult;
import com.example.skptemp.domain.user.entity.User;
import com.example.skptemp.domain.user.service.FriendRelationshipService;
import com.example.skptemp.domain.user.service.UserService;
import com.example.skptemp.global.common.ApiResponse;
import com.example.skptemp.global.common.SecurityUtil;
import com.example.skptemp.global.error.GlobalErrorCode;
import com.example.skptemp.global.error.GlobalException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
@RestController
public class FriendController {
    private final FriendRelationshipService friendRelationshipService;
    private final UserService userService;
    private final SecurityUtil securityUtil;

    @Operation(summary = "getFriendList", description = "사용자 친구 리스트 조회 API")
    @GetMapping("/{user-id}")
    ApiResponse<FriendResponse> getFriendList(@Valid Long userId){
        List<FriendResult> friendRelationshipList = friendRelationshipService.findFriendRelationshipList(userId);
        return new ApiResponse<>(new FriendResponse(friendRelationshipList));
    }

    @Operation(summary = "createFriend", description = "친구 추가 API")
    @PostMapping
    ApiResponse<Void> createFriend(@RequestBody FriendCreateRequest request){
        Long userId = securityUtil.getUserIdFromContext();
        User user = userService.findById(userId);

        // 자기 자신과 친구 관계 생성 불가.
        if(user.getCode().equals(request.getUserCode())){
            throw new GlobalException(GlobalErrorCode.FRIEND_RELATIONSHIP_VALID_EXCEPTION);
        }
        User friendUser = userService.findByCode(request.getUserCode());

        friendRelationshipService.enrollFriendRelationship(userId, friendUser.getId());
        return ApiResponse.ok();
    }

    @Operation(summary = "deleteFriend", description = "친구 삭제 API")
    @DeleteMapping("/{friend-id}")
    ApiResponse<Void> deleteFriend(@RequestBody FriendDeleteRequest request){
        Long userId = securityUtil.getUserIdFromContext();
        friendRelationshipService.deleteFriendRelationship(userId, request.getFriendId());
        return ApiResponse.ok();
    }

}
