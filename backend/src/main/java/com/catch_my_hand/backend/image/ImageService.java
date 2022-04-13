package com.catch_my_hand.backend.image;

import com.catch_my_hand.backend.config.BaseException;
import com.catch_my_hand.backend.image.model.PostImageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catch_my_hand.backend.config.BaseResponseStatus.*;

@Service
public class ImageService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ImageDao imageDAO;
    private final ImageProvider imageProvider;

    @Autowired
    public ImageService(ImageDao imageDAO, ImageProvider imageProvider) {
        this.imageDAO = imageDAO;
        this.imageProvider = imageProvider;
    }

    // 분양 이미지 등록
    public int createPetImage(PostImageReq postImageReq) throws BaseException {
        try {
            int imageidx = imageDAO.createProductImage(postImageReq);
            return imageidx;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 imageidx를 가지는 이미지 수정
    public void modifyPetImage(int petidx, List<String> imgUrlList) throws BaseException {
        try {
            imageDAO.deleteImage("product", petidx);
            for (String imgUrl : imgUrlList) {
                imageDAO.createProductImage(new PostImageReq(
                        imgUrl,
                        petidx
                ));
            }
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int deletePetImage(int petidx) throws BaseException {
        try {
            int deleteImageCnt = imageDAO.deleteImage("product", petidx);
            return deleteImageCnt;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 useridx를 가지는 이미지 수정
    public void modifyUserImage(PostImageReq postImageReq) throws  BaseException {
        try {
            if (imageProvider.getUserImage(postImageReq.getIdx()) == null) {
                imageDAO.createUserImage(postImageReq);
            }
            imageDAO.modifyUserImage(postImageReq);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
