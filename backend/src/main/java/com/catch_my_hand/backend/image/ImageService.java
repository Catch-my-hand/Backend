package com.catch_my_hand.backend.image;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.image.model.PostImageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catch_my_hand.backend.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Log4j2
@RequiredArgsConstructor
public class ImageService {

    private final ImageDao imageDao;
    private final ImageProvider imageProvider;


    // 게시물 이미지 등록
    public int createProductImage(PostImageReq postImageReq) throws BaseException {
        try {
            int imageIdx = imageDao.createProductImage(postImageReq);
//            System.out.println(imageIdx);
            return imageIdx;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 productIdx를 갖는 이미지 수정
    public void modifyProductImage(int productIdx, List<String> imgUrlList) throws BaseException{
        try {
            imageDao.deleteImage("product", productIdx); // 해당 상품 이미지 전체 삭제
            for(String imgUrl : imgUrlList) {
                imageDao.createProductImage(new PostImageReq(
                        imgUrl,
                        productIdx
                ));
            } // 상품 이미지 새로 등록
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 productIdx를 갖는 이미지 삭제
    public int deleteProductImage(int productIdx) throws BaseException {
        try {
            int deleteImageCnt = imageDao.deleteImage("product", productIdx);
            return deleteImageCnt;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
