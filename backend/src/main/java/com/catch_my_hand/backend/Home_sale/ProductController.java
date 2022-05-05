package com.catch_my_hand.backend.Home_sale;

import com.catch_my_hand.backend.Home_sale.model.*;
import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.config.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@Log4j2
@RequiredArgsConstructor
@Tag(name = "Pet Controller", description = "Pet Controller REST API")
public class ProductController {

    private final ProductProvider productProvider;
    private final ProductService productService;



    // 게시물 등록
    //Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostProductRes> createProduct(@RequestBody PostProductReq postProductReq){

        try {
            PostProductRes postProductRes = productService.createProduct(postProductReq);
            return new BaseResponse<>(postProductRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 게시물 수정
    @ResponseBody
    @PutMapping("/{productidx}")
    public BaseResponse<String> modifyProduct(@PathVariable("productidx") int productidx, @RequestBody PostProductReq postProductReq){

        try {
            productService.modifyProduct(productidx, postProductReq);
            String result = "게시물정보가 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 모든 게시물 보기
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetProductPreviewRes>> getProduct(@RequestParam(required = false) String search,
                                                               @RequestParam(required = false, defaultValue = "1") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int size){
        try {
            if (search == null) { // query string인 search가 없을 경우, 그냥 전체 상품정보를 불러온다.
                List<GetProductPreviewRes> getProductPreviewResList = productProvider.getAllProducts(page,size);
                return new BaseResponse<>(getProductPreviewResList);
            }
            // query string인 search가 있을 경우, title 에서 검색한다..
            List<GetProductPreviewRes> getProductPreviewResList = productProvider.getProductsByTitle(search,page,size);
            return new BaseResponse<>(getProductPreviewResList);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 현재 분양 진행중인 게시물만 보기
    @ResponseBody
    @GetMapping("/active")
    public BaseResponse<List<GetProductPreviewRes>> getActiveProduct(@RequestParam(required = false, defaultValue = "1") int page,
                                                                     @RequestParam(required = false, defaultValue = "10")int size){
        try {
            List<GetProductPreviewRes> productListRes = productProvider.getActiveProducts(page,size);
            return new BaseResponse<>(productListRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 게시물 상태 변경
    @ResponseBody
    @PatchMapping("/{productidx}/status")
    public BaseResponse<String> modifyProductStatus(@PathVariable("productidx") int productidx, @RequestBody Product product) {

        try {
            // 판매중으로 변경
            String status = product.getStatus();
            productService.modifyProductStatus(new PatchProductReq(productidx,status));
            String result = "상품 상태가 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 게시물 삭제
    @ResponseBody
    @DeleteMapping("/{productidx}")
    public BaseResponse<String> deleteUser(@PathVariable("productidx") int productIdx) {

        try {
            productService.deleteProduct(productIdx);
            String result = "삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }




}
