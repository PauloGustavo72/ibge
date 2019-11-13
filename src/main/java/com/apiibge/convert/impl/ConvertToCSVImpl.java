package com.apiibge.convert.impl;

import com.apiibge.convert.ConvertToCSV;
import com.apiibge.exception.OutputStreamException;
import com.apiibge.request.IbgeVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Service
public class ConvertToCSVImpl implements ConvertToCSV {
    private static final Logger logger = LogManager.getLogger(ConvertToCSVImpl.class);

    private static final String HEADER = "idEstado,siglaEstado,regiaoNome,nomeCidade,nomeMesorregiao,nomeFormatado \n";

    @Override
    public HttpServletResponse convert(List<IbgeVO> ibgeVOS, HttpServletResponse response) {
        response.setContentType("application/ms-excel"); // or you can use text/csv
        response.setHeader("Content-Disposition", "attachment; filename=output.csv");

        try {
            OutputStream out = response.getOutputStream();
            out.write(HEADER.getBytes());

            for ( IbgeVO ibgeVO: ibgeVOS ) {
                out.write( ibgeVO.getLineOfCSV().getBytes());
            }

            out.flush();
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OutputStreamException(e.getMessage());
        }
    }
}
