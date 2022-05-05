package com.catch_my_hand.backend.image;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.image.model.GetImageRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catch_my_hand.backend.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Log4j2
@RequiredArgsConstructor
public class ImageProvider {
    private final ImageDao imageDao;

    // 게시물 이미지들 전체 조회
    public List<GetImageRes> getProductImages(int productIdx) throws BaseException {
        try {
            List<GetImageRes> getImagesRes = imageDao.getProductImages(productIdx);
            return getImagesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 게시물 대표 이미지 url 조회
    public String getOneProductImage(int productIdx) throws BaseException {
        try {
            List<GetImageRes> getImageRes = imageDao.getOneProductImage(productIdx);
            if (!getImageRes.isEmpty()){
                return getImageRes.get(0).getImgUrl();
            } else {
                return null;
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }





}
