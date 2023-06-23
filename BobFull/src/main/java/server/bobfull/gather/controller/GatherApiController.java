package server.bobfull.gather.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.bobfull.common.dto.ApiResponse;
import server.bobfull.gather.domain.application.GatherService;
import server.bobfull.gather.dto.GatherDtos.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gather")
public class GatherApiController {
    private final GatherService gatherService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<GatherCreateResponse> createGather(@RequestHeader("Authorization") Long memberId,
                                                        @RequestBody GatherCreateRequestDto requestDto) {
        return ApiResponse.success(gatherService.createGather(memberId,requestDto));
    }
    @GetMapping(value = "/all",produces = "application/json;charset=UTF-8")
    public ApiResponse<List<GatherResponseDto>> getGathersForList(@RequestHeader("Authorization") Long memberId,
                                                                @RequestParam("bigLocation") String bigLocation,
                                                                @RequestParam("smallLocation") String smallLocation) {
        return ApiResponse.success(gatherService.getAllGathersForList(memberId,bigLocation,smallLocation));
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<GatherDetailDto> getGatherDetail(@RequestHeader("Authorization") Long memberId,@RequestParam("gatherId") Long gatherId) {
        return ApiResponse.success(gatherService.getGatherDetailByGatherId(memberId,gatherId));
    }
    @DeleteMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<Boolean> removeGather(@RequestHeader("Authorization") Long memberId,@RequestParam("gatherId") Long gatherId) {
        return ApiResponse.success(gatherService.deleteGatherByGatherId(memberId,gatherId));
    }
    @GetMapping(value = "/self",produces = "application/json;charset=UTF-8")
    public ApiResponse<GatherDetailDto> getGatherDetailByMemberId(@RequestHeader("Authorization") Long memberId) {
        return ApiResponse.success(gatherService.getGatherDetailByMemberId(memberId));
    }
    @GetMapping(value = "/proposal/join",produces = "application/json;charset=UTF-8")
    public ApiResponse<Boolean> checkJoinGather(@RequestHeader("Authorization") Long memberId,
                                                @RequestParam("gatherId") Long gatherId) {
        return ApiResponse.success(gatherService.proposalJoin(memberId,gatherId));
    }
    @GetMapping(value = "/proposal/confirm",produces = "application/json;charset=UTF-8")
    public ApiResponse<Boolean> checkConfirmGatherJoin(@RequestHeader("Authorization") Long memberId,
                                                      @RequestParam("confirmMemberId") Long confirmMemberId, // 상대방의 memberId
                                                        @RequestParam("friendId") Long friendId,
                                                      @RequestParam("confirm") Boolean confirm) {   // 승낙 true
        return ApiResponse.success(gatherService.proposalConfirm(memberId,confirm,confirmMemberId,friendId));
    }

}
