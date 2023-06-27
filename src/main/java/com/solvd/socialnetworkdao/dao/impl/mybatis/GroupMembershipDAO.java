package com.solvd.socialnetworkdao.dao.impl.mybatis;

import com.solvd.socialnetworkdao.GroupMembership;
import com.solvd.socialnetworkdao.dao.IGroupMembershipDAO;
import java.util.List;

public class GroupMembershipDAO extends MyBatisDAO implements IGroupMembershipDAO {

    @Override
    public GroupMembership getById(long id) {
        return executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            return groupMembershipMapper.getById(id);
        });
    }

    @Override
    public List<GroupMembership> getAll() {
        return executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            return groupMembershipMapper.getAll();
        });
    }

    @Override
    public List<GroupMembership> getGroupMembershipsByProfileId(long profileId) {
        return executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            return groupMembershipMapper.getGroupMembershipsByProfileId(profileId);
        });
    }

    @Override
    public List<GroupMembership> getMembershipsByGroupId(long groupId) {
        return executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            return groupMembershipMapper.getMembershipsByGroupId(groupId);
        });
    }

    @Override
    public void insert(GroupMembership groupMembership, long groupMemberProfileId, long groupId) {
        executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            groupMembershipMapper.insert(groupMembership, groupMemberProfileId, groupId);
            return null;
        });
    }

    @Override
    public void update(GroupMembership groupMembership, long groupMemberProfileId, long groupId) {
        executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            groupMembershipMapper.update(groupMembership, groupMemberProfileId, groupId);
            return null;
        });
    }

    @Override
    public void delete(long id) {
        executeWithSession(session -> {
            IGroupMembershipDAO groupMembershipMapper = session.getMapper(IGroupMembershipDAO.class);
            groupMembershipMapper.delete(id);
            return null;
        });
    }
}
