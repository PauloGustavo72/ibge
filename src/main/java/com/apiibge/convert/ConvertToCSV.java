package com.apiibge.convert;

import com.apiibge.request.IbgeVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ConvertToCSV {

    HttpServletResponse convert(List<IbgeVO> ibgeVOS, HttpServletResponse response);
}
