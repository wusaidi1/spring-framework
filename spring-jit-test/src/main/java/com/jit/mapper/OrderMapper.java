package com.jit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
public interface OrderMapper {

	@Select("select 100 from dual")
	int selectPrice();
}
