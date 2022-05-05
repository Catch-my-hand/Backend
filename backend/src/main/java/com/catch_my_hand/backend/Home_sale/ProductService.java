package com.catch_my_hand.backend.Home_sale;

import com.catch_my_hand.backend.Home_sale.model.PatchProductReq;
import com.catch_my_hand.backend.Home_sale.model.PostProductReq;
import com.catch_my_hand.backend.Home_sale.model.PostProductRes;
import com.catch_my_hand.backend.Home_sale.model.ProductStatus;
import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.image.ImageService;
import com.catch_my_hand.backend.image.model.PostImageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;
    private final ImageService imageService;


    // 게시물등록(POST)
    public PostProductRes createProduct(PostProductReq postProductReq) throws BaseException {
        try {
            int productidx = productDao.createProduct(postProductReq);
            for(String imgUrl: postProductReq.getImgUrlList()){
                 imageService.createProductImage(new PostImageReq(
                        imgUrl,
                        productidx
                ));
            }
            System.out.println(productidx);
            return new PostProductRes(productidx);
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 게시물수정
    public void modifyProduct(int productidx, PostProductReq postProductReq) throws BaseException {
        try {
            productDao.modifyProduct(productidx, postProductReq); // 상품 정보 수정
            imageService.modifyProductImage(productidx, postProductReq.getImgUrlList()); // 이미지 수정

        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 게시물 상태 수정
    public void modifyProductStatus(PatchProductReq patchProductReq) throws BaseException {
        String status = patchProductReq.getStatus();
        // 상태는 acitve, reserved, completed 만 가능
        if(status.equals(ProductStatus.active.toString()) || status.equals(ProductStatus.reserved.toString()) || status.equals(ProductStatus.completed.toString())){
            int result = productDao.modifyProductStatus(patchProductReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
                throw new BaseException(MODIFY_FAIL_PRODUCT_STATUS);
            }
            // buyer 에 0 삽입

            try{
                productDao.modifyProductBuyer(patchProductReq);
            } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
                throw new BaseException(DATABASE_ERROR);
            }

        } else { //유효하지 않은 상태값
            throw new BaseException(PATCH_PRODUCTS_INVALID_STATUS);
        }
    }


    // 해당 productIdx를 갖는 Product 삭제
    public void deleteProduct(int productidx) throws BaseException{
        try {
            productDao.deleteProduct(productidx);
            imageService.deleteProductImage(productidx); // 해당 상품 이미지 전체 삭제
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
