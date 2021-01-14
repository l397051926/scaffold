package com.lmx.scaffold.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmx.scaffold.dao.entity.DwsPrsnInfDi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author lmx
 * @since 2020-11-03
 */
public interface DwsPrsnInfDiMapper extends BaseMapper<DwsPrsnInfDi> {

    /**
     * 根据用户列表 查询员工信息 集合
     *
     * @param list
     * @return
     */
    List<DwsPrsnInfDi> querByuserIds(@Param("lists") List<String> list);

    /**
     * 根据指定用户id 查询员工信息
     *
     * @param userId
     * @return
     */
    DwsPrsnInfDi queryByUserId(@Param("userId") String userId);

    /**
     * 根据员工号 查询员工信息
     *
     * @param employId
     * @return
     */
    DwsPrsnInfDi queryEmployId(@Param("employId") String employId);

    List<DwsPrsnInfDi> queryAllUser();

}
