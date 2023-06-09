package com.solvd.socialnetworkdao.services.impl;

import com.solvd.socialnetworkdao.Post;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.ProfileTag;
import com.solvd.socialnetworkdao.dao.IPostDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.IProfileTagDAO;
import com.solvd.socialnetworkdao.dao.impl.PostDAO;
import com.solvd.socialnetworkdao.dao.impl.ProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.ProfileTagDAO;
import com.solvd.socialnetworkdao.services.IPostService;
import com.solvd.socialnetworkdao.services.IProfileService;
import com.solvd.socialnetworkdao.services.IProfileTagService;

import java.util.List;

public class ProfileTagService implements IProfileTagService {
    private final IProfileTagDAO profileTagDAO = new ProfileTagDAO();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final IProfileService profileService = new ProfileService();
    private final IPostDAO postDAO = new PostDAO();
    private final IPostService postService = new PostService();

    @Override
    public void insert(ProfileTag profileTag) {
        profileTagDAO.insert(profileTag);
    }

    @Override
    public ProfileTag getById(long id) {
        ProfileTag profileTag = profileTagDAO.getById(id);
        setValues(profileTag);
        return profileTag;
    }

    @Override
    public List<ProfileTag> getAll() {
        List<ProfileTag> profileTags = profileTagDAO.getAll();
        for(ProfileTag profileTag : profileTags){
            setValues(profileTag);
        }
        return profileTags;
    }

    @Override
    public void update(ProfileTag profileTag) {
        profileTagDAO.update(profileTag);
    }

    @Override
    public void delete(long id) {
        profileTagDAO.delete(id);
    }

    @Override
    public void setValues(ProfileTag profileTag) {
        Profile profile = profileDAO.getByProfileTagId(profileTag.getId());
        profileService.setValues(profile);

        Post post = postDAO.getByProfileTagId(profileTag.getId());
        postService.setValues(post);

        profileTag.setTaggedProfile(profile);
        profileTag.setTaggedPost(post);
    }
}
