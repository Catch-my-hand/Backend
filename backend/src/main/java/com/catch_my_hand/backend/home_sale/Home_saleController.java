package com.catch_my_hand.backend.home_sale;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponse;
import com.catch_my_hand.backend.home_sale.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "가정분양", description = "가정분양 REST API")
public class Home_saleController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final Home_saleProvider home_saleProvider;
    private final Home_saleService home_saleService;

    @Autowired
    public Home_saleController(Home_saleProvider home_saleProvider, Home_saleService home_saleService) {
        this.home_saleProvider = home_saleProvider;
        this.home_saleService = home_saleService;
    }

    // 분양 등록 API
    // api/v1/home-post [POST]
    @ResponseBody
    @PostMapping("home-post")
    public BaseResponse<PostPetRes> createPet(@RequestBody PostPetReq postPetReq) {
        try {
            PostPetRes postPetRes = home_saleService.createPets(postPetReq);
            return new BaseResponse<>(postPetRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    // 분양 수정 API
    // api/v1/:productidx [POST]
    @ResponseBody
    @PostMapping("/{productidx}")
    public BaseResponse<String> modifyPet(@PathVariable("productidx") int productidx, @RequestBody PostPetReq postPetReq) {
        try {
            home_saleService.modifyPet(productidx, postPetReq);
            String result = "수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    // 분양 게시물 전체 조회
    // api/v1/home-post [GET]
    @ResponseBody
    @GetMapping("/home-post")
    public BaseResponse<List<GetPetPreviewRes>> getPet( @RequestParam(required = false, defaultValue = "1") int page,
                                                       @RequestParam(required = false, defaultValue = "10")int size) {

        try {
            List<GetPetPreviewRes> getPetPreviewResList = home_saleProvider.getAllPets(page, size);
            return new BaseResponse<>(getPetPreviewResList);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    // 현재 분양 진행중인 게시물만 조회 API
    // api/v1/active
    @ResponseBody
    @GetMapping("/active")
    public BaseResponse<List<GetPetPreviewRes>> getActivePet(@RequestParam(required = false, defaultValue = "1") int page,
                                                             @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            List<GetPetPreviewRes> petListRes = home_saleProvider.getActivePets(page, size);
            return new BaseResponse<>(petListRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    // 분양 상태 변경 API
    // api/v1/{productidx}/status [POST]

    @ResponseBody
    @PostMapping("/{productidx}/status")
    public BaseResponse<String> modifyPetStatus(@PathVariable("productidx") int productidx, @RequestBody Product product) {
        try {
            String status = product.getStatus();
            home_saleService.modifyPetStatus(new PatchPetReq(productidx, status));
            String result = "수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }





}
