package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.Friendship;
import com.solvd.socialnetworkdao.dao.IFriendshipDAO;
import java.util.List;

public class FriendshipDAO extends MyBatisDAO implements IFriendshipDAO {

    @Override
    public Friendship getById(long id) {
        return executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            return friendshipMapper.getById(id);
        });
    }

    @Override
    public List<Friendship> getAll() {
        return executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            return friendshipMapper.getAll();
        });
    }

    @Override
    public void insert(Friendship friendship, long requesterProfileId, long requestedProfileId) {
        executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            friendshipMapper.insert(friendship, requesterProfileId, requestedProfileId);
            return null;
        });
    }

    @Override
    public void update(Friendship friendship, long requesterProfileId, long requestedProfileId) {
        executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            friendshipMapper.update(friendship, requesterProfileId, requestedProfileId);
            return null;
        });
    }

    @Override
    public List<Friendship> getFriendshipsBySenderProfileId(long id) {
        return executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            return friendshipMapper.getFriendshipsBySenderProfileId(id);
        });
    }

    @Override
    public List<Friendship> getFriendshipsByReceiverProfileId(long id) {
        return executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            return friendshipMapper.getFriendshipsByReceiverProfileId(id);
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IFriendshipDAO friendshipMapper = session.getMapper(IFriendshipDAO.class);
            friendshipMapper.delete(id);
            return null;
        });
    }
}
