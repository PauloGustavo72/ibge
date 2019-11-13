package com.apiibge.convert.impl;

import com.apiibge.exception.OutputStreamException;
import com.apiibge.request.IbgeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConvertToCSVImplTest {

    @InjectMocks private ConvertToCSVImpl convertToCSV ;

    @Mock private HttpServletResponse httpServletResponse;

    @Mock private ServletOutputStream outputStream;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_Check_OutputStream() throws IOException {
        // Mock
        List<IbgeVO> ibgeVOS = getIbgeVOS();

        when(httpServletResponse.getOutputStream())
                .thenReturn(outputStream);

        // Method call
        convertToCSV.convert(ibgeVOS, httpServletResponse);

        //Assertions
        verify(httpServletResponse).setContentType(anyString());
        verify(httpServletResponse).setHeader(anyString(), anyString());
        verify(outputStream, Mockito.times(2)).write(Mockito.any());

    }

    @Test(expected = OutputStreamException.class)
    public void should_Return_OutputStreamException() throws IOException {
        // Mock
        List<IbgeVO> ibgeVOS = getIbgeVOS();

        // Method call
        convertToCSV.convert(ibgeVOS, httpServletResponse);
    }

    private List<IbgeVO> getIbgeVOS() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File("src/test/resources/ibgeVOS.json"),
                                mapper.getTypeFactory().
                                constructCollectionType(List.class, IbgeVO.class));
    }


}