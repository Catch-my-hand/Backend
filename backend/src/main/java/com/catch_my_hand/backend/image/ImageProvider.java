package com.catch_my_hand.backend.image;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.image.model.GetImageRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
public class ImageProvider {
    private final ImageDao imageDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ImageProvider(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    // 분양 이미지들 전체 조회
    public List<GetImageRes> getProductImages(int productIdx) throws BaseException {
        try {
            List<GetImageRes> getImageRes = imageDao.getProductImages(productIdx);
            return getImageRes;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // pet 대표 이미지 url 조회
    public String getOnePetImage(int petIdx) throws BaseException {
        try {
            List<GetImageRes> getImageRes = imageDao.getOneProductImage(petIdx);
            if (!getImageRes.isEmpty()) {
                return getImageRes.get(0).getImgUrl();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 유저 이미지 조회
    public GetImageRes getUserImage(int useridx) throws BaseException {
        try {
            List<GetImageRes> getImageRes = imageDao.getUserImage(useridx);
            if (!getImageRes.isEmpty()) {
                return getImageRes.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
