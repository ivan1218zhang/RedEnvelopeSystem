package com.zyf.redenvelopesystem.common.database.dao;

import com.zyf.redenvelopesystem.common.database.dto.Member;
import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(Member record);

    Member selectByPrimaryKey(String id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member record);
}