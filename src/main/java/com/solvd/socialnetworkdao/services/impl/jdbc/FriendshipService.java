package com.solvd.socialnetworkdao.services.impl.jdbc;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.Profile;
import com.solvd.socialnetworkdao.dao.IFriendshipDAO;
import com.solvd.socialnetworkdao.dao.IProfileDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.FriendshipDAO;
import com.solvd.socialnetworkdao.dao.impl.jdbc.ProfileDAO;
import com.solvd.socialnetworkdao.services.IFriendshipService;

import java.util.List;

public class FriendshipService implements IFriendshipService {
    private final IFriendshipDAO friendshipDAO = new FriendshipDAO();
    private final IProfileDAO profileDAO = new ProfileDAO();
    private final ProfileService profileService = new ProfileService();

    @Override
    public void insert(Friendship friendship) {
        friendshipDAO.insert(friendship);
    }

    public Friendship getById(long id){
        Friendship friendship = friendshipDAO.getById(id);
        setValues(friendship);
        return friendship;
    }

    @Override
    public List<Friendship> getAll() {
        List<Friendship> friendships = friendshipDAO.getAll();
        for(Friendship friendship : friendships){
            setValues(friendship);
        }
        return friendships;
    }

    @Override
    public void update(Friendship friendship) {
        friendshipDAO.update(friendship);
    }

    @Override
    public void delete(long id) {
        friendshipDAO.delete(id);
    }

    private void setValues(Friendship friendship) {
        Profile requesterProfile = profileDAO.getByFriendshipId(friendship.getId());
        requesterProfile = profileService.getById(requesterProfile.getId());

        Profile requestedProfile = profileDAO.getRequestedByFriendshipId(friendship.getId());
        requesterProfile = profileService.getById(requesterProfile.getId());

        friendship.setRequesterProfile(requesterProfile);
        friendship.setRequestedProfile(requestedProfile);
    }
}
