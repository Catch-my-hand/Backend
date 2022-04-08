package com.catch_my_hand.backend.image;

import com.catch_my_hand.backend.image.model.GetImageRes;
import com.catch_my_hand.backend.image.model.PostImageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ImageDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //애견사진 등록
    public int createProductImage(PostImageReq postImageReq) {
        String createProductImageQuery = "Insert into Image (imgUrl, imgCategory, productIdx) VALUES (?,?,?)";
        Object[] createImageParams = new Object[]{postImageReq.getImgUrl(), "product", postImageReq.getIdx()};
        this.jdbcTemplate.update(createProductImageQuery, createImageParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 사진 전체 조회
    public List<GetImageRes> getProductImages(int productIdx) {
        String getProductImagesQuery = "select imgIdx, imgUrl from Image where productIdx = ? ";
        return this.jdbcTemplate.query(getProductImagesQuery,
                (rs, rowNum) -> new GetImageRes(
                        rs.getInt("imgidx"),
                        rs.getString("imgUrl")
                ), productIdx);
    }

    // 대표 이미지 1개
    public List<GetImageRes> getOneProductImage(int productIdx) {
        String getPetImageQuery = "select imgidx, imgUrl from Image where productidx=? limit 1";
        return this.jdbcTemplate.query(getPetImageQuery,
                (rs, rowNum) -> new GetImageRes(
                        rs.getInt("imgidx"),
                        rs.getString("imgUrl")
                ), productIdx);
    }


    // 유저 이미지 등록
    public int createUserImage(PostImageReq postImageReq) {
        String createUserImageQuery = "Insert into Image (imgUrl, imgCategory, productIdx) VALUES (?,?,?)";
        Object[] createImageParams = new Object[]{postImageReq.getImgUrl(), "user", postImageReq.getIdx()};
        this.jdbcTemplate.update(createUserImageQuery, createImageParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 유저 이미지 조회
    public List<GetImageRes> getUserImage(int useridx) {
        String getUserImageQuery = "select imgidx, imgUrl from Image where useridx=?";
        return this.jdbcTemplate.query(getUserImageQuery,
                (rs, rowNum) -> new GetImageRes(
                        rs.getInt("imgidx"),
                        rs.getString("imgUrl")
                ), useridx);
    }

    // 이미지 수정
    public void modifyUserImage(PostImageReq postImageReq) {
        String modifyUserImageQuery = "update Image set imgUrl=? where useridx = ?";
        Object[] modifyUserImageParams = new Object[]{postImageReq.getImgUrl(), postImageReq.getIdx()};
        this.jdbcTemplate.update(modifyUserImageQuery, modifyUserImageParams);
    }

    // 이미지 삭제
    public int deleteImage(String imgCategory, int idx) {
        String deleteImageQuery = "";
        switch (imgCategory) {
            case "post":
                deleteImageQuery = "DELETE FROM Image WHERE postidx = ?";
                break;
            case "product":
                deleteImageQuery = "DELETE FROM Image WHERE productidx = ?";
                break;
            case "user":
                deleteImageQuery = "DELETE FROM Image WHERE useridx = ? ";
                break;
            default:
                return 0;
        }
        int getIdxParams = idx;
        return this.jdbcTemplate.update(deleteImageQuery, getIdxParams);
    }
}
