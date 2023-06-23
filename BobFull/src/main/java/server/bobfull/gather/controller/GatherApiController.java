package server.bobfull.gather.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<GatherCreateResponse> createGather(@RequestHeader("Authorization") Long memberId,@RequestBody GatherCreateRequestDto requestDto){
        return ApiResponse.success(gatherService.createGather(memberId,requestDto));
    }
    @GetMapping(value = "/all",produces = "application/json;charset=UTF-8")
    public ApiResponse<List<GatherResponseDto>> getGathersForList(@RequestHeader("Authorization") Long memberId) {
        return ApiResponse.success(gatherService.getAllGathersForList(memberId));
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<GatherDetailDto> getGatherDetail(@RequestHeader("Authorization") Long memberId,@RequestParam("gatherId") Long gatherId) {
        return ApiResponse.success(gatherService.getGatherDetailByGatherId(memberId,gatherId));
    }

//    @GetMapping(value = "/search",produces = "application/json;charset=UTF-8")
//    public ApiResponse<List<GatherResponseDto>> getGathersBySearchForList(@RequestHeader("Authorization") Long memberId, @RequestParam("searchText") String searchText) {
//        return ApiResponse.success(gatherService.getAllGathersBySearchForList(memberId,searchText));
//    }

}
